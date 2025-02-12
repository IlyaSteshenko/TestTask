package entities.statistics;

import enums.StatisticType;

import java.io.BufferedReader;
import java.io.FileReader;

/// Класс статистики для чисел (целых и с плавающей точкой)
public class NumericalStatistic implements Statistic {

    private int count = 0;
    private double minValue = Integer.MAX_VALUE;
    private double maxValue = Integer.MIN_VALUE;
    private double sumValue = 0;
    private double midValue = 0;
    private final String statisticFileName;

    public NumericalStatistic(String statisticFileName) {
        this.statisticFileName = statisticFileName;
    }

    private void updateCount() {
        count++;
    }

    private void updateMinValue(double value) {
        if (value < minValue) minValue = value;
    }

    private void updateMaxValue(double value) {
        if (value > maxValue) maxValue = value;
    }

    private void updateSumValue(double value) {
        sumValue += value;
    }

    private void updateMidValue() {
        midValue = sumValue / count;
    }

    @Override
    public void getStatistic(StatisticType statisticType) {

        // Обработка статистики файлов результатов
        try (FileReader reader = new FileReader(statisticFileName)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line  = bufferedReader.readLine();

            while (line != null) {
                double num;
                try {
                    num = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    num = Double.parseDouble(line);
                }
                updateMinValue(num);
                updateMaxValue(num);
                updateSumValue(num);
                updateCount();

                line = bufferedReader.readLine();
            }
            updateMidValue();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(statisticFileName);
        switch (statisticType) {
            case SHORT -> {
                System.out.println("Count: " + count);
            }
            case FULL -> {
                System.out.println("Min Value: " + minValue);
                System.out.println("Max Value: " + maxValue);
                System.out.println("Sum: " + sumValue);
                System.out.println("Middle: " + midValue + "\n");
            }
        }
    }

}
