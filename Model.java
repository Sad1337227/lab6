import java.util.ArrayList;
import java.util.Random;

class Model {
  public DeliveryGuy deliveryguy;
  public Inspector inspector;
  public ArrayList<PoorCustomer> poorcustomer_list;
  public ArrayList<MediumCustomer> mediumcustomer_list;
  public ArrayList<RichCustomer> richcustomer_list;
  public ArrayList<Product> product;
  public ArrayList<Robber> robbers;
  public Shop shop;
  public Rent rent;
  private Random rand;

  public int RandInt(int UpperB) {
    return RandIntInterval(UpperB, 0);
  }

  public int RandIntInterval(int UpperB, int LowerB) {
    return rand.nextInt(UpperB - LowerB) + LowerB;
  }

  Model(ConfigManager configManager) {
    poorcustomer_list = new ArrayList<PoorCustomer>();
    mediumcustomer_list = new ArrayList<MediumCustomer>();
    richcustomer_list = new ArrayList<RichCustomer>();
    robbers = new ArrayList<Robber>();
    deliveryguy = new DeliveryGuy(configManager);
    inspector = new Inspector();
    rent = new Rent(configManager);
    shop = new Shop(configManager);
    rand = new Random();

    for (int i = 0; i < 15; ++i) {
      poorcustomer_list.add(new PoorCustomer());
      mediumcustomer_list.add(new MediumCustomer());
      richcustomer_list.add(new RichCustomer());
      robbers.add(new Robber(configManager));
    }

    return;
  }
}