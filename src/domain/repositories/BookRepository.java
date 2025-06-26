package domain.repositories;

import domain.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void save(Book book);
    List<Book> findAll();
    Optional<Book> findByTitle(String title);
    List<Book> findBooksAuthor(String authorName);
}
