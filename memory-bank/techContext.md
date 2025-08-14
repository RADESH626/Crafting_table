# Tech Context

## Technologies Used

-   **Java:** The core programming language. Based on the project structure, it's likely Java 17 or newer, as is common with recent Spring Boot versions.
-   **Spring Boot:** The primary framework for building the application. It simplifies the creation of stand-alone, production-grade Spring-based applications. Key features we will use include:
    -   **Spring Web:** For building RESTful web services.
    -   **Embedded Tomcat:** As the default web server.
    -   **Jackson:** The default library for JSON serialization and deserialization.
-   **Maven:** The build automation and dependency management tool, as indicated by the `pom.xml` file.

## Development Setup

-   **IDE:** The user is interacting via a VS Code-like environment.
-   **Build Process:** The project is built using Maven. The `mvnw` (Maven wrapper) scripts are present, which means we can build the project using `./mvnw clean install` without needing a system-wide Maven installation.
-   **Running the Application:** The application can be started via the main class `CraftingTableApplication.java` or by using the Spring Boot Maven plugin: `./mvnw spring-boot:run`.

## Technical Constraints

-   We must work within the existing project structure.
-   New code should follow the existing package naming convention (`com.crafting_table.crafting_table`).
-   Dependencies should be managed through the `pom.xml` file. For this task, no new dependencies should be required, as `spring-boot-starter-web` includes everything we need for a JSON API.

## Tool Usage Patterns

-   **File Creation:** New Java classes will be created in the appropriate package directory under `src/main/java/com/crafting_table/crafting_table`.
-   **Code Edits:** Changes to existing files will be done carefully, respecting the surrounding code style.
-   **Terminal Commands:** We will use `mvnw` for any build or runtime commands to ensure consistency.
