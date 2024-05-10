import java.io.Serializable;
import java.util.*;
import java.util.regex.*;
import java.time.LocalDate;

/**
 * Represents a member of the library.
 */
class Member implements Serializable {
    private String name; // Name of the member
    private String email; // Email address of the member
    private List<Book> borrowedBooks; // List of books borrowed by the member

    /**
     * Constructor to initialize a member with name and email.
     * @param name Name of the member.
     * @param email Email address of the member.
     * @throws IllegalArgumentException if the email format is invalid.
     */
    public Member(String name, String email) {
        this.name = name;
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.borrowedBooks = new ArrayList<>();
    }

    /**
     * Retrieves the name of the member.
     * @return The name of the member.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the email address of the member.
     * @return The email address of the member.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the list of books borrowed by the member.
     * @return The list of books borrowed by the member.
     */
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Validates the format of an email address.
     * @param email The email address to validate.
     * @return True if the email address format is valid, false otherwise.
     */
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Borrows a book for the member.
     * @param book The book to be borrowed.
     * @throws IllegalStateException if the book is not available for checkout.
     */
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.toggleAvailability();
            // Set due date as 14 days from now
            book.setDueDate(LocalDate.now().plusDays(14));
        } else {
            throw new IllegalStateException("Book is not available for checkout.");
        }
    }

    /**
     * Returns a book borrowed by the member.
     * @param book The book to be returned.
     * @throws IllegalStateException if the book is not borrowed by this member.
     */
    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.toggleAvailability();
            // Reset due date when book is returned
            book.setDueDate(null);
        } else {
            throw new IllegalStateException("Book is not borrowed by this member.");
        }
    }
}