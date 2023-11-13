package pl.javastart.library.io;

import pl.javastart.library.model.Book;
import pl.javastart.library.model.Magazine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public String getString() {
        return sc.nextLine();
    }

    public void close() {
        sc.close();
    }

    public int getInt(){
        try {
            return sc.nextInt();
        }
//        } catch (InputMismatchException e) {   SKRÓCONA WERSJA
//            throw e;
//        }
        finally {
            sc.nextLine();
        }
    }

    public Book readAndCreateBook() {
        printer.printLine("Title: ");
        String title = sc.nextLine();
        printer.printLine("Author: ");
        String author = sc.nextLine();
        printer.printLine("Publisher: ");
        String publisher = sc.nextLine();
        printer.printLine("ISBN: ");
        String isbn = sc.nextLine();
        printer.printLine("Release date: ");
        int releaseDate = getInt();
        printer.printLine("Pages: ");
        int pages = getInt();

        return new Book(title, author, publisher, pages, releaseDate, isbn);
    }

    public Magazine readeAndCreateMagazine() {
        printer.printLine("Title: ");
        String title = sc.nextLine();
        printer.printLine("Publisher: ");
        String publisher = sc.nextLine();
        printer.printLine("Language: ");
        String language = sc.nextLine();
        printer.printLine("Publishing year: ");
        int year = getInt();
        printer.printLine("Month: ");
        int month = getInt();
        printer.printLine("Day: ");
        int day = getInt();

        return new Magazine(title, publisher, language, year, month, day);
    }
}
