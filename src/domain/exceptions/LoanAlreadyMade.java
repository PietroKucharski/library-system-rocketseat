package domain.exceptions;

public class LoanAlreadyMade extends Exception{
    public LoanAlreadyMade() {
        super("Operação inválida! Livro já foi emprestado");
    }

    public LoanAlreadyMade(String message) {
        super(message);
    }
}
