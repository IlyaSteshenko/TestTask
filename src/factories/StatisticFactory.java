package factories;

import entities.statistics.NumericalStatistic;
import entities.statistics.Statistic;
import entities.statistics.StringStatistic;
import enums.Type;
import managers.LaunchSettings;

public class StatisticFactory {

    public static Statistic getStatistic(Type type, LaunchSettings launchSettings) {
        Statistic statistic;
        String fileName =
                launchSettings.getExternalFilePath() + type.getFilePath(launchSettings.getFileSignaturePrefix());
        if (type == Type.INT || type == Type.FLOAT) {
            statistic = new NumericalStatistic(fileName);
        } else {
            statistic = new StringStatistic(fileName);
        }
        return statistic;
    }
}
