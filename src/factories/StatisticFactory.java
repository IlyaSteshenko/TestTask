package factories;

import entities.statistics.NumericalStatistic;
import entities.statistics.Statistic;
import entities.statistics.StringStatistic;
import enums.Type;

public class StatisticFactory {

    public static Statistic getStatistic(Type type, String prefix) {
        Statistic statistic = null;
        if (type == Type.INT || type == Type.FLOAT) {
            statistic = new NumericalStatistic(type.getFilePath(prefix));
        } else {
            statistic = new StringStatistic(type.getFilePath(prefix));
        }
        return statistic;
    }
}
