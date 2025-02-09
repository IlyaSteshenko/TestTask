package entities.writers;

import enums.Type;

import java.io.IOException;

// Singleton-класс для записи строк в файл
public class StringFileWriter implements FileWriter {

    private static StringFileWriter stringFileWriterInstance;

    private final boolean exists = exists(Type.STRING.getFilePath());

    private int semaphore = 0;

    private StringFileWriter() {}

    public static StringFileWriter getInstance() {
        if (stringFileWriterInstance == null) {
            stringFileWriterInstance = new StringFileWriter();
        }
        return stringFileWriterInstance;
    }

    @Override
    public void write(String str, boolean append) {

        if (!isCorrectString(str)) {
            return;
        }

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(Type.STRING.getFilePath(), true);
            if (!append && exists && semaphore == 0) {
                fileWriter = new java.io.FileWriter(Type.STRING.getFilePath());
                fileWriter.close();
                fileWriter = new java.io.FileWriter(Type.STRING.getFilePath(), true);
                semaphore = 1;
            }
            fileWriter.write(str + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isCorrectString(String string) {
        return string.matches(".+[a-zа-яA-ZА-Я0-9]+.+");
    }
}
