# 🎬 BookMyShow - Movie Ticket Booking System

## 🚀 Implementation
A movie ticket booking system with theaters, shows, seat management, and payment processing. Below is the file structure:

- `Movie.java`: Movie details with title, genre, duration, and language.
- `Theater.java`: Theater entity containing multiple screens at a location.
- `Screen.java`: Screen with seat initialization for different seat types.
- `Seat.java`: Individual seat with row, number, and type (VIP, Premium, Regular).
- `Show.java`: Movie show with timing, theater, screen, and seat pricing.
- `ShowSeat.java`: Seat instance for a specific show with booking and lock functionality.
- `User.java`: User with booking history.
- `Booking.java`: Booking entity with user, show, seats, payment details, and status.
- `BookMyShowService.java`: Core service managing theaters, shows, bookings, and seat locking.
- `PaymentMethod.java`: Payment strategy interface.
- `CreditCardPayment.java`, `UpiPayment.java`: Payment implementations.
- `PricingStrategy.java`: Interface for dynamic pricing based on timing.
- `WeekdayPricingStrategy.java`, `WeekendPricingStrategy.java`: Pricing strategies for different days.
- `Coupon.java`: Discount coupon with validation and usage tracking.
- `SeatType.java`, `BookingStatus.java`, `ShowTiming.java`: Enums for seat types, booking status, and show timings.
- `Main.java`: Demo with booking scenarios including coupon application and different payment methods.

## 📊 Class Diagram
*I will manually draw the class diagram and attach it here.*
