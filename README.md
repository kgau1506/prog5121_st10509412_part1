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

# PROG5121 – QuickChat Application (Part 3)

## 1. Project Overview

QuickChat is a Java-based messaging application developed as part of the PROG5121 programming module. The application allows users to register, log in, send messages, disregard messages and store messages for later access.

Part 3 extends the functionality of the application by introducing stored-message management features. These features allow users to view, search, delete and generate reports about messages saved in a JSON file.

## 2. Part 3 Features

The following features were implemented:

- **Populate Arrays:** Message information is organised into arrays to manage sent, disregarded and stored messages, together with message IDs and hashes.
- **Display Longest Message:** Searches stored messages and displays the longest message with its related details.
- **Search Messages by Recipient:** Searches for messages sent to a specific recipient and displays the matching message information.
- **Delete Message Using Message Hash:** Finds a stored message using its unique message hash and removes it from the JSON file.
- **Read JSON File:** Reads previously stored messages from `messages.json` and loads their information into arrays.
- **Display Stored Messages:** Displays stored message details, including the sender, recipient, message ID, hash, message text and status.
- **Full Message Report:** Generates a report containing the details of all stored messages.

## 3. Files and Classes

| File | Description |
|---|---|
| `ST10509412_part1.java` | Main class that manages the application menu and connects the different features. |
| `sendMessage.java` | Handles message creation, validation, sending, disregarding and storing. |
| `storedData.java` | Handles stored-message arrays, JSON reading, searching, deletion and reporting. |
| `storedDataTest.java` | Contains unit tests for the Part 3 stored-message functionality. |
| `messages.json` | Stores message information in JSON format. |

## 4. How to Run the Application

1. Clone or download the project from the GitHub repository.
2. Open the project in NetBeans.
3. Ensure that Java 21 is installed.
4. Ensure that the `json-simple-1.1.jar` library is included in the project.
5. Locate and run `ST10509412_part1.java`.
6. Register a user or log in using the available login functionality.
7. Use the QuickChat menu to send messages or access stored-message features.

## 5. Stored Messages Menu

The Stored Messages menu provides the following options:

1. Display sender and recipient details.
2. Display the longest message.
3. Search for a message using its ID.
4. Search for messages using a recipient.
5. Delete a message using its hash.
6. Display the full message report.
7. Return to the main menu.

## 6. Unit Testing

Unit tests were created to verify the functionality of the Part 3 features.

The tests cover:
- Displaying details of the longest stored message.
- Searching for messages belonging to a particular recipient.
- Ensuring unrelated recipient messages are excluded.
- Deleting a message using its hash while retaining other messages.
- Reading multiple stored messages from a JSON file.
- Displaying stored message details.
- Handling older stored messages that do not contain sender information.

**Latest local test result:** 22 tests passed, with 0 failures.

## 7. Automated Testing with GitHub Actions

GitHub Actions was configured to automatically build the project and execute the unit tests.

The workflow:
- Runs when changes are pushed to the `KhanbanTasks` branch.
- Runs when a pull request targets the `KhanbanTasks` branch.
- Uses Java 21 (Temurin).
- Downloads the required JUnit 4.13.2 and Hamcrest dependencies.
- Executes the Ant `clean test` command.

This helps verify that the project builds and that the automated tests pass.

## 8. GitHub Version Control

GitHub was used to manage the development of the application. Meaningful commits were made to record the implementation and testing of the Part 3 functionality.

The Part 3 work includes six additional commits covering:
- Longest-message testing.
- Recipient-search testing.
- Message-deletion testing.
- JSON-reading testing.
- Stored-message display testing.
- Compatibility with older stored messages.

## 9. Conclusion

Part 3 extends the QuickChat application by introducing additional functionality for managing stored messages. Users can access message information, search for messages, identify the longest stored message, delete messages using their hashes and generate a full message report.

Unit testing and GitHub Actions were used to test the application and support the development process.

\## Author

Kgauhelo Senatle

