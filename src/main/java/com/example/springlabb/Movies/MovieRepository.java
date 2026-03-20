package com.example.springlabb.Movies;

import org.springframework.data.repository.ListCrudRepository;

public interface MovieRepository extends ListCrudRepository<Movie, Long> {
    Movie getMovieById(Long id);
}
