package org.bookMyShow.providers;

import lombok.NonNull;
import org.bookMyShow.exception.SeatTemporaryUnavailableException;
import org.bookMyShow.model.Seat;
import org.bookMyShow.model.SeatLock;
import org.bookMyShow.model.Show;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider{

    private final Integer lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider(@NonNull final Integer lockTimeout) {
        this.lockTimeout=lockTimeout;
        locks = new HashMap<>();
    }

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat:seats){
            if(isSeatLocked(show,seat))
                throw new SeatTemporaryUnavailableException();
        }
        for (Seat seat : seats)
        {
            lockSeat(show,seat,user,lockTimeout);
        }
    }

    private void lockSeat(Show show, Seat seat, String user, Integer timeoutInSeconds) {
        if(!locks.containsKey(show))
        {
            locks.put(show, new HashMap<>());
        }
        final SeatLock seatLock = new SeatLock(show,seat,user,new Date(),timeoutInSeconds);
        locks.get(show).put(seat,seatLock);
    }

    private boolean isSeatLocked(final Show show, final Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && locks.get(show).get(seat).isLockExpired();
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat : seats)
        {
            if(validateLock(show,seat,user)){
                unlockSeat(show, seat);
            }
        }
    }

    private void unlockSeat(Show show, Seat seat) {
        if(!locks.containsKey(show))
            return;
        locks.get(show).remove(seat);
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show,seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if(!locks.containsKey(show))
            return new ArrayList<>();
        final List<Seat> lockedSeats = new ArrayList<>();
        for(Seat seat : locks.get(show).keySet())
        {
            if(isSeatLocked(show,seat)){
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }
}
