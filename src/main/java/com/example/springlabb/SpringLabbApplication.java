package com.example.springlabb;

import com.example.springlabb.DTO.CreateEntityDTO;
import com.example.springlabb.Movies.Movie;
import com.example.springlabb.Movies.MovieRepository;
import com.example.springlabb.Movies.MovieService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringLabbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLabbApplication.class, args);
    }

}
