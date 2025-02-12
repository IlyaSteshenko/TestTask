package enums;

/// Класс для предоставления типов входных данных
public class DataType {

    /// Метод возвращает тип входных данных
    public static Type typeOf(String str) {
        try {
            Long.parseLong(str);
            return Type.INT;
        } catch (NumberFormatException e) {
            try {
                Double.parseDouble(str);
                return Type.FLOAT;
            } catch (NumberFormatException ex) {
                return Type.STRING;
            }
        }
    }
}
