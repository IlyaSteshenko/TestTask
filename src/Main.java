import managers.LaunchManager;
import managers.LaunchSettings;

public class Main {
    public static void main(String[] args) {
        LaunchSettings launchSettings = new LaunchSettings(args);
        LaunchManager launchManager = new LaunchManager(launchSettings);
        launchManager.launch();
    }
}
