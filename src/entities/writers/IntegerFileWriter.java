package entities.writers;

import enums.Type;
import managers.LaunchSettings;

import java.io.File;
import java.io.IOException;

/// Singleton-класс для записи в файл чисел типа Integer
public class IntegerFileWriter implements FileWriter {

    private static IntegerFileWriter integerFileWriterInstance;

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

    private IntegerFileWriter() {}

    public static IntegerFileWriter getInstance() {
        if (integerFileWriterInstance == null) {
            integerFileWriterInstance = new IntegerFileWriter();
        }
        return integerFileWriterInstance;
    }

    @Override
    public void setLaunchSettings(LaunchSettings launchSettings) {
        this.launchSettings = launchSettings;
        if (semaphore == 0) {
            String totalPath =
                    launchSettings.getExternalFilePath() + Type.INT.getFilePath(launchSettings.getFileSignaturePrefix());
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

        try {
            String totalPath = launchSettings.getExternalFilePath() + Type.INT.getFilePath(launchSettings.getFileSignaturePrefix());

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
}
