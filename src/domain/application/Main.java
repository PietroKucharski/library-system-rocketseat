package domain.application;

import domain.entities.Author;
import domain.entities.Book;
import domain.entities.Customer;
import domain.entities.Library;
import domain.exceptions.LoanAlreadyMade;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws LoanAlreadyMade {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Library library = new Library();

        int userInput;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Cadastrar autor");
            System.out.println("3 - Cadastrar cliente");
            System.out.println("4 - Realizar empréstimo");
            System.out.println("5 - Devolver livro");
            System.out.println("6 - Ver todos os empréstimos");
            System.out.println("7 - Ver empréstimo por cliente");
            System.out.println("8 - Ver empréstimo por título do livro");
            System.out.println("9 - Ver todos os livros");
            System.out.println("10 - Buscar livro por título");
            System.out.println("11 - Buscar livros por autor");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            userInput = sc.nextInt();
            sc.nextLine();

            switch (userInput) {
                case 1: {
                    if (library.getAllAuthors().isEmpty()) {
                        System.out.println("Cadastre um autor antes de adicionar um livro.");
                        break;
                    }

                    System.out.print("Digite o título do livro: ");
                    String title = sc.nextLine();

                    System.out.println("Selecione o autor:");
                    for (int i = 0; i < library.getAllAuthors().size(); i++) {
                        System.out.println((i + 1) + " - " + library.getAllAuthors().get(i));
                    }

                    int selected = sc.nextInt();
                    sc.nextLine();
                    if (selected < 1 || selected > library.getAllAuthors().size()) {
                        System.out.println("Autor inválido.");
                        break;
                    }

                    Author selectedAuthor = library.getAllAuthors().get(selected - 1);
                    library.addBook(new Book(title, selectedAuthor));
                    System.out.println("Livro adicionado com sucesso.");
                    break;
                }
                case 2: {
                    System.out.print("Digite o nome do autor: ");
                    String authorName = sc.nextLine();

                    System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
                    String authorBirthDate = sc.nextLine();

                    try {
                        LocalDate birthDate = LocalDate.parse(authorBirthDate, dtf);
                        library.addAuthor(new Author(authorName, birthDate));
                        System.out.println("Autor cadastrado com sucesso.");
                    } catch (DateTimeException e) {
                        System.out.println("Data inválida.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Digite o nome do cliente: ");
                    String customerName = sc.nextLine();

                    System.out.print("Digite o email do cliente: ");
                    String customerEmail = sc.nextLine();

                    System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
                    String birthDateStr = sc.nextLine();

                    try {
                        LocalDate birthDate = LocalDate.parse(birthDateStr, dtf);
                        library.addCustomer(new Customer(customerName, customerEmail, birthDate));
                        System.out.println("Cliente cadastrado com sucesso.");
                    } catch (DateTimeException e) {
                        System.out.println("Data inválida.");
                    }
                    break;
                }
                case 4: {
                    if (library.getAllCustomers().isEmpty() || library.getAllBooks().isEmpty()) {
                        System.out.println("É necessário ter clientes e livros cadastrados.");
                        break;
                    }

                    System.out.println("Selecione o cliente:");
                    for (int i = 0; i < library.getAllCustomers().size(); i++) {
                        System.out.println((i + 1) + " - " + library.getAllCustomers().get(i));
                    }
                    int selectedCustomer = sc.nextInt();
                    sc.nextLine();

                    if (selectedCustomer < 1 || selectedCustomer > library.getAllCustomers().size()) {
                        System.out.println("Cliente inválido.");
                        break;
                    }
                    Customer customer = library.getAllCustomers().get(selectedCustomer - 1);

                    System.out.println("Selecione o livro:");
                    for (int i = 0; i < library.getAllBooks().size(); i++) {
                        System.out.println((i + 1) + " - " + library.getAllBooks().get(i));
                    }
                    int selectedBook = sc.nextInt();
                    sc.nextLine();

                    if (selectedBook < 1 || selectedBook > library.getAllBooks().size()) {
                        System.out.println("Livro inválido.");
                        break;
                    }

                    Book book = library.getAllBooks().get(selectedBook - 1);
                    library.addLoan(customer, book);
                    System.out.println("Empréstimo realizado.");
                    break;
                }
                case 5: {
                    System.out.print("Digite o email do cliente: ");
                    String email = sc.nextLine();

                    System.out.print("Digite o título do livro: ");
                    String title = sc.nextLine();

                    library.returnLoan(email, title);
                    System.out.println("Devolução registrada.");
                    break;
                }
                case 6: {
                    System.out.println("Todos os empréstimos:");
                    System.out.println(library.getAllLoans());
                    break;
                }
                case 7: {
                    System.out.print("Digite o email do cliente: ");
                    String email = sc.nextLine();
                    System.out.println(library.getLoanByCustomer(email));
                    break;
                }
                case 8: {
                    System.out.print("Digite o título do livro: ");
                    String title = sc.nextLine();
                    System.out.println(library.getLoanByBookTitle(title));
                    break;
                }
                case 9: {
                    System.out.println("Livros cadastrados:");
                    System.out.println(library.getAllBooks());
                    break;
                }
                case 10: {
                    System.out.print("Digite o título do livro: ");
                    String title = sc.nextLine();
                    System.out.println(library.getBookByTitle(title));
                    break;
                }
                case 11: {
                    System.out.print("Digite o nome do autor: ");
                    String authorName = sc.nextLine();
                    System.out.println(library.getBooksByAuthor(authorName));
                    break;
                }
                case 0: {
                    System.out.println("Encerrando aplicação...");
                    break;
                }
                default: {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }

        } while (userInput != 0);
        sc.close();
    }
}
