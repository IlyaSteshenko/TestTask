package entities.writers;

import java.io.File;

public interface FileWriter {
    void write(String str, boolean append);
    default boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
