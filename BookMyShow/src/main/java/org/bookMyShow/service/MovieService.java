package org.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.bookMyShow.exception.MovieNotFoundException;
import org.bookMyShow.exception.TheatreNotFoundException;
import org.bookMyShow.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class MovieService {

    private final Map<String, Movie> movies;
    public MovieService(){
        movies = new HashMap<>();
    }
    public Movie createMovie(String movieName) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId,movieName);
        movies.put(movieId,movie);
        return movie;
    }

    public Movie getMovie(String movieId)
    {
        if (!movies.containsKey(movieId)) {
            throw new MovieNotFoundException();
        }
        return movies.get(movieId);
    }
}
