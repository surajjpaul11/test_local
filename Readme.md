## Selenium Cucumber Test Automation Framework
Project Overview
This is a Selenium and Cucumber based test automation framework using the Page Object Model (POM) design pattern. It provides easy-to-read feature files written in Gherkin syntax and automates web application testing using Selenium. The framework is integrated with WebDriverManager to handle browser drivers and uses JUnit as the test runner.

## Features
- **Page Object Model (POM)**: Maintainable and reusable structure for UI components.
- **Cucumber**: Behavior-Driven Development (BDD) with feature files and step definitions.
- **Selenium WebDriver**: UI testing automation.
- **Field Validation Utilities**: Generic utility methods for common field validations.
- **Cucumber Reports**: Generate and view test execution reports.
- **Continuous Integration**: GitHub Actions CI integration with test execution and report generation.


## Project Structure
project-folder/
|-- src
|   |-- main
|   |   |-- java
|   |       |-- pageObjects     
        │   │   └── LoginPage.java    # Contains all POM (Page Object Model) classes
|   |       |-- utils     
        │       └── WebDriverManager.java          # Contains utility classes like FieldValidationUtils and PageValidationUtils
|-- src
|   |-- test
|       |-- java
|           |-- stepDefinitions     # Step definitions for Cucumber
        │   │   ├── LoginSteps.java
        │   │   └── Hooks.java
|           |-- runners             # Test runners for Cucumber
                └── TestRunner.java
        └-- resources/
            └── features/
                └── login.feature
|-- target                           # Generated reports and other build artifacts
|-- .github
|   |-- workflows
|       |-- ci.yml                  # GitHub Actions CI workflow file
|-- pom.xml                          # Maven configuration file
|-- README.md                        # Project documentation
|-- .gitignore                       # Git ignore rules



## Key Components
pom.xml: Maven configuration file, managing project dependencies.
pageObjects/: Contains the Page Object classes that abstract the web elements and actions.
stepDefinitions/: Defines the Cucumber step definitions that connect feature files to Selenium actions.
runners/: Contains the TestRunner class to execute the tests.
utils/: Utility classes, such as WebDriver management.
Prerequisites
Before running the project, ensure the following dependencies are installed:

## JDK 11+: Download Java
Apache Maven: Download Maven
Google Chrome: Ensure that the latest version of Chrome is installed.
Running the Tests
Running via Maven
To run the test suite using Maven, follow these steps:

## Clone the repository:
git clone https://github.com/your-repo/your-project.git


## Execute tests: Run the following command in the terminal to execute the tests:
cd your-project
mvn clean test
Running via IntelliJ IDEA or VSCode
Open the project in your preferred IDE.
Run the TestRunner.java: Navigate to src/test/java/runners/TestRunner.java, right-click on the file, and select Run 'TestRunner'.


## Test Results
After the test run, the results are output to the console and an HTML report is generated in:
target/cucumber-reports.html
You can open this file in any browser to view the detailed results.

## GitHub Actions Integration
The project includes a GitHub Actions workflow to run the tests automatically when code is pushed to the main branch or a pull request is opened.

## GitHub Actions Workflow
The workflow configuration can be found in .github/workflows/ci.yml.

## The CI workflow:
Sets up Java 11.
Installs the latest version of Chrome.
Runs the tests using Maven.
Uploads the Cucumber HTML report as an artifact.
Modifications

## Updating Feature Files
Feature files are located in src/test/resources/features. To add or modify tests, simply edit or create new .feature files using Gherkin syntax.

## Using Specific WebDriver Versions
If you need to use a specific version of a WebDriver, modify the WebDriverManager configuration in your setup:
WebDriverManager.chromedriver().driverVersion("114.0.5735.90").setup();


## Dependencies
The framework uses the following primary dependencies:
Cucumber: BDD framework for defining test cases.
Selenium: Browser automation.
WebDriverManager: For automatic WebDriver management.
JUnit: Test runner.
All dependencies are managed via Maven and are defined in the pom.xml file.

