package domain.entities;

import domain.exceptions.LoanAlreadyMade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Book> getAllBooks() {
        return books.stream().toList();
    }

    public Map<Customer, Loan> getAllLoans() {
        loans.entrySet().stream()
                .map(entry -> String.format("Cliente: %s | Empréstimo: %s", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

        return loans;
    }

    public List<Author> getAllAuthors() {
        return authors.stream().toList();
    }

    public void addLoan(Customer customer, Book book) throws LoanAlreadyMade {
        if (!book.getIsAvailable()) {
            throw new LoanAlreadyMade("Operação inválida! Livro já foi emprestado");
        } else {
            book.setIsAvailable(false);
            loans.put(customer, new Loan(customer, book, LocalDate.now()));
        }
    }
}
