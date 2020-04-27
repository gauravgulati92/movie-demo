package com.example.moviedemo.rest;

import com.example.moviedemo.service.MovieService;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovieControllerTest {

    
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @BeforeEach
    public void before(){
        MockitoAnnotations.initMocks(this);
        movieController=new MovieController(movieService);

    }


}