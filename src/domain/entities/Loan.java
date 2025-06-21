package domain.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    private Customer customer;
    private Book book;
    private LocalDate loanDay;
    private LocalDate loanReturn;

    public Loan() {
    }

    public Loan(Customer customer, Book book, LocalDate loanDay) {
        this.customer = customer;
        this.book = book;
        this.loanDay = loanDay;
        this.loanReturn = loanDay.plusDays(7);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        return "Loan {" + customer + ", " + book + ", " + loanDay.format(formatter) + ", " + loanReturn.format(formatter) + "}";
    }

}
