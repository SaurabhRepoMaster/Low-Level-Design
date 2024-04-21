package org.bookMyShow.model;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Seat {
    private final String id;
    private final int rowNumber;
    private final int seatNumber;

    public Seat(@NonNull final String id, @NonNull final int rowNumber, @NonNull final int seatNumber) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }
}
