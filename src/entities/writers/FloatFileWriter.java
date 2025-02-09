package entities.writers;

import enums.Type;
import managers.LaunchSettings;

import java.io.IOException;

// Singleton-класс для записи в файл чисел типа Float
public class FloatFileWriter implements FileWriter {

    private static FloatFileWriter floatFileWriterInstance;

    private LaunchSettings launchSettings;

    private boolean exists;

    private int semaphore = 0;

    private FloatFileWriter() {}

    public static FloatFileWriter getInstance() {
        if (floatFileWriterInstance == null) {
            floatFileWriterInstance = new FloatFileWriter();
        }
        return floatFileWriterInstance;
    }

    @Override
    public void setLaunchSettings(LaunchSettings launchSettings) {
        this.launchSettings = launchSettings;
        exists = exists(Type.FLOAT.getFilePath(launchSettings.getFileSignaturePrefix()));
    }

    @Override
    public void write(String str, boolean append) {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(Type.FLOAT.getFilePath(launchSettings.getFileSignaturePrefix()), true);
            if (!append && exists && semaphore == 0) {
                fileWriter = new java.io.FileWriter(Type.FLOAT.getFilePath(launchSettings.getFileSignaturePrefix()));
                fileWriter.close();
                fileWriter = new java.io.FileWriter(Type.FLOAT.getFilePath(launchSettings.getFileSignaturePrefix()), true);
                semaphore = 1;
            }
            fileWriter.write(str + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
