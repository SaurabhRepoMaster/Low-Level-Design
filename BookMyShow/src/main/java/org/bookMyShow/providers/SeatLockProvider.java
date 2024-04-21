package org.bookMyShow.providers;

import org.bookMyShow.model.Seat;
import org.bookMyShow.model.Show;

import java.util.List;

public interface SeatLockProvider {

    void lockSeats(Show show, List<Seat> seats, String user);

    void unlockSeats(Show show,List<Seat> seats,String user);

    boolean validateLock(Show show,Seat seat,String user);

    List<Seat> getLockedSeats(Show show);
}
