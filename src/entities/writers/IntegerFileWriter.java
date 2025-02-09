package entities.writers;

import enums.Type;

import java.io.File;
import java.io.IOException;

// Singleton-класс для записи в файл чисел типа Integer
public class IntegerFileWriter implements FileWriter {

    private static IntegerFileWriter integerFileWriterInstance;

    private final boolean exists = exists(Type.INT.getFilePath());

    private int semaphore = 0;

    private IntegerFileWriter() {}

    public static IntegerFileWriter getInstance() {
        if (integerFileWriterInstance == null) {
            integerFileWriterInstance = new IntegerFileWriter();
        }
        return integerFileWriterInstance;
    }

    @Override
    public void write(String str, boolean append) {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(Type.INT.getFilePath(), true);
            if (!append && exists && semaphore == 0) {
                fileWriter = new java.io.FileWriter(Type.INT.getFilePath());
                fileWriter.close();
                fileWriter = new java.io.FileWriter(Type.INT.getFilePath(), true);
                semaphore = 1;
            }
            fileWriter.write(str + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
