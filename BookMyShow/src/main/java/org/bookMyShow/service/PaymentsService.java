package org.bookMyShow.service;

import org.bookMyShow.exception.BadRequestException;
import org.bookMyShow.model.Booking;
import org.bookMyShow.providers.SeatLockProvider;

import java.util.HashMap;
import java.util.Map;

public class PaymentsService {

    private final Integer allowedRetries;
    Map<Booking, Integer> bookingFailures;
    private final SeatLockProvider seatLockProvider;

    public PaymentsService(Integer allowedRetries, SeatLockProvider seatLockProvider) {
        this.allowedRetries = allowedRetries;
        bookingFailures = new HashMap<>();
        this.seatLockProvider = seatLockProvider;
    }

    public void processPaymentFailed(Booking booking, String user) {
        if(!booking.getUser().equals(user))
            throw new BadRequestException();
        if(!bookingFailures.containsKey(booking))
        {
            bookingFailures.put(booking, 0);
        }
        final Integer currentFailuresCount = bookingFailures.get(booking);
        final Integer newFailuresCount = currentFailuresCount + 1;
        bookingFailures.put(booking, newFailuresCount);
        if (newFailuresCount > allowedRetries) {
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), booking.getUser());
        }
    }
}
