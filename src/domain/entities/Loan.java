package domain.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    private String username;
    private Book book;
    private LocalDate loanDay;
    private LocalDate loanReturn;

    public Loan() {
    }

    public Loan(String username, Book book, LocalDate loanDay) {
        this.username = username;
        this.book = book;
        this.loanDay = loanDay;
        this.loanReturn = loanDay.plusDays(7);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getLoanDay() {
        return loanDay;
    }

    public void setLoanDay(LocalDate loanDay) {
        this.loanDay = loanDay;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Loan {" + username + ", " + book + ", " + loanDay.format(formatter) + ", " + loanReturn.format(formatter) + "}";
    }

}
