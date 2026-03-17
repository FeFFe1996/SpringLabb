package com.example.springlabb.Movies;

import com.example.springlabb.DTO.CreateEntityDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private Mapper movieMapper;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        this.movieMapper = new Mapper();
    }

    public List<Movie> getMovieList(){
        return movieRepository.findAll();
    }

    @Transactional
    public void createNewMovie(CreateEntityDTO createEntityDTO){
        try {
            movieMapper.createEntity(createEntityDTO, movieRepository);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
