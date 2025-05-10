# Fibank - Banking Operations Management System

## Overview

Fibank is a Spring Boot application designed to manage banking operations, specifically focusing on cash handling
operations performed by cashiers. The system allows for deposits and withdrawals with different currencies and
denominations, while maintaining accurate balance records and transaction history.

## Features

- **Cash Operations Management**: Support for deposit and withdrawal operations
- **Multi-Currency Support**: Handle different currencies in transactions
- **Denomination Tracking**: Track specific denominations for each transaction
- **Balance Management**: Real-time balance tracking for cashiers
- **Transaction History**: Maintain a detailed history of all transactions
- **API Security**: Basic API key authentication for secure access
- **Validation**: Comprehensive input validation including denomination verification

## Technical Stack

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Data JPA**: For database operations
- **Spring Web**: For REST API endpoints
- **Spring Validation**: For input validation
- **Liquibase**: For database schema management
- **H2 Database**: In-memory database for data storage
- **Lombok**: For reducing boilerplate code
- **Maven**: For dependency management and build

## Project Structure

The project follows a standard Spring Boot application structure with the following main packages:

- `com.fibank.balance`: Balance management components
- `com.fibank.cash`: Cash operation handling
- `com.fibank.cashier`: Cashier management
- `com.fibank.exception`: Exception handling
- `com.fibank.history`: Transaction history tracking
- `com.fibank.security`: API security components
- `com.fibank.util`: Utility classes

## API Endpoints

- **POST /cash-operation**: Perform deposit or withdrawal operations
- **GET /cash-balance**: Retrieve balance information with optional filtering by date range and cashier

## Setup and Installation

1. Ensure you have Java 17 installed
2. Clone the repository
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   java -jar target/fibank-0.0.1-SNAPSHOT.jar
   ```

## Database

The application uses an H2 in-memory database by default, configured through Liquibase for schema management. Initial
data is loaded through Liquibase changelog files.

## Security

The application implements basic API key authentication through a custom filter. Ensure the appropriate API key is
provided in requests.

## Transaction History

All transactions are logged to maintain an audit trail. The history includes details such as operation type, amount,
denominations, and timestamp.