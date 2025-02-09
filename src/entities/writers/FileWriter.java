package entities.writers;

import managers.LaunchSettings;

import java.io.File;

public interface FileWriter {
    void write(String str, boolean append);
    void setLaunchSettings(LaunchSettings launchSettings);
    default boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
