package entities.writers;

import enums.Type;
import managers.LaunchSettings;

import java.io.File;
import java.io.IOException;

/// Singleton-класс для записи в файл чисел типа Float
public class FloatFileWriter implements FileWriter {

    private static FloatFileWriter floatFileWriterInstance;

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

    private FloatFileWriter() {}

    public static FloatFileWriter getInstance() {
        if (floatFileWriterInstance == null) {
            floatFileWriterInstance = new FloatFileWriter();
        }
        return floatFileWriterInstance;
    }

    @Override
    public void setLaunchSettings(LaunchSettings launchSettings) {
        this.launchSettings = launchSettings;
        if (semaphore == 0) {
            String totalPath =
                    launchSettings.getExternalFilePath() + Type.FLOAT.getFilePath(launchSettings.getFileSignaturePrefix());
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
            String totalPath = launchSettings.getExternalFilePath() + Type.FLOAT.getFilePath(launchSettings.getFileSignaturePrefix());

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
