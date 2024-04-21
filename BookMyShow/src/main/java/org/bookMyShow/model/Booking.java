package org.bookMyShow.model;

import lombok.Getter;
import lombok.NonNull;
import org.bookMyShow.exception.InvalidStateException;

import java.util.List;

@Getter
public class Booking {

    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking(@NonNull String id, Show show, List<Seat> seatsBooked, String user) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        bookingStatus=BookingStatus.CREATED;
    }

    public Boolean isConfirmed(){
        return this.bookingStatus==BookingStatus.CONFIRMED;
    }

    public void confirmBooking(){
        if(this.bookingStatus!=BookingStatus.CREATED)
            throw new InvalidStateException();
        this.bookingStatus=BookingStatus.CONFIRMED;
    }

    public void expireBooking(){
        if(this.bookingStatus!=BookingStatus.CREATED)
            throw new InvalidStateException();
        this.bookingStatus=BookingStatus.EXPIRED;
    }

}
