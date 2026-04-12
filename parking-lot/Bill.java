public class Bill {
    double totalAmount;
    int totalDuration;
    SpotType spotType;

    public Bill(double amt, int dur, SpotType type){
        this.totalAmount = amt;
        this.totalDuration = dur;
        this.spotType = type;
    }
}