package org.bookMyShow.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.bookMyShow.model.*;
import org.bookMyShow.service.MovieService;
import org.bookMyShow.service.SeatAvailabilityService;
import org.bookMyShow.service.ShowService;
import org.bookMyShow.service.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowController {

    private final TheatreService theatreService;
    private final MovieService movieService;
    private final ShowService showService;
    private final SeatAvailabilityService seatAvailabilityService;

    public String createShow(@NonNull final String movieId, @NonNull final String screenId, @NonNull final Date startTime,
                            @NonNull final Integer durationInSeconds)
    {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie,screen,startTime,durationInSeconds).getId();
    }

    public List<String> getAvailableSeats(@NonNull final String showId){
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}
