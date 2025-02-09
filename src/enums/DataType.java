package enums;

public class DataType {

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

    public static StatisticType statisticTypeOf(String flag) {
        switch (flag) {
            case "-s" -> {
                return StatisticType.SHORT;
            }
            case "-f" -> {
                return StatisticType.FULL;
            }
            default -> throw new IllegalArgumentException(
                    "Wrong parameter"
            );
        }
    }
}
