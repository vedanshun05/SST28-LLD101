import java.util.ArrayList;

public class Ticket {
  String ticketId;
  Show show;
  ArrayList<ShowSeat> seats;
  
  public Ticket(String ticketId, Show show, ArrayList<ShowSeat> bookedSeats) {
    this.ticketId = ticketId;
    this.show = show;
    this.seats = bookedSeats;
  }
}
