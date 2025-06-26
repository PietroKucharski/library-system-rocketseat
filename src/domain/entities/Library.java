package domain.entities;

import domain.repositories.AuthorRepository;
import domain.repositories.BookRepository;
import domain.repositories.CustomerRepository;
import domain.services.LoanService;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final LoanService loanService;

    public Library(AuthorRepository authorRepository, BookRepository bookRepository, CustomerRepository customerRepository, LoanService loanService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.loanService = loanService;
    }

    public void insertAuthor(Author author) {
        authorRepository.save(author);
    }

    public void insertBook(Book book) {
        bookRepository.save(book);
    }

    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getAllAuthorBooks(String name) {
        return bookRepository.findBooksAuthor(name);
    }

    public void makeLoan(Customer customer, Book book) {
        loanService.makeLoan(customer, book);
    }

    public void returnLoan(Customer customer, Book book) {
        loanService.returnLoan(customer, book);
    }

    public List<Loan> getAllLoans() {
        return loanService.findAllLoans();
    }

    public List<Loan> getLoansByCustomer(Customer customer) {
        return loanService.findLoansByCustomer(customer);
    }

    public Loan getLoanByBookTitle(String title) {
        return loanService.findLoanByBookTitle(title);
    }
}
