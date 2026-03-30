import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        BookMyShowService service = new BookMyShowService();

        System.out.println("🎬 BookMyShow - Movie Ticket Booking System\n");
        System.out.println("=".repeat(60));

        Theater pvr = new Theater("T1", "PVR Cinemas", "Bangalore");
        Screen screen1 = new Screen("S1", "Audi 1", 50);
        Screen screen2 = new Screen("S2", "Audi 2", 50);
        pvr.addScreen(screen1);
        pvr.addScreen(screen2);
        service.addTheater(pvr);

        Theater inox = new Theater("T2", "INOX", "Bangalore");
        Screen screen3 = new Screen("S3", "Screen 1", 50);
        inox.addScreen(screen3);
        service.addTheater(inox);

        Movie movie1 = new Movie("M1", "Avatar 2", "Sci-Fi", 192, "English");
        Movie movie2 = new Movie("M2", "Pathaan", "Action", 146, "Hindi");

        Show show1 = new Show("SH1", movie1, pvr, screen1, 
            LocalDateTime.now().plusHours(2), ShowTiming.EVENING, 200.0);
        Show show2 = new Show("SH2", movie2, inox, screen3, 
            LocalDateTime.now().plusHours(3), ShowTiming.NIGHT, 180.0);
        
        service.addShow(show1);
        service.addShow(show2);

        User user = new User("U1", "Rahul Kumar", "rahul@example.com", "9876543210");
        service.registerUser(user);

        System.out.println("\n📍 Available Theaters:");
        for (Theater theater : service.getAllTheaters()) {
            System.out.println("  " + theater);
        }

        System.out.println("\n🎥 Available Shows:");
        for (Show show : service.getAllShows()) {
            System.out.println("  " + show);
            System.out.println("     Available seats: " + show.getAvailableSeats().size());
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("📌 Scenario 1: User books 3 seats for Avatar 2");
        System.out.println("=".repeat(60));

        List<Show> searchResults = service.searchShows("Avatar 2", "Bangalore");
        if (!searchResults.isEmpty()) {
            Show selectedShow = searchResults.get(0);
            System.out.println("\nSelected show: " + selectedShow);
            
            List<ShowSeat> availableSeats = selectedShow.getAvailableSeats();
            System.out.println("\nSample available seats:");
            for (int i = 0; i < Math.min(5, availableSeats.size()); i++) {
                ShowSeat ss = availableSeats.get(i);
                System.out.println(String.format("  %s - ₹%.2f", ss.getSeat(), ss.getPrice()));
            }

            List<String> selectedSeatIds = Arrays.asList("S1-A1", "S1-A2", "S1-A3");
            Coupon coupon = new Coupon("FIRST50", 10, 100, 300);
            System.out.println("\nApplying coupon: " + coupon);
            
            Booking booking = service.createBooking(user, selectedShow, selectedSeatIds, coupon);
            
            if (booking != null) {
                System.out.println("\n📝 Booking created:");
                System.out.println("  Booking ID: " + booking.getBookingId());
                System.out.println("  User: " + user.getName());
                System.out.println("  Show: " + selectedShow.getMovie().getTitle());
                System.out.println("  Seats: " + selectedSeatIds);
                System.out.println("  Total Amount: ₹" + booking.getTotalAmount());
                System.out.println("  Discount: ₹" + booking.getDiscountAmount());
                System.out.println("  Final Amount: ₹" + booking.getFinalAmount());
                System.out.println("  Status: " + booking.getStatus());

                System.out.println("\n💳 Processing Payment...");
                PaymentMethod payment = new CreditCardPayment("1234567890123456", "Rahul Kumar");
                boolean success = service.confirmBooking(booking.getBookingId(), payment);
                
                if (success) {
                    System.out.println("  Payment Status: SUCCESS");
                    System.out.println("  Booking Status: " + booking.getStatus());
                }
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("📌 Scenario 2: Booking Pathaan with UPI payment");
        System.out.println("=".repeat(60));

        Show show2Selected = service.getShow("SH2");
        List<String> seats2 = Arrays.asList("S3-C5", "S3-C6");
        
        Booking booking2 = service.createBooking(user, show2Selected, seats2, null);
        if (booking2 != null) {
            System.out.println("\n📝 Booking created:");
            System.out.println("  Total Amount: ₹" + booking2.getFinalAmount());
            
            System.out.println("\n💳 Processing Payment...");
            PaymentMethod upiPayment = new UpiPayment("rahul@upi");
            service.confirmBooking(booking2.getBookingId(), upiPayment);
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("📜 User Booking History");
        System.out.println("=".repeat(60));
        for (Booking b : user.getBookingHistory()) {
            System.out.println("  " + b);
        }

        System.out.println("\n✅ Demo completed!\n");
    }
}
