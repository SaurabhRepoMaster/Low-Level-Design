package org.bookMyShow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bookMyShow.providers.InMemorySeatLockProvider;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Getter
public class SeatLock {

    private final Show show;
    private final Seat seat;
    private final String lockedBy;
    private Date lockTime;
    private Integer timeoutInSeconds;

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        final Instant currentInstant = new Date().toInstant();
        return currentInstant.isBefore(lockInstant);
    }
}
