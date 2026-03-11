package com.example.springlabb;

import com.example.springlabb.Movies.Movie;
import com.example.springlabb.Movies.MovieRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringLabbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLabbApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(MovieRepository movieRepository){
        return  args -> {
            if (movieRepository.count() == 0) {
                Movie movie = new Movie();
                movie.setTitle("The Shawshank Redemption");
                movie.setDirector("Frank Darabont");
                movie.setYear("1994");
                movieRepository.save(movie);
            }
        };
    }
}
