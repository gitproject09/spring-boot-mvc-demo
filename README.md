<h1 align="center">   
Spring BOOT MVC DEMO: A Comprehensive Example
</h1>

## 🔎 Table of Contents:

- [About](#about)
- [Objective](#objective)
- [Technical features](#technical-features)
- [Available Endpoints](#available-endpoints)
- [Project Structure](#project-structure)
- [Architecture](#architecture)
    - [Globale Architecture](#architecture)
    - [MVC Pattern](#mvc)
- [Data Flow](#data-flow)
- [Used Technologies](#used-technologies)
- [Contacts](#contacts)

## <a name="about"></a> 📎 About:

- This Demo is designed to showcase the implementation of a Spring Boot application using the
  Model-View-Controller (MVC) architectural pattern.

## <a name="objective"></a> 📎 Objective:

- The primary objective of this project is to provide a simple yet comprehensive example of building a web application
  with Spring Boot, focusing on MVC design pattern.
- While the example used in this demo revolves around managing a company, the primary focus is on demonstrating
  the capabilities of Spring Boot and MVC architecture rather than addressing specific business needs,
  The goal is to provide developers with insights into how to effectively leverage these technologies
  to develop a robust and scalable web application adhering to best practices and conventions such as:

    - Dependency Inversion Principle (DIP),
    - Dependency Injection (DI),
    - Open/Closed Principle (OCP),
    - Single Responsibility Principle (SRP),
    - Use of Constants and more.

- These principles and practices ensure maintainability, modularity and scalability which makes the application
  easier to maintain and extend in the long run.

## <a name="technical-features"></a> 📎 Technical Features:

| Section                  | Description                                                                                                                  |
|--------------------------|------------------------------------------------------------------------------------------------------------------------------|
| **Project Structure**    | Organized project structure following best practices and conventions to enhance code readability and maintainability.        |
| **DTOs**                 | Usage of DTOs to encapsulate data exchanged between the client and server.                                                   |
| **Mapper Classes**       | Mapper classes for mapping between DTOs and entities, promoting clean separation of concerns and enhancing maintainability.  |
| **Repository Layer**     | Data access logic managed by JPA repositories, leveraging Spring Data JPA for seamless database interactions.                |
| **Service Layer**        | Business logic encapsulated in service classes to maintain separation of concerns and enhance modularity.                    |
| **MVC Architecture**     | Implementation of the Model-View-Controller architectural pattern for organizing and managing the application's components.  |
| **Thymeleaf Templates**  | Use of Thymeleaf as the templating engine for generating dynamic HTML content, enhancing server-side rendering capabilities. |
| **Exception Handling**   | Custom exception handling for gracefully managing errors and providing meaningful responses to clients.                      |
| **Database Interaction** | Interaction with MySQL database using Hibernate as the JPA implementation, ensuring efficient data storage and retrieval.    |

### Home Screen Screenshot:

<p align="center">
<img src="public-doc/home.jpg">
</p>

## <a name="available-endpoints"></a> 📎 Available Endpoints:

- Bellow is a table representing the endpoints available in this demo application after it has been launched:

| Endpoint                  | HTTP Method | Description                                            |
|---------------------------|-------------|--------------------------------------------------------|
| /companies                | GET         | Display the home page with paginated list of companies |
| /companies/page/{pageNum} | GET         | Display paginated list of companies                    |
| /companies/{id}           | GET         | Display details of a specific company                  |
| /companies/new            | GET         | Display form to add a new company                      |
| /companies                | POST        | Save a new company                                     |
| /companies/{id}/edit      | GET         | Display form to edit an existing company               |
| /companies/{id}/update    | POST        | Update an existing company                             |
| /companies/{id}/delete    | GET         | Delete a specific company                              |

## <a name="project-structure"></a> 📎 Project Structure:

```Bash
spring-Boot-MVC-demo/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── sopan/
│   │   │           └── springBootMVCDemo/
│   │   │               ├── controller/
│   │   │               │   └── CompanyController.java
│   │   │               ├── dto/
│   │   │               │   └── CompanyDTO.java
│   │   │               ├── entity/
│   │   │               │   └── Company.java
│   │   │               ├── enums/
│   │   │               │   └── Sector.java
│   │   │               ├── exception/
│   │   │               │   └── CompanyNoSuchElementException.java
│   │   │               ├── handler/
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── mapper/
│   │   │               │   └── CompanyMapper.java
│   │   │               ├── repository/
│   │   │               │   └── CompanyRepository.java
│   │   │               ├── service/
│   │   │               │   ├── CompanyService.java
│   │   │               │   └── impl/
│   │   │               │       └── CompanyServiceImpl.java
│   │   │               ├── util/
│   │   │               │   └── Constants.java
│   │   │               └── SpringBootDemoApplication.java
│   │   │
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   │   └── css/
│   │   │   │       └── styles.css
│   │   │   ├── templates/
│   │   │   │   ├── companies/
│   │   │   │   │   ├── addCompany.html
│   │   │   │   │   ├── companies.html
│   │   │   │   │   ├── company.html
│   │   │   │   │   └── editCompany.html
│   │   │   │   ├── fragments/
│   │   │   │   │   ├── footer.html
│   │   │   │   │   └── navbar.html
│   │   │   │   └── template.html
│   │   │   ├── application.properties
│   │   │   └── messages.properties
│   │   │
├── pom.xml
└── README.md
 ```

### Note:

- The project structure of this demo based Spring boot mvc is organized to maintain a clear separation of concerns and
  facilitate modular development. Below is an overview of the main components:

| Component                  | Description                                                                                                                                                                                                                                                                                                                                                                                                                                      |
|----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **pom.xml**                | The pom.xml file contains project configuration and dependencies managed by Apache Maven. It specifies required dependencies such as, in our case, Spring Boot Starter, Thymeleaf, Spring Data JPA, MySQL Connector, and Bootstrap.                                                                                                                                                                                                              |
| **application.properties** | The application.properties file holds configuration properties for the Spring Boot application. It allows configuring settings like database connection details, server port etc.                                                                                                                                                                                                                                                                |
| **Entity**                 | The Entity layer represents the main data manipulated by the application. It defines the structure of data stored in the database. With each entity is annotated with @Entity and corresponds to a table in the database.                                                                                                                                                                                                                        |
| **Repository**             | The Repository layer serves as an interface for database operations within the application, enabling interaction with the database through Spring Data JPA. It offers methods for performing CRUD operations on the relevant entity. A Repository is commonly annotated with @Repository to signify its purpose within the application and facilitate dependency injection.                                                                      |
| **DTO**                    | The DTO component encapsulates data exchanged between the client and server, providing a simplified representation of entity data. It enhances efficiency in data transfer between different layers of the application. In our example : CompanyDTO servers as a simplified representation of the company entity data, facilitating communication between the client and server layers by containing only relevant attributes for data exchange. |
| **Mapper**                 | The Mapper component contains class responsible for mapping between DTOs and entities, promoting clean separation of concerns by handling the conversion of data between different layers of the application.                                                                                                                                                                                                                                    |
| **Service**                | The Service layer encapsulates the business logic of the application. A Service is commonly annotated with @Service to denote its role within the application and interact with the repository layer to perform database operations.                                                                                                                                                                                                             |
| **Controller**             | The Controller layer handle HTTP requests and define endpoint mappings for various operations. A controller is annotated with @Controller or @RestController and interact with the service layer to execute business logic and return appropriate responses.                                                                                                                                                                                     |
| **Constants**              | The Constants component contains application-wide constants used throughout the application. It promotes code reusability and maintainability by centralizing the definition of constants in a single location.                                                                                                                                                                                                                                    |
| **Exception handler**      | This component handles exceptions that may occur during application execution. it includes custom exceptions which extends RuntimeException and a global exception handler GlobalExceptionHandler to manage exceptions globally and provide meaningful responses to clients.                                                                                                                                                                     |
| **Templates**              | Templates, typically implemented using Thymeleaf, define the views presented to users in the web application. They contain HTML markup along with Thymeleaf attributes to dynamically render data from the backend. Stored in the src/main/resources/templates directory, these templates are used by controllers to generate HTML responses sent to clients.                                                                                    |
| **Static Resources**       | Static resources such as CSS files, images, and JavaScript files are stored in the src/main/resources/static directory. These resources are served directly to clients by the server and are used to enhance the appearance and functionality of the web application.                                                                                                                                                                           |
| **Database**               | The application uses MySQL as the database backend, and database schema management is handled automatically by Hibernate based on entity mappings.                                                                                                                                                                                                                                                                                               |

## <a name="architecture"></a> 📎 Global Architecture :

<h1 align="center">
<img src="public-doc/architecture.jpg">
</h1>

### Overview:

- This diagram represents the technical structure pursued in this system, Here is an overview of this architecture:

    - HTTP CLIENT: This is the entry point of our system. It is responsible for sending HTTP requests and receiving
      associated responses. The HTTP client communicates directly with the Spring Boot IOC container.
    - Spring Boot IOC Container: This is the core of our architecture. It is subdivided into three main layers.

        - Web layer,
        - Business layer,
        - DAO layer

## <a name="mvc"></a> 📎 MVC (Model-View-Controller) Pattern:

<p align="center">
<img src="public-doc/mvc.png">
</p>

### Overview:

- The architecture of this demo follows the MVC design pattern, separating the application into three main components:
  Model, View and Controller.

    - The Model represents the data and business logic.
    - The View handles the presentation layer.
    - The Controller manages user input and coordinates interactions between Model and Views.

## <a name="data-flow"></a> 📎 Data flow:

<p align="center">
<img src="public-doc/data_flow.jpg" align="center">
</p>

- The following diagram provides an overview of data management in our system, highlighting the transaction of
  entities to the database through various layers such as repositories, Spring Data JPA, Hibernate, and finally to the
  SGBD via JDBC, below is an overview of each of these components:

| Component         | Description                                                                                                                                                                                                                                                                                                                                                                                                   |
|-------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Entity**        | An entity represents a persistent object or a class that is stored in a database.                                                                                                                                                                                                                                                                                                                             |
| **Repository**    | A repository is a class or interface that acts as an intermediary layer between the application and the database. It is responsible for retrieving, storing and manipulating entities in the database.                                                                                                                                                                                                        |
| **JPARepository** | JPARepository is an interface provided by Spring Data JPA that extends the Spring Data Commons Repository interface and provides pre-defined CRUD methods for interacting with the database. It simplifies development by providing ready-to-use methods for common operations on entities.                                                                                                                   |
| **Spring Data**   | Spring Data is a project within the Spring Framework that provides an abstraction over data persistence technologies such as JPA. It simplifies development by providing features like automatic query generation, pagination, etc.                                                                                                                                                                           |
| **JPA**           | JPA is a Java specification that describes a standard programming interface for managing data persistence in Java applications. It provides annotations for mapping Java classes to database tables and APIs for performing CRUD operations on these classes.                                                                                                                                                 |
| **Hibernate**     | Hibernate is an open-source persistence framework that implements the JPA specification. It provides a reference implementation for JPA and offers additional features such as session management, query generation, transaction management, etc.                                                                                                                                                             |
| **JDBC**          | JDBC is a Java API that allows Java applications to interact with relational databases. it provides classes and interfaces for establishing a connection to the database, sending SQL queries, retrieving and manipulating results. In the context of Spring Boot, JDBC is used to communicate directly with the database when using raw SQL queries or when the features provided by JPA are not sufficient. |

## <a name="used-technologies"></a> 📎 Used technologies:

- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Bootstrap 5
- MySQL Database

## <a name="contacts"></a> 📎 Contacts:

- For any inquires or feedback regarding this project, please contact me on: spn.ahmed92@gmail.com.

<p align="right" style="font-size: x-large; font-weight: bold"> End </p>

---

<p align="end">
Author: Sopan Ahmed
</p>
