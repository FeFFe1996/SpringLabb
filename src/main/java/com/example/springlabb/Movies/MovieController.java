package com.example.springlabb.Movies;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    private MovieService movieService;

    private MovieController(MovieService movieService){
        this.movieService = movieService;
    }


}
