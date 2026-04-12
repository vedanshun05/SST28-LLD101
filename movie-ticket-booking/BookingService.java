import java.util.ArrayList;
import java.util.UUID;

public class BookingService {
  public Ticket bookTicket(User user, Show show, ArrayList<ShowSeat> seats) {

        for (ShowSeat ss : seats) {
            if (ss.seatStatus != SeatStatus.AVAILABLE) {
                throw new RuntimeException("Seat not available");
            }
        }

        for (ShowSeat ss : seats) {
            ss.seatStatus = SeatStatus.BOOKED;
        }

        String ticketId = UUID.randomUUID().toString();
        return new Ticket(ticketId, show, seats);
    }

    public void cancelTicket(Ticket ticket) {
        for (ShowSeat ss : ticket.seats) {
            ss.seatStatus = SeatStatus.AVAILABLE;
        }
    }
}
