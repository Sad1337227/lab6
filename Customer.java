import java.util.Random;

public class Customer extends Person implements ITippable, IDiscountCard {
  @Override
  public int ApplyDiscount(int prod_left) {
    int Discount = (10 / (prod_left == 0 ? 1 : prod_left));
    return Discount == 0 ? 1 : Discount;
  }

  @Override
  public int Tip(Bank bank) {
    int TipValue = Math.abs((int) (bank.GetMoney() * 0.01));

    if (TipValue > this.GetMoney()) {
      return -1;
    }

    this.SetMoney(this.GetMoney() - TipValue);
    bank.SetMoney(bank.GetMoney() + TipValue);

    return TipValue;
  }

  public int Buy(Product product) {
    this.SetMoney(this.GetMoney() - product.GetPrice() / ApplyDiscount(product.GetQuantity()));
    return product.GetPrice();
  };

  Customer() {
    super();
    this.SetMoney(0);
  }
}