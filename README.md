# 📚 LiterAlura - Book Management with Gutendex API

LiterAlura is a **Java Spring Boot** project that consumes data from the [Gutendex API](https://gutendex.com/), stores it in a **PostgreSQL** database, and allows users to interact with the data through a powerful **command-line interface**.

This application is part of a personal challenge to improve backend skills using Java, Spring Boot, JPA, and API integration — with a real-world use case around **books and authors**.

---

## 🚀 Technologies & Tools

| Stack           | Techs Used                                    |
|-----------------|-----------------------------------------------|
| 💻 Backend      | Java 17+, Spring Boot                         |
| 🗃️ Persistence  | Spring Data JPA, Hibernate                    |
| 🧠 Querying     | JPQL (Java Persistence Query Language)        |
| 🌐 API Client   | Custom HTTP request using `java.net`          |
| 🐘 Database     | PostgreSQL                                    |
| 🔄 JSON Mapper  | Jackson                                       |

---

## 📦 Project Overview

The app connects to **Gutendex**, an open API for public domain books, fetches book and author data, saves it into a local PostgreSQL database, and provides users with multiple query and manipulation features — all through a console menu.

The goal is not only to manage data, but also to **practice backend development concepts** such as:

- DTO to Entity conversion  
- Repository and Service layers  
- Custom JPQL queries  
- API data validation  
- One-to-many relationships  
- Query optimization  

---

## 🛠️ How to Run

To get this project running locally, follow the steps below:


# 1. Clone the repository
    ```bash
    git clone https://github.com/your-username/literalura.git
    cd literalura

# 2. Create a PostgreSQL database
# (Access your PostgreSQL CLI or a client like pgAdmin and run:)
# CREATE DATABASE literalura;

# 3. Configure the application properties
# Open src/main/resources/application.properties and update with your DB credentials:
    ```bash
    # spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
    # spring.datasource.username=yourUsername
    # spring.datasource.password=yourPassword
    # spring.jpa.hibernate.ddl-auto=update
    # spring.jpa.show-sql=true

# 4. Run the application

# If you're using Maven Wrapper (recommended):
    ```bash
    ./mvnw spring-boot:run

# Or if Maven is installed globally:
    ```bash
    mvn spring-boot:run

# 5. Use the console
# After starting, the application will show a menu in your terminal where you can:
```bash
    # - Search books from the Gutendex API
    # - Save and list books and authors
    # - Query by language, year, downloads, etc.

