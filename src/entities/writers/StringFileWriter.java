package entities.writers;

import enums.Type;
import managers.LaunchSettings;

import java.io.IOException;

// Singleton-класс для записи строк в файл
public class StringFileWriter implements FileWriter {

    private static StringFileWriter stringFileWriterInstance;

    private LaunchSettings launchSettings;

    private boolean exists;

    private int semaphore = 0;

    private StringFileWriter() {}

    public static StringFileWriter getInstance() {
        if (stringFileWriterInstance == null) {
            stringFileWriterInstance = new StringFileWriter();
        }
        return stringFileWriterInstance;
    }

    @Override
    public void setLaunchSettings(LaunchSettings launchSettings) {
        this.launchSettings = launchSettings;
        exists = exists(Type.STRING.getFilePath(launchSettings.getFileSignaturePrefix()));
    }

    @Override
    public void write(String str, boolean append) {

        if (!isCorrectString(str)) {
            return;
        }

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(Type.STRING.getFilePath(launchSettings.getFileSignaturePrefix()), true);
            if (!append && exists && semaphore == 0) {
                fileWriter = new java.io.FileWriter(Type.STRING.getFilePath(launchSettings.getFileSignaturePrefix()));
                fileWriter.close();
                fileWriter = new java.io.FileWriter(Type.STRING.getFilePath(launchSettings.getFileSignaturePrefix()), true);
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
