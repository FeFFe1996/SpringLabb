package com.example.springlabb.Movies;

import com.example.springlabb.DTO.CreateEntityDTO;
import com.example.springlabb.DTO.EntityDTO;
import com.example.springlabb.DTO.UpdateEntityDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private Mapper movieMapper;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        this.movieMapper = new Mapper();
    }

    public List<EntityDTO> getMovieList(){
        return movieMapper.getEntities(movieRepository);
    }

    @Transactional
    public void createNewMovie(CreateEntityDTO createEntityDTO){
        try {
            movieMapper.createEntity(createEntityDTO, movieRepository);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public EntityDTO getMovieByID(String id) {
        if (id == null || id.isBlank()){
            throw new IllegalArgumentException("Id cannot be empty or null");
        }
        if (movieRepository.findById(Long.parseLong(id)).isEmpty()){
            throw new MovieNotFoundException("Movie with id "+id + " not found");
        }
        return movieRepository.findById(Long.parseLong(id)).map(movie -> movieMapper.getEntityByID(movie)).orElseThrow(() -> new MovieNotFoundException("movie with that id not found"));
    }

    @Transactional
    public void updateMovie(UpdateEntityDTO updateEntityDTO){
        try {
            movieMapper.updateMovieEntity(updateEntityDTO, movieRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
