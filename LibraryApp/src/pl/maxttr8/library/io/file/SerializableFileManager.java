package pl.javastart.library.io.file;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager{
    private static final String FILE_NAME = "Library.o";

    public void exportData(Library library) {
        try(FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("There is no file " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Error while saving data to file " + FILE_NAME);
        }
    }

    public Library importData() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("There is no file " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Error while reading file " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Incompatible data type in file " + FILE_NAME);
        }
    }
}
