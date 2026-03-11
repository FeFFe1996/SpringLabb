package com.example.springlabb.Movies;

import com.example.springlabb.DTO.CreateEntityDTO;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private Mapper movieMapper;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        this.movieMapper = new Mapper();
    }

    public void createNewMovie(CreateEntityDTO createEntityDTO){
        movieMapper.createEntity(createEntityDTO, movieRepository);
    }
}
