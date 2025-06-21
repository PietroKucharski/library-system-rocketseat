package domain.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }

    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors);
    }

    public void addLoan(String username, Book book) {
        if (book.getIsAvailable() == false) {
            System.out.println(book + " já emprestado");
        } else {
            book.setIsAvailable(false);
            loans.add(new Loan(username, book, LocalDate.now()));
        }
    }
}
