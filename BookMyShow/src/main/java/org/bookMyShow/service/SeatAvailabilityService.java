package org.bookMyShow.service;

import org.bookMyShow.model.Seat;
import org.bookMyShow.model.Show;
import org.bookMyShow.providers.SeatLockProvider;

import java.util.ArrayList;
import java.util.List;

public class SeatAvailabilityService {

    private final SeatLockProvider seatLockProvider;
    private final BookingService bookingService;

    public SeatAvailabilityService(SeatLockProvider seatLockProvider, BookingService bookingService) {
        this.seatLockProvider = seatLockProvider;
        this.bookingService = bookingService;
    }

    public List<Seat> getAvailableSeats(Show show) {
        final List<Seat> allSeats = show.getScreen().getSeats();
        final List<Seat> unavailableSeats = getUnAvailableSeats(show);
        final List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;

    }

    private List<Seat> getUnAvailableSeats(Show show) {
        final List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }
}
