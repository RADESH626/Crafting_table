# Active Context

**Current Work:** Successfully configured MongoDB connection for a Spring Boot Java project.

**Recent Changes:**
- Added `spring-boot-starter-data-mongodb` dependency to `pom.xml`.
- Created `MongoConfig.java` in `src/main/java/com/crafting_table/crafting_table/utils/` for connection management.
- Modified `Controller.java` to inject `MongoClient` and added a `/conexion` endpoint to test the connection.

**Next Steps:**
- The task of connecting to MongoDB is complete.
- Future work could involve using the `MongoClient` to perform CRUD operations or integrate it further into the application's core logic (e.g., the `/craft` endpoint).
