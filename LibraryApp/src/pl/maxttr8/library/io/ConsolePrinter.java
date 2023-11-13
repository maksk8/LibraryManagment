package pl.javastart.library.io;

import pl.javastart.library.model.Publication;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Book;

public class ConsolePrinter {
        public void printBooks(Publication[] publications){
            int countBooks = 0;
            for (Publication publication : publications) {
                if (publication instanceof Book){
                    printLine(publication.toString());
                    countBooks++;
                }
            }
            if (countBooks == 0) {
                printLine("There is no books in library.");
            }
        }

        public void printMagazines(Publication[] publications) {
            int magazineCount = 0;
            for (Publication publication : publications) {
                if (publication instanceof Magazine) {
                    printLine(publication.toString());
                    magazineCount++;
                }
            }
            if (magazineCount == 0) {
                printLine("There is no magazines in library.");
            }
        }

        public void printLine(String text) {
            System.out.println(text);
        }

}
