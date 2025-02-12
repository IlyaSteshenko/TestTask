package entities.writers;

import enums.Type;
import managers.LaunchSettings;

import java.io.File;
import java.io.IOException;

/// Singleton-класс для записи строк в файл
public class StringFileWriter implements FileWriter {

    private static StringFileWriter stringFileWriterInstance;

    /// Настройки запуска
    private LaunchSettings launchSettings;

    /// Проверка существования файла
    private boolean exists;

    /// Переменная-семафор для проверки существования файлов результатов
    ///
    /// По умолчанию равна 0
    ///
    /// Если файлы по заданному пути отсутствуют, после их создания семафор принимает значение 1
    private int semaphore = 0;

    private StringFileWriter() {}

    public static StringFileWriter getInstance() {
        if (stringFileWriterInstance == null) {
            stringFileWriterInstance = new StringFileWriter();
        }
        return stringFileWriterInstance;
    }

    @Override
    public void setLaunchSettings(LaunchSettings launchSettings) {
        this.launchSettings = launchSettings;
        if (semaphore == 0) {
            String totalPath =
                    launchSettings.getExternalFilePath() + Type.STRING.getFilePath(launchSettings.getFileSignaturePrefix());
            exists = exists(totalPath);
        }
    }

    /// Метод записи данных в файл
    @Override
    public synchronized void write(String str, boolean append) {

        File file = new File(launchSettings.getExternalFilePath());

        if (!file.exists()) {
            file.mkdir();
        }

        if (!isCorrectString(str)) {
            return;
        }

        try {
            String totalPath =
                    launchSettings.getExternalFilePath() + Type.STRING.getFilePath(launchSettings.getFileSignaturePrefix());

            java.io.FileWriter fileWriter = new java.io.FileWriter(totalPath, true);
            if (!append && exists && semaphore == 0) {
                fileWriter = new java.io.FileWriter(totalPath);
                fileWriter.close();
                fileWriter = new java.io.FileWriter(totalPath, true);
            }
            semaphore = 1;
            fileWriter.write(str + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /// Проверка корректности строки
    private boolean isCorrectString(String string) {
        return string.matches(".+[a-zа-яA-ZА-Я0-9]+.+");
    }
}
