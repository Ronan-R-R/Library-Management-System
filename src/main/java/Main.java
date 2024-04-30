import java.util.*;

// Private static fields to store books, members, and scanner object
public class Main {
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Main method: Start of the program
    public static void main(String[] args) {
        // Add sample data to books and members lists
        addSampleData();
        // Display the main menu
        displayMenu();
    }

    // Method to add sample data to the books and members lists
    private static void addSampleData() {
        // Adding sample books
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084"));
        books.add(new Book("1984", "George Orwell", "9780451524935"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "9780141439518"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769488"));

        // Adding sample members
        members.add(new Member("Alice Johnson", "alice@gmail.com"));
        members.add(new Member("Bob Smith", "bob@gmail.com"));
        members.add(new Member("Charlie Brown", "charlie@gmail.com"));
    }

    // Method to display the main menu and handle user input
    private static void displayMenu() {
        // Variable to store user's choice
        int choice;
        do {
            // Display the main menu options
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("==============================");
            System.out.println("1. Add a new book");
            System.out.println("2. Add a new member");
            System.out.println("3. Search for a book");
            System.out.println("4. Check out a book");
            System.out.println("5. Return a book");
            System.out.println("6. View Members");
            System.out.println("7. View Books");
            System.out.println("8. Exit");
            System.out.println("==============================");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            try {
                switch (choice) {
                    case 1:
                        addNewBook();
                        break;
                    case 2:
                        addNewMember();
                        break;
                    case 3:
                        searchForBook();
                        break;
                    case 4:
                        checkOutBook();
                        break;
                    case 5:
                        returnBook();
                        break;
                    case 6:
                        viewMembers();
                        break;
                    case 7:
                        viewBooks();
                        break;
                    case 8:
                        System.out.println("Exiting the program. Thank you!");
                        return; // Exit the program
                    default:
                        throw new IllegalArgumentException("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Prompt user to return to the main menu or exit
            int option;
            do {
                System.out.print("Press 0 to return to the main menu, or 1 to exit: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (option != 0 && option != 1) {
                    System.out.println("Error: Invalid option. Please try again.");
                }
            } while (option != 0 && option != 1);

            if (option == 1) {
                System.out.println("Exiting the program. Thank you!");
                return; // Exit the program
            }
        } while (true); // Continue looping until user chooses to exit
    }

    // Method to add a new member to the library
    private static void addNewBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();

        // Input validation
        assert !title.isEmpty() : "Title cannot be empty";
        assert !author.isEmpty() : "Author name cannot be empty";
        assert isValidISBN(ISBN) : "Invalid ISBN format";

        Book newBook = new Book(title, author, ISBN);
        books.add(newBook);
        System.out.println("Book added successfully.");
    }

    // Method to add a new member to the library
    private static void addNewMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member email: ");
        String email = scanner.nextLine();

        // Input validation
        assert !name.isEmpty() : "Name cannot be empty";
        assert isValidEmail(email) : "Invalid email format";

        try {
            Member newMember = new Member(name, email);
            members.add(newMember);
            System.out.println("Member added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to search for books in the library
    private static void searchForBook() {
        System.out.print("Enter search query (title or author): ");
        String query = scanner.nextLine().toLowerCase();

        List<Book> matchedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query) || book.getAuthor().toLowerCase().contains(query)) {
                matchedBooks.add(book);
            }
        }

        if (matchedBooks.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            System.out.println("Matching books:");
            for (Book book : matchedBooks) {
                System.out.println("------------------------------");
                System.out.println(book.getTitle() + " by " + book.getAuthor());
                System.out.println("ISBN: " + book.getISBN());
                System.out.println("Status: " + (book.isAvailable() ? "Available" : "Checked Out"));
            }
        }
    }

    // Method to check out a book from the library
    private static void checkOutBook() {
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        Member member = findMemberByName(memberName);
        Book book = findBookByTitle(bookTitle);

        if (member == null || book == null) {
            System.out.println("Member or book not found.");
            return;
        }

        try {
            member.borrowBook(book);
            System.out.println("Book checked out successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to return a book to the library
    private static void returnBook() {
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        Member member = findMemberByName(memberName);
        Book book = findBookByTitle(bookTitle);

        if (member == null || book == null) {
            System.out.println("Member or book not found.");
            return;
        }

        try {
            member.returnBook(book);
            System.out.println("Book returned successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to view a list of all members in the library
    private static void viewMembers() {
        System.out.println("\nMembers:");
        for (Member member : members) {
            System.out.println("------------------------------");
            System.out.println("- Name: " + member.getName());
            System.out.println("- Email: " + member.getEmail());
            List<Book> borrowedBooks = member.getBorrowedBooks();
            if (!borrowedBooks.isEmpty()) {
                System.out.println("- Booked Books:");
                for (Book book : borrowedBooks) {
                    System.out.println("    - " + book.getTitle());
                }
            } else {
                System.out.println("- No books booked out.");
            }
        }
    }

    // Method to view a list of all books in the library
    private static void viewBooks() {
        System.out.println("\nBooks:");
        for (Book book : books) {
            System.out.println("------------------------------");
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            System.out.println("- ISBN: " + book.getISBN());
            System.out.println("- Status: " + (book.isAvailable() ? "Available" : "Checked Out"));
        }
    }

    // Method to find a member by name in the members list
    private static Member findMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }

    // Method to find a book by title in the books list
    private static Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Method to validate the format of an email address
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // Method to validate the format of an ISBN
    private static boolean isValidISBN(String ISBN) {
        String ISBNRegex = "^\\d{13}$";
        return ISBN.matches(ISBNRegex);
    }
}