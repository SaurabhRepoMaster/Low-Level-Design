package org.bookMyShow.service;

import lombok.NonNull;
import org.bookMyShow.exception.BadRequestException;
import org.bookMyShow.exception.BookingNotFoundException;
import org.bookMyShow.exception.SeatPermanentlyUnavailableException;
import org.bookMyShow.model.Booking;
import org.bookMyShow.model.Seat;
import org.bookMyShow.model.Show;
import org.bookMyShow.providers.SeatLockProvider;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {

    private final Map<String, Booking> showBookings;
    private final SeatLockProvider seatLockProvider;

    public BookingService(SeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        showBookings = new HashMap<>();
    }

    public Booking getBooking(@NonNull final String bookingId) {
        if (!showBookings.containsKey(bookingId)) {
            throw new BookingNotFoundException();
        }
        return showBookings.get(bookingId);
    }

    public Booking createBooking(Show show, List<Seat> seats, String userId) {
        if(isAnySeatAlreadyBooked(show,seats))
            throw new SeatPermanentlyUnavailableException();
        seatLockProvider.lockSeats(show,seats,userId);
        final String bookingId = UUID.randomUUID().toString();
        final Booking newBooking = new Booking(bookingId, show, seats, userId);
        showBookings.put(bookingId,newBooking);
        return newBooking;
    }

    private boolean isAnySeatAlreadyBooked(Show show, List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for(Seat seat: seats){
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }

    public List<Seat> getBookedSeats(Show show) {
        return getAllBookings(show).stream().filter(Booking::isConfirmed).map(Booking::getSeatsBooked).
                flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<Booking> getAllBookings(@NonNull final Show show) {
        List<Booking> response = new ArrayList<>();
        for (Booking booking : showBookings.values()) {
            if (booking.getShow().getId().equals(show.getId())) {
                response.add(booking);
            }
        }
        return response;
    }

    public void confirmBooking(Booking booking, String user) {
        if(!booking.getUser().equals(user))
            throw new BadRequestException();
        for(Seat seat: booking.getSeatsBooked())
        {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new BadRequestException();
            }
        }
        booking.confirmBooking();

    }
}
