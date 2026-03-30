public class Coupon {
    private final String couponCode;
    private final double discountPercentage;
    private final double maxDiscountAmount;
    private final double minOrderAmount;
    private boolean isUsed;

    public Coupon(String couponCode, double discountPercentage, double maxDiscountAmount, double minOrderAmount) {
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.maxDiscountAmount = maxDiscountAmount;
        this.minOrderAmount = minOrderAmount;
        this.isUsed = false;
    }

    public double applyDiscount(double amount) {
        if (isUsed) {
            return 0;
        }
        if (amount < minOrderAmount) {
            return 0;
        }
        
        double discount = amount * (discountPercentage / 100.0);
        discount = Math.min(discount, maxDiscountAmount);
        isUsed = true;
        return discount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public boolean isUsed() {
        return isUsed;
    }

    @Override
    public String toString() {
        return String.format("%s: %.0f%% off (Max ₹%.0f, Min order ₹%.0f)", 
            couponCode, discountPercentage, maxDiscountAmount, minOrderAmount);
    }
}
