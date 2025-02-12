import managers.LaunchManager;
import managers.LaunchSettings;

public class Main {
    public static void main(String[] args) {

        /// Настройка параметров программы
        LaunchSettings launchSettings = new LaunchSettings(args);
        LaunchManager launchManager = new LaunchManager(launchSettings);

        /// Запуск программы
        launchManager.launch();
    }
}
