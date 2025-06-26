package domain.application;

import domain.entities.Author;
import domain.entities.Book;
import domain.entities.Customer;
import domain.entities.Library;
import domain.exceptions.LoanAlreadyMade;
import domain.repositories.AuthorRepository;
import domain.repositories.BookRepository;
import domain.repositories.CustomerRepository;
import domain.repositories.LoanRepository;
import domain.services.LoanService;
import infrastructure.repositories.InMemoryAuthorRepository;
import infrastructure.repositories.InMemoryBookRepository;
import infrastructure.repositories.InMemoryCustomerRepository;
import infrastructure.repositories.InMemoryLoansRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class LibraryMenu {
    private AuthorRepository authorRepository = new InMemoryAuthorRepository();
    private BookRepository bookRepository = new InMemoryBookRepository();
    private LoanRepository loanRepository = new InMemoryLoansRepository();
    private CustomerRepository customerRepository = new InMemoryCustomerRepository();
    private LoanService loanService = new LoanService(loanRepository);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Scanner scanner = new Scanner(System.in);

    Library library = new Library(authorRepository, bookRepository, customerRepository, loanService);

    public LibraryMenu(Library library) {
        this.library = library;
    }

    public void startApplication() {
        int option;

        do {
            showMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            try {
                handleOption(option);
            } catch (LoanAlreadyMade e) {
                throw new RuntimeException(e);
            }
        } while (option != 0);
    }

    public void showMenu() {
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
    }

    public void handleOption(int option) throws LoanAlreadyMade {
        switch (option) {
            case 1 -> registerBook();
            case 2 -> registerAuthor();
            case 3 -> registerCustomer();
            case 4 -> registerLoan();
            case 5 -> returnLoan();
            case 6 -> library.getAllLoans().forEach(System.out::println);
            case 7 -> getLoanByCustomer();
            case 8 -> getLoanByBookTitle();
            case 9 -> library.getAllBooks().forEach(System.out::println);
            case 10 -> getBookByTitle();
            case 11 -> getBookByAuthor();
            case 0 -> System.out.println("Encerrando o sistema...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private void registerAuthor() {
        System.out.print("Digite o nome do autor: ");
        String authorName = scanner.nextLine();

        System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
        String authorBirthDate = scanner.nextLine();

        try {
            LocalDate birthDate = LocalDate.parse(authorBirthDate, formatter);
            library.insertAuthor(new Author(authorName, birthDate));
            System.out.println("Autor cadastrado com sucesso.");
        } catch (DateTimeException e) {
            System.out.println("Data inválida.");
        }
    }

    private void registerBook() {
        if (library.getAllAuthors().isEmpty()) {
            System.out.println("Cadastre um autor antes de adicionar um livro.");
            return;
        }

        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();

        System.out.println("Selecione o autor:");
        for (int i = 0; i < library.getAllAuthors().size(); i++) {
            System.out.println((i + 1) + " - " + library.getAllAuthors().get(i));
        }

        int selected = scanner.nextInt();
        scanner.nextLine();
        if (selected < 1 || selected > library.getAllAuthors().size()) {
            System.out.println("Autor inválido.");
        }

        Author selectedAuthor = library.getAllAuthors().get(selected - 1);
        library.insertBook(new Book(title, selectedAuthor));
        System.out.println("Livro adicionado com sucesso.");
    }

    private void registerCustomer() {
        System.out.print("Digite o nome do cliente: ");
        String customerName = scanner.nextLine();

        System.out.print("Digite o email do cliente: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
        String birthDateStr = scanner.nextLine();

        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            library.insertCustomer(new Customer(customerName, customerEmail, birthDate));
            System.out.println("Cliente cadastrado com sucesso.");
        } catch (DateTimeException e) {
            System.out.println("Data inválida.");
        }
    }

    private void registerLoan() throws LoanAlreadyMade {
        if (library.getAllCustomer().isEmpty() || library.getAllBooks().isEmpty()) {
            System.out.println("É necessário ter clientes e livros cadastrados.");
        }

        System.out.println("Selecione o cliente:");
        for (int i = 0; i < library.getAllCustomer().size(); i++) {
            System.out.println((i + 1) + " - " + library.getAllCustomer().get(i));
        }
        int selectedCustomer = scanner.nextInt();
        scanner.nextLine();

        if (selectedCustomer < 1 || selectedCustomer > library.getAllCustomer().size()) {
            System.out.println("Cliente inválido.");
        }
        Customer customer = library.getAllCustomer().get(selectedCustomer - 1);

        System.out.println("Selecione o livro:");
        for (int i = 0; i < library.getAllBooks().size(); i++) {
            System.out.println((i + 1) + " - " + library.getAllBooks().get(i));
        }
        int selectedBook = scanner.nextInt();
        scanner.nextLine();

        if (selectedBook < 1 || selectedBook > library.getAllBooks().size()) {
            System.out.println("Livro inválido.");
        }

        Book book = library.getAllBooks().get(selectedBook - 1);
        library.makeLoan(customer, book);
        System.out.println("Empréstimo realizado.");
    }

    private void returnLoan() {
        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();

        Optional<Customer> customerEmail = library.getCustomerByEmail(email);
        Optional<Book> bookTitle = library.getBookByTitle(title);

        if(customerEmail.isEmpty()) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        if (bookTitle.isEmpty()) {
            System.out.println("Livro não encontrado.");
            return;
        }

        Customer customer = customerEmail.get();
        Book book = bookTitle.get();

        library.returnLoan(customer, book);
        System.out.println("Devolução registrada.");
    }

    private void getLoanByCustomer() {
        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        Optional<Customer> customerEmail = library.getCustomerByEmail(email);

        if(customerEmail.isEmpty()) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Customer customer = customerEmail.get();

        System.out.println(library.getLoansByCustomer(customer));
    }

    private void getLoanByBookTitle() {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.println(library.getLoanByBookTitle(title));
    }

    private void getBookByTitle() {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.println(library.getBookByTitle(title));
    }

    private void getBookByAuthor() {
        System.out.print("Digite o nome do autor: ");
        String authorName = scanner.nextLine();
        System.out.println(library.getAllAuthorBooks(authorName));
    }
}
