package entities.writers;

import enums.Type;
import managers.LaunchSettings;

import java.io.File;
import java.io.IOException;

// Singleton-класс для записи в файл чисел типа Integer
public class IntegerFileWriter implements FileWriter {

    private static IntegerFileWriter integerFileWriterInstance;

    private LaunchSettings launchSettings;

    private boolean exists;

    private int semaphore = 0;

    private IntegerFileWriter() {}

    public static IntegerFileWriter getInstance() {
        if (integerFileWriterInstance == null) {
            integerFileWriterInstance = new IntegerFileWriter();
        }
        return integerFileWriterInstance;
    }

    @Override
    public void setLaunchSettings(LaunchSettings launchSettings) {
        this.launchSettings = launchSettings;
        if (semaphore == 0) {
            String totalPath =
                    launchSettings.getExternalFilePath() + Type.INT.getFilePath(launchSettings.getFileSignaturePrefix());
            exists = exists(totalPath);
        }
    }

    @Override
    public void write(String str, boolean append) {
        try {
            String totalPath = launchSettings.getExternalFilePath() + Type.INT.getFilePath(launchSettings.getFileSignaturePrefix());

            java.io.FileWriter fileWriter = new java.io.FileWriter(totalPath, true);
            if (!append && exists && semaphore == 0) {
                fileWriter = new java.io.FileWriter(totalPath);
                fileWriter.close();
                fileWriter = new java.io.FileWriter(totalPath, true);
            }
            semaphore = 1;
            fileWriter.write(str + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
