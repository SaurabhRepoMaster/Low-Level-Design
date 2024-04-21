package org.bookMyShow.service;

import org.bookMyShow.exception.ScreenAlreadyOccupiedException;
import org.bookMyShow.exception.ShowNotFoundException;
import org.bookMyShow.exception.TheatreNotFoundException;
import org.bookMyShow.model.Movie;
import org.bookMyShow.model.Screen;
import org.bookMyShow.model.Show;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {

    private final Map<String,Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }
    public Show createShow(Movie movie, Screen screen, Date startTime, Integer durationInSeconds) {
        if(!checkIfShowCreationAllowed(screen,startTime,durationInSeconds))
            throw new ScreenAlreadyOccupiedException();
        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId,movie,screen,startTime,durationInSeconds);
        shows.put(showId,show);
        return show;
    }

    private boolean checkIfShowCreationAllowed(Screen screen, Date startTime, Integer durationInSeconds) {
        return true;
    }

    public Show getShow(String showId) {
        if (!shows.containsKey(showId)) {
            throw new ShowNotFoundException();
        }
        return shows.get(showId);
    }
}
