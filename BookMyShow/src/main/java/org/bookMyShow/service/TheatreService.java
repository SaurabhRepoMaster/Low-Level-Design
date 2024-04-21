package org.bookMyShow.service;

import lombok.NonNull;
import org.bookMyShow.exception.ScreenNotFoundException;
import org.bookMyShow.exception.SeatNotFoundException;
import org.bookMyShow.exception.TheatreNotFoundException;
import org.bookMyShow.model.Screen;
import org.bookMyShow.model.Seat;
import org.bookMyShow.model.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {

    private final Map<String,Theatre> theatres;
    private final Map<String,Screen> screens;
    private final Map<String, Seat> seats;

    public TheatreService() {
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }

    public Theatre createTheatre(String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId,theatreName);
        theatres.put(theatreId,theatre);
        return theatre;
    }

    public Theatre getTheatre(String theatreId) {
        if (!theatres.containsKey(theatreId)) {
            throw new TheatreNotFoundException();
        }
        return theatres.get(theatreId);
    }

    public Screen createScreenInTheatre(String screenName, Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId,screenName,theatre);
        screens.put(screenId, screen);
        theatre.addScreen(screen);
        return screen;
    }

    public Screen getScreen(String screenId) {
        if (!screens.containsKey(screenId)) {
            throw new ScreenNotFoundException();
        }
        return screens.get(screenId);
    }

    public Seat createSeatInScreen(Integer rowNo, Integer seatNo, Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        screen.addSeat(seat);
        seats.put(seatId, seat);
        return seat;
    }

    public Seat getSeat(@NonNull final String seatId) {
        if (!seats.containsKey(seatId)) {
            throw new SeatNotFoundException();
        }
        return seats.get(seatId);
    }
}

