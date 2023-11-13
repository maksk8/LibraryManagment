package pl.javastart.library.app;

import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryControl libraryControl = new LibraryControl();
        ConsolePrinter printer = new ConsolePrinter();
        DataReader reader = new DataReader(printer);
        libraryControl.controlLoop();
    }
}