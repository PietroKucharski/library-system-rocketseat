package domain.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private Author author;
    private Boolean isAvailable;
    private LocalDate createdAt;

    public Book() {
    }

    public Book(String title, Author author, LocalDate createdAt) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }


    public String toString() {
        return "Book {" +
                "\n  Title: " + title +
                "\n  Author: " + author.getName() + // ou author.toString() para detalhes completos
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
