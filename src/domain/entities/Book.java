package domain.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private Author author;
    private Boolean isAvailable;
    private LocalDate registeredBookDate;
    private LocalDate updatedBookDate;

    public Book() {
    }

    public Book(String title, Author author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.registeredBookDate = LocalDate.now();
        this.updatedBookDate = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
        setUpdatedBookDate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        setUpdatedBookDate();
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        setUpdatedBookDate();

    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
        }

    public LocalDate getRegisteredBookDate() {
        return registeredBookDate;
    }

    public void setRegisteredBookDate(LocalDate registeredBookDate) {
        this.registeredBookDate = registeredBookDate;
    }

    public LocalDate getUpdatedBookDate() {
        return updatedBookDate;
    }

    public void setUpdatedBookDate() {
        this.updatedBookDate = LocalDate.now();
    }

    public String toString() {
        return "Book {" +
                "\n  ID: " + id +
                "\n  Title: " + title +
                "\n  Author: " + author.getName() +
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
