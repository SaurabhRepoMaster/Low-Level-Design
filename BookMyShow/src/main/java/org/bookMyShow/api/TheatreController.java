package org.bookMyShow.api;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bookMyShow.model.Screen;
import org.bookMyShow.model.Theatre;
import org.bookMyShow.service.TheatreService;

@AllArgsConstructor
public class TheatreController {

    final private TheatreService theatreService;

    public String createTheatre(@NonNull final String theatreName)
    {
        return theatreService.createTheatre(theatreName).getId();
    }

    public String addScreensToTheatre(@NonNull final String screenName, @NonNull final String theatreId){
        final Theatre theatre = theatreService.getTheatre(theatreId);
        return theatreService.createScreenInTheatre(screenName,theatre).getId();
    }

    public String createSeatsInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo,@NonNull final String screenId ){
        final Screen screen = theatreService.getScreen(screenId);
        return theatreService.createSeatInScreen(rowNo,seatNo,screen).getId();
    }
}
