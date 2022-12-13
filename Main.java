import java.beans.Customizer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class Main {
  public static void main(String[] args) throws Exception {
    ConfigManager configManager = new ConfigManager();
    configManager.Load();

    Model model = new Model(configManager);
    View view = new View();
    Presenter presenter = new Presenter(view, model, configManager);

    presenter.StartSimulation();
    while (true) {
      try {
        Thread.sleep(10);
      } catch (Exception e) {
      }
    }
  }
};