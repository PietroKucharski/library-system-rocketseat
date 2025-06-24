package domain.entities;

import domain.exceptions.LoanAlreadyMade;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private List<Book> books;
    private List<Author> authors;
    private Map<Customer, Loan> loans;
    private List<Customer> customers;

    public Library() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.loans = new HashMap<>();
        this.customers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addAllBook(List<Book> allBooks) {
        books.addAll(allBooks);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addAllAuthor(List<Author> allAuthors) {
        authors.addAll(allAuthors);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addLoan(Customer customer, Book book) throws LoanAlreadyMade {
        if (!book.getIsAvailable()) {
            throw new LoanAlreadyMade("Operação inválida! Livro já foi emprestado");
        } else {
            book.setIsAvailable(false);
            loans.put(customer, new Loan(customer, book, LocalDate.now()));
        }
    }

    public List<Book> getAllBooks() {
        return books.stream().toList();
    }

    public List<Author> getAllAuthors() {
        return authors.stream().toList();
    }

    public List<Customer> getAllCustomers() {
        return customers.stream().toList();
    }

    public List<String> getAllLoans() {
        return loans.entrySet()
                .stream()
                .map(entry -> String.format("\n Cliente: %s | \n %s", entry.getKey(), entry.getValue()))
                .toList();
    }

    public Loan getLoanByCustomer(UUID customerId) {
        return loans.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getId().equals(customerId))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public Book getBookByTitle(String bookTitle) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(bookTitle.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public Book getBookByAuthor(String authorName) {
        return books.stream()
                .filter(book -> book.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
