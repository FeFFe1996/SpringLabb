package com.example.springlabb.Movies;

import com.example.springlabb.DTO.CreateEntityDTO;
import com.example.springlabb.DTO.UpdateEntityDTO;

public class Mapper {
    public void createEntity(CreateEntityDTO createEntityDTO, MovieRepository movieRepository){
        Movie movie = new Movie();
        movie.setTitle(createEntityDTO.title());
        movie.setDescription(createEntityDTO.description());
        movie.setYear(createEntityDTO.year());
        movie.setDirector(createEntityDTO.director());
        movie.setLength(createEntityDTO.length());
        movieRepository.save(movie);
    }
}
