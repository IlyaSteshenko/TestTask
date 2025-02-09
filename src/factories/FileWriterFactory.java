package factories;

import entities.writers.FileWriter;
import entities.writers.FloatFileWriter;
import entities.writers.IntegerFileWriter;
import entities.writers.StringFileWriter;
import enums.Type;

public class FileWriterFactory {

    public static FileWriter getWriter(Type type) {
        FileWriter writer = null;
        switch (type) {
            case INT -> writer = IntegerFileWriter.getInstance();
            case FLOAT -> writer = FloatFileWriter.getInstance();
            case STRING -> writer = StringFileWriter.getInstance();
        }
        return writer;
    }

}
