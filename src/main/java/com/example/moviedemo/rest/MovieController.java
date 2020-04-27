package com.example.moviedemo.rest;

import java.util.ArrayList;
import java.util.List;

import com.example.moviedemo.dtos.request.MovieCreateRequestDto;
import com.example.moviedemo.dtos.request.MovieUpdateReuestDto;
import com.example.moviedemo.dtos.response.AppErrorResponse;
import com.example.moviedemo.dtos.response.AppResponse;
import com.example.moviedemo.model.Movie;
import com.example.moviedemo.service.MovieService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/movie/")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<AppResponse> createMovie(@Validated @RequestBody MovieCreateRequestDto dto,
            BindingResult bindingResult) {
        AppResponse appResponse = new AppResponse();
        if (bindingResult.hasErrors()) {
            AppErrorResponse configErrorResponse = new AppErrorResponse();
            List<AppErrorResponse.Error> errors = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                AppErrorResponse.Error rError = new AppErrorResponse.Error();
                rError.setCode(HttpStatus.BAD_REQUEST.value());
                rError.setUserMessage(fieldError.getField());
                rError.setInternalMessage(fieldError.getDefaultMessage());
                errors.add(rError);
                LOGGER.info(bindingResult.getFieldError().getDefaultMessage());
            }
            configErrorResponse.setErrors(errors);
            appResponse.setResponse(configErrorResponse);
            return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
        }
        Movie movie = movieService.createMovie(dto);
        appResponse.setDescription("Movie created successfully");
        appResponse.setResponse(movie);
        return new ResponseEntity<>(appResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<AppResponse> updateMovie(@Validated @RequestBody MovieUpdateReuestDto dto,
            BindingResult bindingResult) {
        AppResponse appResponse = new AppResponse();
        if (bindingResult.hasErrors()) {
            AppErrorResponse configErrorResponse = new AppErrorResponse();
            List<AppErrorResponse.Error> errors = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                AppErrorResponse.Error rError = new AppErrorResponse.Error();
                rError.setCode(HttpStatus.BAD_REQUEST.value());
                rError.setUserMessage(fieldError.getField());
                rError.setInternalMessage(fieldError.getDefaultMessage());
                errors.add(rError);
                LOGGER.error(bindingResult.getFieldError().getDefaultMessage());
            }
            configErrorResponse.setErrors(errors);
            appResponse.setResponse(configErrorResponse);
            return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
        }
        Movie movie = movieService.updateMovie(dto);
        appResponse.setDescription("Movie updated successfully");
        appResponse.setResponse(movie);
        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<AppResponse> getAllMovies() {
        AppResponse appResponse = new AppResponse();
        appResponse.setDescription("List of movies");
        appResponse.setResponse(movieService.getAll());
        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<AppResponse> getMovie(@PathVariable("id") Integer id) {
        AppResponse appResponse = new AppResponse();
        appResponse.setDescription("Movie");
        appResponse.setResponse(movieService.getById(id));
        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<AppResponse> deleteMovie(@PathVariable("id") Integer id) {
        AppResponse appResponse = new AppResponse();
        appResponse.setDescription("Deleted");
        movieService.delete(id);
        appResponse.setResponse(null);
        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }



}