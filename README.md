\# PROG5121 Part 1 – Chat Application



\## Project Overview



This project is a Java-based chat application registration login developed for PROG5121 Part 1. The application focuses on user registration and login functionality.



\## Features



\* User registration

\* Username validation

\* Password complexity validation

\* South African cellphone number validation

\* User login

\* Login status messages

\* JUnit testing of the login functionality



\## Technologies Used



\* Java

\* NetBeans IDE

\* JUnit 4

\* Git and GitHub



\## Project Structure



\* `src/` – Contains the Java source code.

\* `test/` – Contains the JUnit test class.

\* `nbproject/` – Contains the NetBeans project configuration.

\* `build.xml` – NetBeans build configuration.

\* `.gitignore` – Specifies files and folders that should not be uploaded to GitHub.



\## Testing



The project includes JUnit 4 tests for the login functionality, including successful and unsuccessful username, password, cellphone number, registration, and login scenarios.


## Part 2: QuickChat Messaging Application

### 1. Project Overview

Part 2 of the PROG5121 Programming 1A project builds on the previous work by implementing message-processing functionality for the QuickChat application. The application allows users to create messages, validate message details, choose how messages should be processed, and store messages for later reference.

The project was developed using Java in NetBeans, with JUnit tests used to verify the functionality of the message-processing features.

### 2. Features Implemented

The main features implemented in Part 2 include:

- **Message ID generation and validation:** Generates a unique-looking 10-digit message ID and checks whether the ID follows the required format.
- **Recipient cellphone validation:** Validates recipient cellphone numbers against the application's required South African international format.
- **Message length validation:** Ensures that messages are not empty and do not exceed 250 characters.
- **Message hash generation:** Generates a message hash using the first two digits of the message ID, the message number, and the first and last words of the message.
- **Message processing:** Allows users to send, disregard, or store messages.
- **Sent message tracking:** Keeps track of successfully sent messages and maintains a total sent-message count.
- **Message storage:** Saves stored message details to a JSON file named `messages.json`.
- **Message display:** Provides functionality to display the messages that have been sent.

### 3. Message Processing Options

The application provides three message-processing choices:

1. **Send:** Marks the message as sent and adds it to the sent-message collection.
2. **Disregard:** Marks the message as disregarded.
3. **Store:** Marks the message as stored and saves its details to `messages.json`.

The application also handles invalid processing choices by returning an appropriate response without marking the message as sent.

### 4. Data Storage

Stored messages are saved in `messages.json` in JSON format. Each stored message contains the following information:

- Message ID
- Message number
- Recipient cellphone number
- Message text
- Message hash
- Message status

This file allows the stored message information to be retained outside the application's in-memory collections.

### 5. Testing

JUnit 4 was used to test the message-processing functionality. The test suite contains 17 tests covering:

\## Author

Kgauhelo Senatle

