package managers;

import enums.StatisticType;

public class LaunchSettings {

    private final String[] args;

//    // Путь к файлам результатов по умолчанию
//    private String internalFilePath;

    // Путь к файлам результатов, задаваемый пользователем
    private String externalFilePath;

    // Префикс к названиям файлов результатов
    private String fileSignaturePrefix = "";

    // Тип статистики
    private StatisticType statisticType;

    // Опция для добавления в существующие файлы
    private boolean append;

    public LaunchSettings(String[] args) {
        this.args = args;
        setSettings();
    }

    // Установка значений настроек
    public void setSettings() {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o" -> externalFilePath = args[i + 1];
                case "-p" -> fileSignaturePrefix = args[i + 1];
                case "-a" -> append = true;
                case "-s" -> statisticType = StatisticType.SHORT;
                case "-f" -> statisticType = StatisticType.FULL;
            }
        }
    }

    public String getExternalFilePath() {
        return externalFilePath;
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
