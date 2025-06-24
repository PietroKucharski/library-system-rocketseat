package domain.application;
import domain.entities.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryMenu menu = new LibraryMenu(library);
        menu.startApplication();
    }
}
