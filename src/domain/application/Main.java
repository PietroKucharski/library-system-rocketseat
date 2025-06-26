package domain.application;

public class Main {
    public static void main(String[] args) {
        LibraryMenu menu = new LibraryMenu(new LibraryMenuFactory().createLibrary());
        menu.startApplication();
    }
}
