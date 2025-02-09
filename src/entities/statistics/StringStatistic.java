package entities.statistics;

import enums.StatisticType;

import java.io.BufferedReader;
import java.io.FileReader;

public class StringStatistic implements Statistic {

    private int count = 0;
    private double shortValue = Integer.MAX_VALUE;
    private double longValue = Integer.MIN_VALUE;
    private final String statisticFileName;

    public StringStatistic(String statisticFileName) {
        this.statisticFileName = statisticFileName;
    }

    private void updateCount() {
        count++;
    }

    private void updateShortValue(String str) {
        if (str.length() < shortValue) shortValue = str.length();
    }

    private void updateLongValue(String str) {
        if (str.length() > longValue) longValue = str.length();
    }

    @Override
    public void getStatistic(StatisticType statisticType) {

        try (FileReader reader = new FileReader(statisticFileName)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line  = bufferedReader.readLine();

            while (line != null) {
                updateShortValue(line);
                updateLongValue(line);
                updateCount();

                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(statisticFileName);
        System.out.println("Count: " + count);

        if (statisticType == StatisticType.FULL) {
            System.out.println("Shorter Length: " + shortValue);
            System.out.println("Longer Length: " + longValue + "\n");
        }
    }
}
