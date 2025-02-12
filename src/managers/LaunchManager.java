package managers;

import entities.statistics.Statistic;
import enums.DataType;
import enums.Type;
import entities.writers.FileWriter;
import factories.FileWriterFactory;
import factories.StatisticFactory;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LaunchManager {

    /// Настройки запуска программы
    private final LaunchSettings launchSettings;

    private final ExecutorService executorService;

    Set<Type> pathsSet = new HashSet<>();

    public LaunchManager(LaunchSettings launchSettings) {
        this.launchSettings = launchSettings;
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /// Главный метод запуска программы
    public void launch() {

        try {

            /// Многопоточное считывание входных файлов
            for (String files : launchSettings.getFiles()) {

                File file = new File(files);

                /// Проверка на директорию
                if (file.isDirectory()) {
                    System.err.println("File is directory");
                    return;
                }

                executorService.submit(() -> {

                    try (FileReader fileReader = new FileReader(files)) { /// Проверка на существование файла

                        /// Проверка на пустоту файла
                        if (file.length() == 0) {
                            System.err.printf("File %s is empty\n", files);
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

                    } catch (IOException e) {
                        System.err.println("File does not exists");
                    }
                });
            }
            executorService.shutdown();
            if (!executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                executorService.shutdownNow();
            }

            /// Вывод статистики по файлам результатов
            if (launchSettings.getStatisticType() != null) {
                System.out.println(launchSettings.getStatisticType().getSign());
                for (Type type : pathsSet) {
                    Statistic statistic = StatisticFactory.getStatistic(type, launchSettings);
                    statistic.getStatistic(launchSettings.getStatisticType());
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
