package scenarios;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class Case2Tests extends BaseTest{

    @Test
    public void testCase2() {
        setupControllers(10, 0);
        String user1 = "User1";
        String user2 = "User2";

        final String movie = movieController.createMovie("Movie 1");
        final String screen = setupScreen();
        final List<String> screen1SeatIds = createSeats(theatreController, screen, 2, 10);

        final String show = showController.createShow(movie, screen, new Date(), 2 * 60 * 60);

        List<String> u1AvailableSeats = showController.getAvailableSeats(show);

        // Validate that seats u1 received has all screen seats

        validateSeatsList(u1AvailableSeats, screen1SeatIds, List.of());
        List<String> u1SelectedSeats = List.of(
                screen1SeatIds.get(0),
                screen1SeatIds.get(2),
                screen1SeatIds.get(5),
                screen1SeatIds.get(10)
        );
        final String bookingId = bookingController.createBooking(user1, show, u1SelectedSeats);
        final List<String> u2AvailableSeats = showController.getAvailableSeats(show);

        // Validate that u2 seats has all screen seats except the ones already blocked by u1
        validateSeatsList(u2AvailableSeats, screen1SeatIds, u1SelectedSeats);
        paymentsController.paymentFailed(bookingId, user1);
        final List<String> u2AvailableSeatsAfterPaymentFailure = showController.getAvailableSeats(show);
        // Since u1's payment has failed so u2 should now get back all the seats.
        validateSeatsList(u2AvailableSeatsAfterPaymentFailure, screen1SeatIds, List.of());

    }
}
