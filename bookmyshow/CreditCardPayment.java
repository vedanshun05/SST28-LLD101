public class CreditCardPayment implements PaymentMethod {
    private final String cardNumber;
    private final String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println(String.format("Processing Credit Card payment of ₹%.2f", amount));
        System.out.println("Card: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        return true;
    }

    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
}
