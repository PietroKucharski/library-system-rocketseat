package domain.application;

import domain.entities.Author;
import domain.entities.Book;
import domain.entities.Library;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        System.out.println("--------------");
//        System.out.println("Bem Vindo a Livraria Bla bla");
//        System.out.println("Gostaria de ver os livros disponíveis? (S/N)");
        Library library = new Library();

        Author author1 = new Author("Carlos Drummond", LocalDate.of(1902, 10, 31));
        Book book1 = new Book("A Rosa do Povo", author1);

        Author author2 = new Author("Clarice Lispector", LocalDate.of(1920, 12, 10));
        Book book2 = new Book("A Hora da Estrela", author2);

        Author author3 = new Author("George Orwell", LocalDate.of(1903, 6, 25));
        Book book3 = new Book("1984", author3);

        library.addBook(book1);
        library.addAuthor(author1);

        library.addBook(book2);
        library.addAuthor(author2);

        library.addBook(book3);
        library.addAuthor(author3);

        library.addLoan("Nathy", book1);
        library.addLoan("Teste", book1);

        System.out.println(library.getAllBooks());
        System.out.println(library.getAllAuthors());
        System.out.println(library.getAllLoans());
    }
}
