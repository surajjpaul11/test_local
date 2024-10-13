package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class PageObjectModelGenerator {

    public static void main(String[] args) {
        // Path to the JSON file
        String jsonFilePath = "i131.json";
        
        // Directory where the generated POM files will be saved
        String outputDirectory = "src/test/java/pageObjects/";

        try {
            // Informing user that JSON parsing is starting
            System.out.println("Starting to parse JSON file: " + jsonFilePath);

            // Parse the JSON file using ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));
            
            // Inform user that parsing is successful
            System.out.println("JSON file parsed successfully.");

            // Begin generating Page Object Model (POM) files
            generatePageObjectModels(rootNode, outputDirectory);

        } catch (IOException e) {
            // Print error in case something goes wrong while reading the file
            e.printStackTrace();
        }
    }

    private static void generatePageObjectModels(JsonNode rootNode, String outputDirectory) throws IOException {
        // Check if output directory exists, if not, create it
        File dir = new File(outputDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("Output directory created: " + outputDirectory);
        } else {
            System.out.println("Using existing output directory: " + outputDirectory);
        }

        // Get all fields from the JSON
        Iterator<JsonNode> fields = rootNode.elements();
        String currentPage = "";  // Keeps track of the current page we are generating
        StringBuilder classBuilder = new StringBuilder();  // StringBuilder for class content

        // Iterating through all fields in the JSON
        System.out.println("Starting field processing...");
        while (fields.hasNext()) {
            JsonNode field = fields.next();
            JsonNode tuNode = field.get("TU"); // "TU" field gives page and section info
            JsonNode idNode = field.get("id"); // Field ID
            JsonNode nameNode = field.get("name"); // Field Name

            if (tuNode != null) {
                String tuText = tuNode.asText();
                String[] tuParts = tuText.split(" "); // Split the TU string to get relevant parts

                // Assuming first part is page info
                String page = tuParts[1]; // "Part 1." etc.

                // Check if we have encountered a new page
                if (!page.equals(currentPage)) {
                    if (!currentPage.isEmpty()) {
                        // We are finishing the previous page, write the class to file
                        writeClassToFile(currentPage, classBuilder, outputDirectory);
                        System.out.println("Page Object Model class written for: " + currentPage);
                        classBuilder.setLength(0); // Reset the StringBuilder for the new class
                    }

                    // Start a new class for the new page
                    currentPage = page;
                    classBuilder.append(generateClassHeader(page));
                    System.out.println("Generating POM for page: " + page);
                }

                // Append field information with comments and @FindBy annotations
                String fieldComment = "// " + tuText;
                String fieldDefinition = generateFieldDefinition(idNode.asText(), nameNode.asText());
                classBuilder.append(fieldComment).append("\n").append(fieldDefinition).append("\n\n");
                System.out.println("Processed field: " + nameNode.asText() + " (ID: " + idNode.asText() + ")");
            }
        }

        // Write the last page to a file after processing all fields
        if (!currentPage.isEmpty()) {
            writeClassToFile(currentPage, classBuilder, outputDirectory);
            System.out.println("Page Object Model class written for: " + currentPage);
        }

        System.out.println("All fields processed and POM files generated successfully.");
    }

    // Method to generate class header
    private static String generateClassHeader(String pageName) {
        return "package pageObjects;\n\n" +
               "import org.openqa.selenium.WebDriver;\n" +
               "import org.openqa.selenium.WebElement;\n" +
               "import org.openqa.selenium.support.FindBy;\n" +
               "import org.openqa.selenium.support.PageFactory;\n\n" +
               "/**\n" +
               " * Page Object Model for " + pageName + ".\n" +
               " */\n" +
               "public class " + formatPageName(pageName) + "Page {\n" +
               "    WebDriver driver;\n\n" +
               "    // Constructor\n" +
               "    public " + formatPageName(pageName) + "Page(WebDriver driver) {\n" +
               "        this.driver = driver;\n" +
               "        PageFactory.initElements(driver, this);\n" +
               "    }\n\n";
    }

    // Method to generate field definition with @FindBy annotation
    private static String generateFieldDefinition(String fieldId, String fieldName) {
        return "    @FindBy(id = \"" + fieldId + "\")\n" +
               "    WebElement " + fieldName + ";\n";
    }

    // Helper method to format page names by removing spaces and periods
    private static String formatPageName(String pageName) {
        return pageName.replace(".", "").replace(" ", "");
    }

    // Method to write the generated class to a .java file
    private static void writeClassToFile(String pageName, StringBuilder classBuilder, String outputDirectory) throws IOException {
        String className = formatPageName(pageName) + "Page";
        String classContent = classBuilder.append("}\n").toString();
        
        // Writing the class content to a .java file
        FileWriter writer = new FileWriter(outputDirectory + className + ".java");
        writer.write(classContent);
        writer.close();
        
        System.out.println("Class file generated: " + className + ".java");
    }
}
