# Library Management System

## Introduction

The Following project was created with a basic understanding of java, currently a console app

This is a simple command-line Library Management System implemented in Java. It allows users to manage books and members of a library. The system provides functionalities such as adding new books, adding new members, searching for books by title or author, checking out books, returning books, and viewing lists of books and members.

## Setup

To set up the Library Management System on your own system, follow these steps:

1. **Install Java**: Ensure you have Java installed on your system. You can download and install Java from the [official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. **Download the Source Code**: Download the source code files (`Main.java`, `Book.java`, `Member.java`) from the repository.

3. **Compile the Code**: Compile the source code files using a Java compiler. You can use command-line tools like `javac` or IDEs like IntelliJ IDEA, Eclipse, or NetBeans.

4. **Run the Program**: Once the code is compiled successfully, run the program by executing the `Main` class. You can do this from the command line using the `java` command or directly from your IDE.

## Features

- **Add New Book**: Librarians can add new books to the library's inventory by providing the title, author, and ISBN of the book.
- **Add New Member**: Librarians can add new members to the library by providing their name and email address.
- **Search for a Book**: Users can search for books in the library's inventory by title or author.
- **Check Out a Book**: Librarians can check out books to members, marking them as unavailable until they are returned.
- **Return a Book**: Members can return books they have borrowed, marking them as available for other members to borrow.
- **View Members**: Librarians can view a list of all library members along with their information and the number of books they have borrowed.
- **View Books**: Librarians can view a list of all books in the library's inventory along with their availability status.
- **Manually Change Due Date**: For testing purposes, librarians can manually change the due date of a book that has been checked out.
- **View Overdue and Due Books**: Librarians can view a list of overdue and due books along with information about the borrower, days overdue, and any applicable fines.

## Note

- **Case Sensitivity**: The search functionality for books is case-sensitive. Ensure to enter titles or author names with the correct casing when performing searches, this excludes the search.
- **Reusability**: simple console app, too impractical for real world use.
- **Real World Use**: Not practical or realistic to use, simple app to learn and practice.
- **Data file**: Do not mess with the data file, noticed it just leads to breaking the code and will require you to complete delete the .dat files and remake them. 