T.EN Banking System
Overview

T.EN Banking is a Java-based client-server banking application that allows users to create accounts, log in using a PIN, check their balance, deposit, withdraw, and view statements. The application uses SQLite for persistence and supports multiple clients connecting to the server.

Features:
User registration with full name, date of birth, and PIN.
User login using PIN with 3-attempt lockout.
Persistent account balance stored in SQLite database.
Deposit, withdraw, and balance check functionality.
Simple command-line client interface.

Setup & Run
Prerequisites

Java 17+ installed
Maven (if using Maven for dependencies)
SQLite (optional for viewing database)

Running the Server

Open terminal in project directory.
Compile and run the server:

Running the Client

Open another terminal in the project directory.
Compile and run the client:

Database

The application uses SQLite with a clients table.
Schema:
clients (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    full_name TEXT NOT NULL,
    date_of_birth TEXT NOT NULL,
    pin INTEGER NOT NULL
);

Future Improvements

Implement REST API using Spring Boot.
Hash PINs for security.
Add transaction history and statements.
Build a GUI or web client.
