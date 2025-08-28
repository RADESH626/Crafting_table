# System Patterns

**Architecture:** Standard Spring Boot application architecture.

**Key Technical Decisions:**
- Used Maven for dependency management.
- Employed Spring Boot's auto-configuration for MongoDB integration.
- Used constructor injection for `MongoClient`.
- Placed configuration in a separate `utils` package for better organization.
