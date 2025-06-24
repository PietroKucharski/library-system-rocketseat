package domain.entities;

import domain.exceptions.BookNotFoundException;
import domain.exceptions.LoanAlreadyMade;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private List<Book> books;
    private List<Author> authors;
    private Map<Customer, List<Loan>> loans;
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
            loans.computeIfAbsent(customer, k -> new ArrayList<>()).add(new Loan(customer, book, LocalDate.now()));
        }
    }

    public void returnLoan(String customerEmail, String bookTitle) {
        loans.entrySet().stream()
                .filter(entry -> entry.getKey().getEmail().equalsIgnoreCase(customerEmail))
                .findFirst()
                .ifPresent(entry -> {
                    List<Loan> loanList = entry.getValue();

                    Loan loanToReturn = loanList.stream()
                            .filter(loan -> loan.getBook().getTitle().equalsIgnoreCase(bookTitle))
                            .findFirst()
                            .orElse(null);

                    if (loanToReturn != null) {
                        loanToReturn.getBook().setIsAvailable(true);
                        loanToReturn.setLoanReturnDate(LocalDate.now());
                    }
                });
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

    public List<Loan> getLoanByCustomer(String customerEmail) {
        return loans.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getEmail().equalsIgnoreCase(customerEmail))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(List.of());
    }

    public List<Loan> getLoanByBookTitle(String bookTitle) {
        return loans.values().stream()
                .flatMap(List::stream) //
                .filter(loan -> loan.getBook().getTitle().equalsIgnoreCase(bookTitle)).toList();
    }

    public Book getBookByTitle(String bookTitle) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(bookTitle.toLowerCase()))
                .findFirst()
                .orElseThrow(BookNotFoundException::new);
    }

    public Book getBookByAuthor(String authorName) {
        return books.stream()
                .filter(book -> book.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
