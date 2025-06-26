package infrastructure.repositories;

import domain.entities.Author;
import domain.entities.Book;
import domain.repositories.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAuthorRepository implements AuthorRepository {
    private final List<Author> authors = new ArrayList<>();

    @Override
    public void save(Author author) {
        authors.add(author);
    }

    @Override
    public List<Author> findAll() {
        return authors.stream().toList();
    }

    @Override
    public Optional<Author> findByName(String name) {
        return authors
                .stream()
                .filter(author -> author.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
