# Library Management System

## Introduction

The Following project was created with a basic understanding of java, currently a console app to be upgraded with simple GUI and to make use of SQLite, these feature will be implemented or alternatives to be found in the future

This is a simple command-line Library Management System implemented in Java. It allows users to manage books and members of a library. The system provides functionalities such as adding new books, adding new members, searching for books by title or author, checking out books, returning books, and viewing lists of books and members.

## Setup

To set up the Library Management System on your own system, follow these steps:

1. **Install Java**: Ensure you have Java installed on your system. You can download and install Java from the [official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. **Download the Source Code**: Download the source code files (`Main.java`, `Book.java`, `Member.java`) from the repository.

3. **Compile the Code**: Compile the source code files using a Java compiler. You can use command-line tools like `javac` or IDEs like IntelliJ IDEA, Eclipse, or NetBeans.

4. **Run the Program**: Once the code is compiled successfully, run the program by executing the `Main` class. You can do this from the command line using the `java` command or directly from your IDE.

## Features

- **Add New Book**: Add new books to the library collection by providing the title, author name, and ISBN.

- **Add New Member**: Register new members to the library by providing their name and email address.

- **Search for Book**: Search for books in the library by title or author. The search is case-sensitive.

- **Check Out Book**: Allow members to check out books from the library. Books must be available to be checked out.

- **Return Book**: Allow members to return books to the library.

- **View Members**: Display a list of all registered members, along with their names, email addresses, and the books they have currently borrowed.

- **View Books**: Display a list of all books in the library collection, along with their titles, authors, ISBNs, and availability status (available or checked out).

## Note

- **Case Sensitivity**: The search functionality for books is case-sensitive. Ensure to enter titles or author names with the correct casing when performing searches, this excludes the search.
- **Reusability**: in the current version it's simply a basic console app with no form of saving items, this is to be made possible with a future update.
- **Real World Use**: Not practical or realistic to use, simple app to learn and practice.