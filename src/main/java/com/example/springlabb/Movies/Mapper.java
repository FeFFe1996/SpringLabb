package com.example.springlabb.Movies;

import com.example.springlabb.DTO.CreateEntityDTO;
import com.example.springlabb.DTO.EntityDTO;

import java.util.List;
import java.util.Optional;

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

    public List<EntityDTO> getEntities(MovieRepository movieRepository){
        List<EntityDTO> entityList = movieRepository.findAll().stream()
                .map(movie -> new EntityDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getDescription(),
                movie.getDirector(),
                movie.getLength())).toList();
        return entityList;
    }

    public EntityDTO getEntityByID(Movie movie){
        EntityDTO entity = new EntityDTO(movie.getId(), movie.getTitle(), movie.getYear(), movie.getDescription(), movie.getDirector(), movie.getLength());
        return entity;
    }
}
