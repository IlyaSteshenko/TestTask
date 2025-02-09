package entities.writers;

import enums.Type;

import java.io.IOException;

// Singleton-класс для записи в файл чисел типа Float
public class FloatFileWriter implements FileWriter {

    private static FloatFileWriter floatFileWriterInstance;

    private final boolean exists = exists(Type.FLOAT.getFilePath());

    private int semaphore = 0;

    private FloatFileWriter() {}

    public static FloatFileWriter getInstance() {
        if (floatFileWriterInstance == null) {
            floatFileWriterInstance = new FloatFileWriter();
        }
        return floatFileWriterInstance;
    }

    @Override
    public void write(String str, boolean append) {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(Type.FLOAT.getFilePath(), true);
            if (!append && exists && semaphore == 0) {
                fileWriter = new java.io.FileWriter(Type.FLOAT.getFilePath());
                fileWriter.close();
                fileWriter = new java.io.FileWriter(Type.FLOAT.getFilePath(), true);
                semaphore = 1;
            }
            fileWriter.write(str + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
