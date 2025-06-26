package domain.repositories;

import domain.entities.Author;
import domain.entities.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    void save(Author author);
    List<Author> findAll();
    Optional<Author> findByName(String name);

}
