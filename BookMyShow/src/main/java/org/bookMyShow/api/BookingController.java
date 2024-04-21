package org.bookMyShow.api;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bookMyShow.model.Seat;
import org.bookMyShow.model.Show;
import org.bookMyShow.service.BookingService;
import org.bookMyShow.service.ShowService;
import org.bookMyShow.service.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {

    private final ShowService showService;
    private final TheatreService theatreService;
    private final BookingService bookingService;

    public String createBooking(@NonNull final String userId, @NonNull final String showId, @NonNull final List<String> seatsIds)
    {
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(show,seats,userId).getId();
    }
}
