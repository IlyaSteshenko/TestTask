package managers;

import entities.statistics.Statistic;
import enums.DataType;
import enums.StatisticType;
import enums.Type;
import entities.writers.FileWriter;
import factories.FileWriterFactory;
import factories.StatisticFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class LaunchManager {

    private final String[] args;

    Set<Type> pathsSet = new HashSet<>();

    public LaunchManager(String[] args) {
        this.args = args;
    }

    public void launch() {
        try (FileReader fileReader = new FileReader("src/files/file.txt")) {
            FileWriter writer;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                Type type = DataType.typeOf(line);
                pathsSet.add(type);
                writer = FileWriterFactory.getWriter(type);
                writer.write(line, true);

                line = bufferedReader.readLine();
            }

            for (Type type : pathsSet) {
                Statistic statistic = StatisticFactory.getStatistic(type);
                statistic.getStatistic(StatisticType.SHORT);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
