//todo comments
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.*;

public class Main {
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData(); // Load data from files
        displayOverdueBooks(); // Check for overdue books on first boot
        //displayMenu();
        saveData(); // Save data to files before exiting
    }

    private static final String DATA_DIR = "./data/";

    private static void loadData() {
        try (ObjectInputStream bookInput = new ObjectInputStream(new FileInputStream(DATA_DIR + "books.dat"));
             ObjectInputStream memberInput = new ObjectInputStream(new FileInputStream(DATA_DIR + "members.dat"))) {
            books = (List<Book>) bookInput.readObject();
            members = (List<Member>) memberInput.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveData() {
        File dataDirectory = new File(DATA_DIR);
        if (!dataDirectory.exists()) {
            if (!dataDirectory.mkdirs()) {
                System.out.println("Error creating data directory.");
                return;
            }
        }

        try (ObjectOutputStream bookOutput = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "books.dat"));
             ObjectOutputStream memberOutput = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "members.dat"))) {
            bookOutput.writeObject(books);
            memberOutput.writeObject(members);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        int choice = -1; // Initialize choice to a default value
        do {
            try {
                System.out.println("\nLibrary Management System Menu:");
                System.out.println("==============================");
                System.out.println("1. Add a new book");
                System.out.println("2. Add a new member");
                System.out.println("3. Search for a book");
                System.out.println("4. Check out a book");
                System.out.println("5. Return a book");
                System.out.println("6. View Members");
                System.out.println("7. View Books");
                System.out.println("8. Manually Change Due Date (For Testing)");
                System.out.println("9. View Overdue and Due Books");
                System.out.println("0. Exit");
                System.out.println("==============================");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    // Handle each menu option
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
                        manuallyChangeDueDate();
                        break;
                    case 9:
                        viewOverdueAndDueBooks();
                        break;
                    case 0:
                        System.out.println("Exiting the program. Thank you!");
                        break; // Exit the program
                    default:
                        System.out.println("Error: Invalid choice. Please try again.");
                }

                // Prompt for continue or exit
                if (choice != 0) {
                    System.out.println("==============================");
                    System.out.println("Continue (1) | Exit (0)");
                    System.out.println("==============================");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice == 1); // Continue looping if choice is 1
    }

    private static void addNewBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();

        assert !title.isEmpty() : "Title cannot be empty";
        assert !author.isEmpty() : "Author name cannot be empty";
        assert isValidISBN(ISBN) : "Invalid ISBN format";

        Book newBook = new Book(title, author, ISBN);
        books.add(newBook);
        System.out.println("Book added successfully.");
    }

    private static void addNewMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member email: ");
        String email = scanner.nextLine();

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
                if (!book.isAvailable()) {
                    System.out.println("Due Date: " + book.getDueDate());
                }
            }
        }
    }

    private static void checkOutBook() {
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        Member member = findMemberByName(memberName);
        Book book = findBookByTitle(bookTitle);

        if (member == null || book == null) {
            System.out.println("Error: Member or book not found.");
            return;
        }

        try {
            member.borrowBook(book);
            book.setBorrower(member);
            System.out.println("Book checked out successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        Book book = findBookByTitle(bookTitle);

        if (book == null) {
            System.out.println("Error: Book not found.");
            return;
        }

        Member borrower = book.getBorrower();
        if (borrower != null) {
            borrower.returnBook(book);
            book.setBorrower(null);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Error: Book is not checked out.");
        }
    }

    private static void viewMembers() {
        System.out.println("Members:");
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (Member member : members) {
                System.out.println("------------------------------");
                System.out.println("Name: " + member.getName());
                System.out.println("Email: " + member.getEmail());
                System.out.println("Borrowed Books: " + member.getBorrowedBooks().size());
            }
        }
    }

    private static void viewBooks() {
        System.out.println("Books:");
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println("------------------------------");
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("ISBN: " + book.getISBN());
                System.out.println("Status: " + (book.isAvailable() ? "Available" : "Checked Out"));
                if (!book.isAvailable()) {
                    System.out.println("Due Date: " + book.getDueDate());
                }
            }
        }
    }

    private static void manuallyChangeDueDate() {
        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        Book book = findBookByTitle(bookTitle);
        if (book == null) {
            System.out.println("Error: Book not found.");
            return;
        }

        System.out.print("Enter new due date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        LocalDate newDueDate = LocalDate.parse(dateString);

        book.setDueDate(newDueDate);
        System.out.println("Due date updated successfully.");
    }

    private static void viewOverdueAndDueBooks() {
        System.out.println("Overdue and Due Books:");
        boolean foundBooks = false;
        for (Book book : books) {
            if (!book.isAvailable() && book.getDueDate().isBefore(LocalDate.now())) {
                long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now());
                double fine = daysOverdue * 5; // Assuming a fine of R5 per day for overdue books
                System.out.println("==============================");
                System.out.println("- Book: " + book.getTitle());
                System.out.println("- Borrower: " + book.getBorrower().getName());
                System.out.println("- Days Overdue: " + (daysOverdue == 0 ? "Today" : daysOverdue));
                System.out.println("- Fine: R" + fine);
                foundBooks = true;
            }
        }
        if (!foundBooks) {
            System.out.println("No overdue or due books found.");
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidISBN(String isbn) {
        // ISBN-10 or ISBN-13 validation regex
        String isbnRegex = "^(?:ISBN(?:-10)?:?\\s)?(?=[0-9X]{10}$|(?=(?:[0-9]+[-\\s]){3})" +
                "[-\\s0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[-\\s]){4})[-\\s0-9]{17}$)" +
                "(?:97[89][-\\s]?)?[0-9]{1,5}[-\\s]?[0-9]+[-\\s]?[0-9]+[-\\s]?[0-9X]$";
        Pattern pattern = Pattern.compile(isbnRegex);
        Matcher matcher = pattern.matcher(isbn);
        return matcher.matches();
    }

    private static Member findMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }

    private static Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    private static void displayOverdueBooks() {
        System.out.println("Overdue Books:");
        boolean foundOverdueBooks = false;
        for (Book book : books) {
            if (!book.isAvailable() && book.getDueDate().isBefore(LocalDate.now())) {
                long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now());
                double fine = daysOverdue * 5; // Assuming a fine of R5 per day for overdue books
                System.out.println("==============================");
                System.out.println("- Book: " + book.getTitle());
                System.out.println("- Borrower: " + book.getBorrower().getName());
                System.out.println("- Days Overdue: " + (daysOverdue == 0 ? "Today" : daysOverdue));
                System.out.println("- Fine: R" + fine);
                foundOverdueBooks = true;
            }
        }
        if (!foundOverdueBooks) {
            System.out.println("No overdue books found.");
        }

        // Prompt for continue or exit
        System.out.println("==============================");
        System.out.println("Continue (1) | Exit (0)");
        System.out.println("==============================");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (choice == 1) {
            displayMenu(); // Continue to the main menu
        } else {
            System.out.println("Exiting the program. Thank you!");
        }
    }
}
