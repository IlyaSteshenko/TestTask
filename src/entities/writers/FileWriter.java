package entities.writers;

import managers.LaunchSettings;

import java.io.File;

/// Интерфейс для реализации классов записи
public interface FileWriter {
    void write(String str, boolean append);
    void setLaunchSettings(LaunchSettings launchSettings);
    default boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
