public class ShowSeat {
  Seat seat;
  double price;
  SeatStatus seatStatus;
  
  public ShowSeat(Seat seat, double price, SeatStatus seatStatus) {
    this.seat = seat;
    this.price = price;
    this.seatStatus = seatStatus.AVAILABLE;
  }
}
