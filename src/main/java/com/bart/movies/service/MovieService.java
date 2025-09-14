package com.bart.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bart.movies.data.Movie;
import com.bart.movies.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> singleMovie(String imdbId) {
        return movieRepository.findByImdbId(imdbId);
    }
}
