package domain.application;

import domain.entities.Library;
import domain.repositories.AuthorRepository;
import domain.repositories.BookRepository;
import domain.repositories.CustomerRepository;
import domain.repositories.LoanRepository;
import domain.services.LoanService;
import infrastructure.repositories.InMemoryAuthorRepository;
import infrastructure.repositories.InMemoryBookRepository;
import infrastructure.repositories.InMemoryCustomerRepository;
import infrastructure.repositories.InMemoryLoansRepository;

public class LibraryMenuFactory {
    public Library createLibrary() {
        AuthorRepository authorRepository = new InMemoryAuthorRepository();
        BookRepository bookRepository = new InMemoryBookRepository();
        LoanRepository loanRepository = new InMemoryLoansRepository();
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        LoanService loanService = new LoanService(loanRepository);

        return new Library(authorRepository, bookRepository, customerRepository, loanService);
    }
}
