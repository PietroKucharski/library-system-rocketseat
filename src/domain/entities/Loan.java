package domain.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    private Customer customer;
    private Book book;
    private LocalDate loanDay;
    private LocalDate loanReturn;
    private LocalDate loanReturnDate;

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDay() {
        return loanDay;
    }

    public void setLoanDay(LocalDate loanDay) {
        this.loanDay = loanDay;
    }

    public LocalDate getLoanReturn() {
        return loanReturn;
    }

    public void setLoanReturn(LocalDate loanReturn) {
        this.loanReturn = loanReturn;
    }

    public LocalDate getLoanReturnDate() {
        return loanReturnDate;
    }

    public void setLoanReturnDate(LocalDate loanReturnDate) {
        this.loanReturnDate = loanReturnDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Empréstimo {" +
                "\n  Usuário: '" + customer + '\'' +
                ",\n  Livro: " + book +
                ",\n  Data do Empréstimo: " + loanDay.format(formatter) +
//                ",\n  Data de Devolução : " + loanReturnDate.format(formatter) +
                ",\n  Data para Devolução: " + loanReturn.format(formatter) +
                "\n}";
    }

}
