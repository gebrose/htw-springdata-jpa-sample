### HTW Berlin, IMI

#### Datenbanken, SS 2022, Brose

# Spring Data JPA sample application

**1. Prerequisites**

You need a PostgreSQL installation with the latest example schema 'uni' (included here) and the example data.

The installation can be local, but you can also use the HTW lab database server.

The sample code requires a Java 17 SDK to compile and run.

**2. Configuration**

Adjust settings in application.properties

**3. Preparation**

Run `mvn clean test` or the individual integration test `BueroRaumEntityTest` to test your environment setup.

**4.Starting the application in IntelliJ Idea**

Go to `de.htw.imi.springdatajpa.Application`.
Right-click on the class name and select "Run Application" or "Debug Application" in IntelliJ Idea.

The project can also be run from the command line using `mvn org.springframework.boot:spring-boot-maven-plugin:run`

The running application can be accessed at `http://localhost:8080`.

**5. Extending the application**

The web application uses **Springboot**  for server-side rendering and **Thymeleaf** as a templating engine.
Thymeleaf has a powerful expression language,
but you do not need to learn any details to work on the tasks here.
Copying and modifying the example HTML code should be straightforward.

HTML files reside in `src/main/resources/templates`,
styles are defined in `src/main/resources/static/main.css`.