import java.io.Serializable;
import java.util.*;
import java.util.regex.*;
import java.time.LocalDate;

class Member implements Serializable {
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    public Member(String name, String email) {
        this.name = name;
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

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