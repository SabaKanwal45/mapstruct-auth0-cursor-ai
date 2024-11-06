# MapStruct Demo Project

A Spring Boot application demonstrating the use of MapStruct for DTO mapping with a book management system.

## Project Overview

This project implements a book management system with authors and users. It demonstrates:
- DTO pattern implementation using MapStruct
- JPA entity relationships
- RESTful API design
- Flyway database migrations

## Technology Stack

- Java 17
- Spring Boot 3.x
- MySQL 8.0
- MapStruct
- Flyway
- Lombok
- Maven

## Database Schema

The application uses the following entities:
- `Book`: Stores book information
- `Author`: Stores author details
- `User`: Stores user information
- Many-to-Many relationships between:
  - Books and Authors
  - Books and Users

## API Endpoints

### Books
- GET    /api/books          - Get all books
- GET    /api/books/{id}     - Get book by ID
- POST   /api/books          - Create new book
- PUT    /api/books/{id}     - Update book
- DELETE /api/books/{id}     - Delete book

### Authors
- GET    /api/authors                           - Get all authors
- GET    /api/authors/{id}                      - Get author by ID
- POST   /api/authors                           - Create new author
- PUT    /api/authors/{id}                      - Update author
- DELETE /api/authors/{id}                      - Delete author
- POST   /api/authors/{authorId}/books/{bookId} - Add book to author
- DELETE /api/authors/{authorId}/books/{bookId} - Remove book from author

### Users
- GET    /api/users          - Get all users
- GET    /api/users/{id}     - Get user by ID
- POST   /api/users          - Create new user
- PUT    /api/users/{id}     - Update user
- DELETE /api/users/{id}     - Delete user

## DTO Structure

### Book DTOs
- `BookDto`: Full book details with authors
- `BookSlimDto`: Minimal book information

### Author DTOs
- `AuthorDto`: Author details
- `AuthorAllDto`: Author with book list
- `AuthorSlimDto`: Basic author information

### User DTOs
- `UserGetDto`: User information for responses
- `UserPostDto`: User data for creation/updates
