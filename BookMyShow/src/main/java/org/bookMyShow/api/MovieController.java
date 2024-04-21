package org.bookMyShow.api;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bookMyShow.service.MovieService;

@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    public String createMovie(@NonNull final String movieName){
        return movieService.createMovie(movieName).getId();
    }
}
