package domain.application;

import domain.entities.Author;
import domain.entities.Book;
import domain.entities.Customer;
import domain.entities.Library;
import domain.exceptions.LoanAlreadyMade;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws LoanAlreadyMade {
        Library library = new Library();
        Customer customer = new Customer("Pietro", "pietro@email.com", LocalDate.of(2002, 6, 13));

        Author author1 = new Author("Carlos Drummond", LocalDate.of(1902, 10, 31));
        Book book1 = new Book("A Rosa do Povo", author1, LocalDate.of(1902, 10, 31));

        Author author2 = new Author("Clarice Lispector", LocalDate.of(1920, 12, 10));
        Book book2 = new Book("A Hora da Estrela", author2, LocalDate.of(1902, 10, 31));

        Author author3 = new Author("George Orwell", LocalDate.of(1903, 6, 25));
        Book book3 = new Book("1984", author3, LocalDate.of(1902, 10, 31));

        library.addAllBook(Arrays.asList(book1, book2, book3));
        library.addAllAuthor(Arrays.asList(author1, author2, author3));
        library.addCustomer(customer);

        library.addLoan(customer, book1);
        library.addLoan(customer, book2);

        /* Métodos dos livros */
//        System.out.println(library.getAllBooks());
//        System.out.println(library.getBookByTitle("teste"));

        /* Métodos dos autores */
//        System.out.println(library.getAllAuthors());

        /* Métodos dos clientes */
//        System.out.println(library.getAllCustomers());

        /* Métodos dos empréstimos */
        System.out.println(library.getAllLoans());
        library.returnLoan("pietro@email.com", "A Rosa do Povo");
        System.out.println("--------------------");
        System.out.println(library.getAllLoans());

//        System.out.println(library.getLoanByCustomer(customer.getEmail()));
    }
}
