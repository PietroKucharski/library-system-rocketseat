package domain.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
        super("Livro não encontrado");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
