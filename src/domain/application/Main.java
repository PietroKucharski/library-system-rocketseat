package domain.application;

import domain.entities.Author;
import domain.entities.Book;
import domain.entities.Customer;
import domain.entities.Library;
import domain.exceptions.LoanAlreadyMade;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws LoanAlreadyMade {
//        System.out.println("--------------");
//        System.out.println("Bem Vindo a Livraria Bla bla");
//        System.out.println("Gostaria de ver os livros disponíveis? (S/N)");
        Library library = new Library();
        Customer teste = new Customer("Teste", "teste@email.com", LocalDate.of(2002, 6, 13));

        Author author1 = new Author("Carlos Drummond", LocalDate.of(1902, 10, 31));
        Book book1 = new Book("A Rosa do Povo", author1, LocalDate.of(1902, 10, 31));

        Author author2 = new Author("Clarice Lispector", LocalDate.of(1920, 12, 10));
        Book book2 = new Book("A Hora da Estrela", author2, LocalDate.of(1902, 10, 31));

        Author author3 = new Author("George Orwell", LocalDate.of(1903, 6, 25));
        Book book3 = new Book("1984", author3, LocalDate.of(1902, 10, 31));

        library.addBook(book1);
        library.addAuthor(author1);

        library.addBook(book2);
        library.addAuthor(author2);

        library.addBook(book3);
        library.addAuthor(author3);

        library.addLoan(teste, book1);

//        System.out.println(library.getAllBooks());
//        System.out.println(library.getAllAuthors());
        System.out.println(library.getAllLoans());
    }
}
