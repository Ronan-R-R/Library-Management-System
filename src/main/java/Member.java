import java.util.*;
import java.util.regex.*;

// Member class: Represents a member of the library
public class Member {
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    // Constructor to initialize member details
    public Member(String name, String email) {
        this.name = name;
        // Validate the email format
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.borrowedBooks = new ArrayList<>();
    }

    // Getter method to retrieve the name of the member
    public String getName() {
        return name;
    }

    // Getter method to retrieve the email of the member
    public String getEmail() {
        return email;
    }

    // Getter method to retrieve the list of borrowed books by the member
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Method to validate the format of an email address
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to allow the member to borrow a book
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.toggleAvailability();
        } else {
            throw new IllegalStateException("Book is not available for checkout.");
        }
    }

    // Method to allow the member to return a book
    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.toggleAvailability();
        } else {
            throw new IllegalStateException("Book is not borrowed by this member.");
        }
    }
}
