# Quiz Application

A secure Quiz Management System built with **Spring Boot**, **Spring MVC**, **Spring Security**, and **Thymeleaf**.

## Features

- User registration and login with role-based access control
- **Admin role**: Add, edit, and delete quiz questions
- **User role**: Attempt quizzes with multiple-choice options
- Automatic score calculation upon quiz submission
- Secure password encryption using BCrypt
- In-memory data management for users and quiz questions

## Tech Stack

- Java 21
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf
- Maven

## How It Works

1. Users register with a username, password, and role (Admin/User)
2. Admins are redirected to the Quiz List page to manage questions
3. Users are redirected to the Quiz page to attempt questions
4. Upon submission, users are shown their score on the Results page

## Project Structure

src/main/java/com/quiz/quizapp/
├── config/ → Spring Security configuration
├── controller/ → Handles HTTP requests
├── model/ → Quiz and User entities
└── service/ → Business logic for quizzes and users


## Running the Project

```bash
mvn clean package
java -jar target/quizapp-0.0.1-SNAPSHOT.jar
```

Then visit `http://localhost:8080/`

## Author

Chitra Joshi
