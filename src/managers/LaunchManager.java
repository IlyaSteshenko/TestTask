package managers;

import entities.statistics.Statistic;
import enums.DataType;
import enums.Type;
import entities.writers.FileWriter;
import factories.FileWriterFactory;
import factories.StatisticFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class LaunchManager {

    // Настройки запуска программы
    private LaunchSettings launchSettings;

    Set<Type> pathsSet = new HashSet<>();

    public LaunchManager(LaunchSettings launchSettings) {
        this.launchSettings = launchSettings;
    }

    public void launch() {

        File file = new File(launchSettings.getFiles().get(0));

        // Проверка на директорию
        if (file.isDirectory()) {
            System.out.println("File is directory");
            return;
        }

        // Проверка на корректное расширение
        if (!getFileExtension(file.getName()).equals("txt")) {
            System.out.println("File is not txt file! Check file extension");
            return;
        }

        try (FileReader fileReader = new FileReader(launchSettings.getFiles().get(0))) { // Проверка на существование файла

            // Проверка на пустоту файла
            if (file.length() == 0) {
                System.out.println("File is empty");
                return;
            }

            FileWriter writer;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                Type type = DataType.typeOf(line);
                pathsSet.add(type);
                writer = FileWriterFactory.getWriter(type);
                writer.setLaunchSettings(launchSettings);
                writer.write(line, launchSettings.isAppend());

                line = bufferedReader.readLine();
            }

            for (Type type : pathsSet) {
                Statistic statistic = StatisticFactory.getStatistic(type, launchSettings);
                statistic.getStatistic(launchSettings.getStatisticType());
            }

        } catch (Exception e) {
            System.out.println("File does not exists");
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.split("\\.")[1];
    }

}
