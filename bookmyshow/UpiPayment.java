public class UpiPayment implements PaymentMethod {
    private final String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println(String.format("Processing UPI payment of ₹%.2f", amount));
        System.out.println("UPI ID: " + upiId);
        return true;
    }

    @Override
    public String getPaymentType() {
        return "UPI";
    }
}
