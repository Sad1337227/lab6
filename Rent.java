import java.util.Random;
import java.util.Timer;

public class Rent {
  private int Area;
  private int PricePerSqM;

  public int Pay(Shop _shop) {
    int Sum = Area * PricePerSqM / 1000;
    _shop.SetMoney(_shop.GetMoney() - (int) (Sum));
    return Sum;
  }

  Rent(ConfigManager configManager) {
    Random rand = new Random();

    Area = configManager.GetValue("Area"); // EXPORT TO CFG
    PricePerSqM = configManager.GetValue("PricePerSquareMeter"); // EXPORT TO CFG
  }
}