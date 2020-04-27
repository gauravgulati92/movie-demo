package com.example.moviedemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.moviedemo.dao.MovieRepository;
import com.example.moviedemo.dtos.request.MovieCreateRequestDto;
import com.example.moviedemo.exceptions.NotFoundException;
import com.example.moviedemo.model.Movie;
import com.example.moviedemo.service.impl.MovieServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class MovieServiceTest {

    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void initialze() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository, new ModelMapper());

    }

    @Test
    public void create_movie_test() throws Exception {
        MovieCreateRequestDto requestDto = new MovieCreateRequestDto();
        requestDto.setCategory("abc");
        requestDto.setTitle("test title");
        requestDto.setRating(3.2f);
        Movie expected = new Movie();
        expected.setCategory(requestDto.getCategory());
        expected.setRating(requestDto.getRating());
        expected.setTitle(requestDto.getTitle());
        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(expected);
        Movie actual = movieService.createMovie(requestDto);
        assertEquals(expected, actual, "unecpected result");
    }

    @Test
    public void find_movie_by_Id_if_present() {
        when(movieRepository.getOne(Mockito.anyInt())).thenReturn(buildMovie(2));
        Movie actual = movieService.getById(2);
        assertNotNull(actual);
    }

    @Test()
    public void test_find_movie_by_Id_if_not_present() {
        when(movieRepository.getOne(Mockito.anyInt())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            movieService.getById(2);
        });

    }

    @Test
    public void test_get_all_movies() {
        List<Movie> expected = new ArrayList<>();
        expected.add(buildMovie(1));
        expected.add(buildMovie(2));
        expected.add(buildMovie(3));
        expected.add(buildMovie(4));
        when(movieRepository.findAll()).thenReturn(expected);
        List<Movie> actual = movieService.getAll();
        assertEquals(expected, actual, "data is not same");

    }

    @AfterEach
    public void deinitialize() {
        movieService = null;
        movieRepository = null;
    }

    private Movie buildMovie(Integer id) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setRating(2.4f);
        movie.setTitle("test title");
        movie.setCategory("test category");
        return movie;
    }
}