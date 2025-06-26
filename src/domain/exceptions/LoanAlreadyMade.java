package domain.exceptions;

public class LoanAlreadyMade extends RuntimeException{
    public LoanAlreadyMade() {
        super("Operação inválida! Livro já foi emprestado");
    }

    public LoanAlreadyMade(String message) {
        super(message);
    }
}
