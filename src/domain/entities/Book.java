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
    private LocalDate updatedAt;

    public Book() {
    }

    public Book(String title, Author author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.createdAt = LocalDate.now();
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

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Book {" + id + ", " + title + ", " + author + ", " + (isAvailable ? "Yes" : "No") + "}";
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
