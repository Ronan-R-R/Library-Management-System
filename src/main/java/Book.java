// Book class: Represents a book in the library
public class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;

    // Constructor to initialize book details
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true; // By default, the book is available
    }

    // Getter method to retrieve the title of the book
    public String getTitle() {
        return title;
    }

    // Getter method to retrieve the author of the book
    public String getAuthor() {
        return author;
    }

    // Getter method to retrieve the ISBN of the book
    public String getISBN() {
        return ISBN;
    }

    // Getter method to retrieve the availability status of the book
    public boolean isAvailable() {
        return isAvailable;
    }

    // Method to toggle the availability status of the book
    public void toggleAvailability() {
        isAvailable = !isAvailable;
    }
}
