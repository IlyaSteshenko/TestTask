package enums;

/// Enum для статистики
///
/// Содержит 2 константы:
///
/// - для вывода короткой статистики
///
/// - для вывода полной статистики
public enum StatisticType {

    /// Константа короткой статистики
    SHORT ("Short Statistic:\n"),

    /// Константа полной статистики
    FULL ("Full Statistic:\n");

    private final String sign;

    StatisticType(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
