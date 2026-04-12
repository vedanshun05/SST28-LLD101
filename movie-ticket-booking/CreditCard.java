
public class CreditCard implements PaymentStrategy{
  public void pay(double amount) {
    System.out.println("Payment of " + amount + " made using Credit Card.");
  }  
}
