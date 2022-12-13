import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class Presenter {
  private View view;
  private Model model;
  private ConfigManager configManager;

  public Presenter(View mainView, Model model, ConfigManager configManager) {
    this.configManager = configManager;
    this.view = mainView;
    this.model = model;

    return;
  }

  public void StartSimulation() {
    new java.util.Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        Simulate_interactions();
      }
    }, 1, 1000);

    new java.util.Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        Simulate_rent();
      }
    }, 1, 3000 * 10);

    new java.util.Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        Simulate_rob();
      }
    }, 1, 6000 * 10);
  }

  private void Simulate_interactions() {
    int rand_ind = -1;
    int rand_list = model.RandInt(3);

    Customer cust = new Customer();
    Product prod = model.shop.ServeCustomer(rand_list);

    switch (rand_list) {
      case 0: {
        if (model.poorcustomer_list.size() > 1) {
          rand_ind = model.RandInt(model.poorcustomer_list.size());
          cust = model.poorcustomer_list.get(rand_ind);
        }

        break;
      }
      case 1: {
        if (model.mediumcustomer_list.size() > 1) {
          rand_ind = model.RandInt(model.mediumcustomer_list.size());
          cust = model.mediumcustomer_list.get(rand_ind);
        }

        break;
      }
      case 2: {
        if (model.richcustomer_list.size() > 1) {
          rand_ind = model.RandInt(model.richcustomer_list.size());
          cust = model.richcustomer_list.get(rand_ind);
        }

        break;
      }
    }

    int BuySum = cust.Buy(prod);
    model.shop.SetMoney(model.shop.GetMoney() + BuySum);
    String message =
        (cust.GetName() + " has bought " + prod.GetName() + " for " + BuySum + " yuans!");
    view.ShowMessage(message);

    int TipValue = cust.Tip(model.shop);
    message = (TipValue != -1 ? (cust.GetName() + " has tipped " + TipValue)
                              : (cust.GetName() + " couldn't tip because he had no money"));
    view.ShowMessage(message);

    model.deliveryguy.TryDeliver(model.shop);
    TipValue = model.shop.Tip(model.deliveryguy);
    message = (TipValue != -1 ? ("Shop " + model.shop.GetName() + " has tipped " + TipValue
                   + "$ for delivery/inspection")
                              : "Shop " + model.shop.GetName()
                + " couldn't tip for delivery/inspection because they do not have money!!");
    view.ShowMessage(message);

    model.inspector.IssueDefect(model.shop);

    return;
  }

  private void Simulate_rent() {
    view.ShowMessage("The assigned value is " + model.shop.ValueInit);
    int PriceToPay = model.rent.Pay(model.shop);
    String message =
        "Shop " + model.shop.GetName() + " has payed " + PriceToPay + "$ for monthly rent!!!";

    view.ShowMessage(message);

    return;
  }

  private void Simulate_rob() {
    int rand_rob = model.RandInt(model.robbers.size());

    Robber robber = model.robbers.get(rand_rob);

    String message = "Robber " + robber.GetName()
        + (robber.TryRob() ? " has robbed THE shop" : " couldn't rob THE shop!!");
    view.ShowMessage(message);

    return;
  }
}