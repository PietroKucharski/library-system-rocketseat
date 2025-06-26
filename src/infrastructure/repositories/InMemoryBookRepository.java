package infrastructure.repositories;

import domain.entities.Book;
import domain.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> findAll() {
        return books.stream().toList();
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return books
                .stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title.toLowerCase()))
                .findFirst();
    }

    @Override
    public List<Book> findBooksAuthor(String authorName) {
        return books
                .stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName.toLowerCase()))
                .toList();
    }
}
