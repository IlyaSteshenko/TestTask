package enums;

import managers.LaunchSettings;

public enum Type {
    INT ("integers.txt"),
    FLOAT ("floats.txt"),
    STRING ("strings.txt");

    private final String filePath;

    Type(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath(String prefix) {
        return prefix + filePath;
    }
}
