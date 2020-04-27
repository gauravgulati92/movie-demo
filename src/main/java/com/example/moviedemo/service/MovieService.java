package com.example.moviedemo.service;

import java.util.List;

import com.example.moviedemo.dtos.request.MovieCreateRequestDto;
import com.example.moviedemo.dtos.request.MovieUpdateReuestDto;
import com.example.moviedemo.model.Movie;

public interface MovieService {

    public Movie createMovie(MovieCreateRequestDto requestDto);

    public Movie updateMovie(MovieUpdateReuestDto dto);

    public Movie getById(Integer id);

    public List<Movie> getAll();

    public void delete(Integer id);

}