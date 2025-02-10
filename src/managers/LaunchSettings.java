package managers;

import enums.StatisticType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LaunchSettings {

    // Входные аргументы программы
    private final String[] args;

    // Список входных файлов
    private final List<String> files;

    // Путь к файлам результатов, задаваемый пользователем
    private String externalFilePath = "";

    // Префикс к названиям файлов результатов
    private String fileSignaturePrefix = "";

    // Тип статистики
    private StatisticType statisticType;

    // Опция для добавления в существующие файлы
    private boolean append = false;

    public LaunchSettings(String[] args) {
        this.args = args;
        files = new ArrayList<>();
        setSettings();
    }

    // Установка значений настроек
    private void setSettings() {

        for (int i = 0; i < args.length; i++) {

            if (args[i].matches(".+\\.txt")) {
                files.add(args[i]);
                continue;
            }

            switch (args[i]) {
                case "-o" -> externalFilePath = args[i + 1];
                case "-p" -> fileSignaturePrefix = args[i + 1];
                case "-a" -> append = true;
                case "-s" -> statisticType = StatisticType.SHORT;
                case "-f" -> statisticType = StatisticType.FULL;
            }
        }
    }

    public List<String> getFiles() {
        return files;
    }

    public String getExternalFilePath() {
        if (externalFilePath.isEmpty()) {
            return externalFilePath;
        }
        return externalFilePath + "/";
    }

    public String getFileSignaturePrefix() {
        return fileSignaturePrefix;
    }

    public StatisticType getStatisticType() {
        return statisticType;
    }

    public boolean isAppend() {
        return append;
    }
}
