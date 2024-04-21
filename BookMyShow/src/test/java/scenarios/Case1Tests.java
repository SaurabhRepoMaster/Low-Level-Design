package scenarios;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.Date;
import java.util.List;

public class Case1Tests extends BaseTest{

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testCase1() {
        setupControllers(10,0);
        String user1 = "User1";
        String user2 = "User2";
        final String screen = setupScreen();
        final String movie = movieController.createMovie("Movie 1");
        final List<String> screen1SeatIds = createSeats(theatreController, screen, 2, 10);
        final String show = showController.createShow(movie, screen, new Date(), 2 * 60 * 60);

        // Validate that seats u1 received has all screen seats
        List<String> u1AvailableSeats = showController.getAvailableSeats(show);
        List<String> u1SelectedSeats = List.of(
                screen1SeatIds.get(0),
                screen1SeatIds.get(2),
                screen1SeatIds.get(5),
                screen1SeatIds.get(10)
        );
        final String bookingId = bookingController.createBooking(user1, show, u1SelectedSeats);
        paymentsController.paymentSuccess(bookingId, user1);
        final List<String> u2AvailableSeats = showController.getAvailableSeats(show);
        // Validate that u2 seats has all screen seats except the ones already booked by u1
        validateSeatsList(u2AvailableSeats, screen1SeatIds, u1SelectedSeats);
    }
}
