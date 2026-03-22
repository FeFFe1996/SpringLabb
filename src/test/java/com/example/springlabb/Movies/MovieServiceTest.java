package com.example.springlabb.Movies;

import com.example.springlabb.DTO.CreateEntityDTO;
import com.example.springlabb.DTO.EntityDTO;
import com.example.springlabb.DTO.UpdateEntityDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Spy
    private Mapper movieMapper;

    @InjectMocks
    private MovieService movieService;

    @Test
    void createNewMovieWhenDataIsValid(){
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("test");
        CreateEntityDTO dto = new CreateEntityDTO("test", "2016", "a test", "test director", "2:32");

        when(movieRepository.findAll()).thenReturn(List.of(movie));

        movieService.createNewMovie(dto);

        var result = movieService.getMovieList();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.getFirst().title()).isEqualTo("test");
    }

    @Test
    void createNewMovieShouldReturnErrorIfFailureHappens(){
        CreateEntityDTO dto = new CreateEntityDTO("test", "2016", "a test", "test director", "2:32");

        doThrow(new RuntimeException("Database Failure")).when(movieRepository).save(any());

        assertThrows(RuntimeException.class, () -> movieService.createNewMovie(dto));
    }

    @Test
    void getMovieByIDShouldThrowErrorWhenParameterIsNullOrBlank(){
        assertThrows(IllegalArgumentException.class, () -> movieService.getMovieByID(""));
        assertThrows(IllegalArgumentException.class, () -> movieService.getMovieByID(null));

    }

    @Test
    void getMovieByIdShouldReturnErrorWhenIdNotFound(){
        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieByID("1"));

    }

    @Test
    void getMovieByIdShouldReturnIfMovieExists(){
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("test");
        CreateEntityDTO dto = new CreateEntityDTO("test", "2016", "a test", "test director", "2:32");

        when(movieRepository.findById(1l)).thenReturn(Optional.of(movie));
        movieService.createNewMovie(dto);
        var result = movieService.getMovieByID("1");

        assertThat(result.id()).isEqualTo(1l);
        assertThat(result.title()).isEqualTo("test");
    }

    @Test
    void updateMovieShouldFailWhenMovieIdDoesntExist(){
        UpdateEntityDTO dto = new UpdateEntityDTO(1l, "testChange", "2016", "a test", "test director", "2:32");

        assertThrows(RuntimeException.class, () -> movieService.updateMovie(dto));
    }

    @Test
    void updateMovieShouldReturnSuccess(){
        Movie movie = new Movie();
        movie.setId(1l);
        UpdateEntityDTO dto = new UpdateEntityDTO(1l, "testChange", "2016", "a test", "test director", "2:32");

        when(movieRepository.existsById(1l)).thenReturn(true);
        when(movieRepository.getMovieById(1l)).thenReturn(movie);

        movieService.updateMovie(dto);
        assertThat(movie.getTitle()).isEqualTo("testChange");

    }
}