package pl.javastart.library.app;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.exception.NoSuchOptionException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.io.file.FileManager;
import pl.javastart.library.io.file.FileManagerBuilder;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Library library = new Library();

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Data imported from file.");
        } catch (DataImportException e) {
            printer.printLine(e.getMessage());
            printer.printLine("New database initialized.");
            library = new Library();
        }
    }

    public void controlLoop(){
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option){
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("There is no such option. Try again.");
            }
        } while(option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", state again:");
            } catch (InputMismatchException ignored) {
                printer.printLine("you have entered non integer value, try again");
            }
        } return option;
    }

    private void printOptions() {
        printer.printLine("Choose option: ");
        for (Option option:Option.values()
             ) {
            System.out.println(option);
        }
    }

    private void addBook(){
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create book. Invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("The capacity limit has been reached. you cannot enter another book");
        }
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readeAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create magazine. Invalid data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("The capacity limit has been reached. you cannot enter another magazine");
        }
    }

    private void printBooks(){
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void printMagazines() {
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
    }

    private void exit(){
        try {
            fileManager.exportData(library);
            printer.printLine("Data export to file succesful");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("End of program.");
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "Exit"),
        ADD_BOOK(1, "Add book"),
        ADD_MAGAZINE(2, "Add magazin"),
        PRINT_BOOKS(3, "Show available books"),
        PRINT_MAGAZINES(4, "Show available magazines    ");

        private int value;
        private String description;

        Option(int value, String desc) {
            this.value = value;
            this.description = desc;
        }

        public String toString() {
            return value + "-" + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException{
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("There is no option id: " + option);
            }
        }


    }
}
