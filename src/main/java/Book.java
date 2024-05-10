import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a book in the library.
 */
class Book implements Serializable {
    private String title; // Title of the book
    private String author; // Author of the book
    private String ISBN; // ISBN of the book
    private boolean isAvailable; // Availability status of the book
    private Member borrower; // Member who has borrowed the book
    private LocalDate dueDate; // Due date for returning the book

    /**
     * Constructor to initialize a book with title, author, and ISBN.
     * @param title Title of the book.
     * @param author Author of the book.
     * @param ISBN ISBN of the book.
     */
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true; // By default, the book is available
    }

    /**
     * Retrieves the title of the book.
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the author of the book.
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Retrieves the ISBN of the book.
     * @return The ISBN of the book.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Checks if the book is available.
     * @return True if the book is available, false otherwise.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Toggles the availability status of the book.
     */
    public void toggleAvailability() {
        isAvailable = !isAvailable;
    }

    /**
     * Retrieves the member who has borrowed the book.
     * @return The member who has borrowed the book.
     */
    public Member getBorrower() {
        return borrower;
    }

    /**
     * Sets the borrower of the book.
     * @param borrower The member who borrows the book.
     */
    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    /**
     * Retrieves the due date for returning the book.
     * @return The due date for returning the book.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for returning the book.
     * @param dueDate The due date for returning the book.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
