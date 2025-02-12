package enums;

/// Enum для типов входных данных
///
/// Содержит 3 константы:
///
/// - для целых чисел
///
/// - для дробных чисел
///
/// - для строк
public enum Type {

    /// Константа для целых чисел
    INT ("integers.txt"),

    /// Константа для дробных чисел
    FLOAT ("floats.txt"),

    /// Константа для строк
    STRING ("strings.txt");

    private final String filePath;

    Type(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath(String prefix) {
        return prefix + filePath;
    }
}
