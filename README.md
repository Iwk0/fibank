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

## Postman Collections

The project includes Postman collections to help you test the API endpoints:

- **Collection File**: `collection/Fibank.postman_collection.json`
- **Environment File**: `collection/Fibank env.postman_environment.json`

### Available Requests

1. **Withdraw Request**: Make a withdrawal operation
   - Method: POST
   - Endpoint: `/api/v1/cash-operation`
   - Authentication: API Key (FIB-X-AUTH header)

2. **Deposit Request**: Make a deposit operation
   - Method: POST
   - Endpoint: `/api/v1/cash-operation`
   - Authentication: API Key (FIB-X-AUTH header)

3. **Cash Balance Request**: Get balance information
   - Method: GET
   - Endpoint: `/api/v1/cash-balance`
   - Authentication: API Key (FIB-X-AUTH header)
   - Optional Query Parameters:
     - `cashier`: Filter by cashier name
     - `dateFrom`: Filter by start date (format: yyyy-MM-dd'T'HH:mm:ss)
     - `dateTo`: Filter by end date (format: yyyy-MM-dd'T'HH:mm:ss)

### Setup Instructions

1. Import both files into Postman
2. Set the `apiKey` variable in the environment with your API key
3. Use the collection to test the API endpoints

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
