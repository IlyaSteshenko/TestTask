package entities.statistics;

import enums.StatisticType;

/// Интерфейс для реализации статистики
public interface Statistic {
    void getStatistic(StatisticType statisticType);
}
