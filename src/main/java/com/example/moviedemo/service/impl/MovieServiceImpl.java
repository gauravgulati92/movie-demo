package com.example.moviedemo.service.impl;

import java.util.List;

import com.example.moviedemo.dao.MovieRepository;
import com.example.moviedemo.dtos.request.MovieCreateRequestDto;
import com.example.moviedemo.dtos.request.MovieUpdateReuestDto;
import com.example.moviedemo.exceptions.NotFoundException;
import com.example.moviedemo.model.Movie;
import com.example.moviedemo.service.MovieService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    

    
    private MovieRepository movieRepository;

    private ModelMapper modelMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Movie createMovie(MovieCreateRequestDto requestDto) {
        Movie movie = new Movie();
        modelMapper.map(requestDto, movie);
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(MovieUpdateReuestDto dto) {
        Movie movie = getById(dto.getId());
        modelMapper.map(dto, movie);
        return movieRepository.save(movie);
    }

    @Override
    public Movie getById(Integer id) {
        Movie movie = movieRepository.getOne(id);
        if (movie == null) {
            LOGGER.error("movie not found with the {}:", id);

            throw new NotFoundException(String.format("movie not found with the id {}", id));

        }
        return movie;
    }

    @Override
    public List<Movie> getAll() {

        return movieRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        Movie movie = movieRepository.getOne(id);
        movieRepository.delete(movie);
        

    }

   

}