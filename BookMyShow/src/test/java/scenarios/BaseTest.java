package scenarios;

import org.bookMyShow.api.*;
import org.bookMyShow.providers.InMemorySeatLockProvider;
import org.bookMyShow.service.*;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected BookingController bookingController;
    protected ShowController showController;
    protected TheatreController theatreController;
    protected MovieController movieController;
    protected PaymentsController paymentsController;

    protected void setupControllers(int lockTimeout, int allowedRetries) {
        final InMemorySeatLockProvider seatLockProvider = new InMemorySeatLockProvider(lockTimeout);
        final BookingService bookingService = new BookingService(seatLockProvider);
        final MovieService movieService = new MovieService();
        final ShowService showService = new ShowService();
        final TheatreService theatreService = new TheatreService();
        final SeatAvailabilityService seatAvailabilityService
                = new SeatAvailabilityService(seatLockProvider, bookingService);
        final PaymentsService paymentsService = new PaymentsService(allowedRetries, seatLockProvider);
        bookingController = new BookingController(showService, theatreService, bookingService);
        movieController = new MovieController(movieService);
        paymentsController = new PaymentsController(bookingService, paymentsService);
        theatreController = new TheatreController(theatreService);
        showController = new ShowController(theatreService, movieService, showService, seatAvailabilityService);
    }

    protected List<String> createSeats(TheatreController theatreController, String screen, int numRows, int numSeatsInRow) {
        List<String> seats = new ArrayList<>();
        for(int row = 0 ;row<numRows;row++)
        {
            for(int seatNo = 0;seatNo<numSeatsInRow;seatNo++)
            {
                String seat  = theatreController.createSeatsInScreen(row,seatNo,screen);
                seats.add(seat);
            }
        }
        return seats;
    }

    protected String setupScreen() {
        final String theatre = theatreController.createTheatre("Theatre 1");
        return theatreController.addScreensToTheatre("Screen 1", theatre);
    }

    protected void validateSeatsList(List<String> seatsListforUser, List<String> allSeatsInScreen, List<String> excludedSeats) {
        for (String includedSeat: allSeatsInScreen) {
            if (!excludedSeats.contains(includedSeat)) {
                Assert.assertTrue(seatsListforUser.contains(includedSeat));
            }
        }
        for (String excludedSeat: excludedSeats) {
            Assert.assertFalse(seatsListforUser.contains(excludedSeat));
        }
    }

}
