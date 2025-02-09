package factories;

import entities.statistics.NumericalStatistic;
import entities.statistics.Statistic;
import entities.statistics.StringStatistic;
import enums.Type;

public class StatisticFactory {

    public static Statistic getStatistic(Type type) {
        Statistic statistic = null;
        if (type == Type.INT || type == Type.FLOAT) {
            statistic = new NumericalStatistic(type.getFilePath());
        } else {
            statistic = new StringStatistic(type.getFilePath());
        }
        return statistic;
    }
}
