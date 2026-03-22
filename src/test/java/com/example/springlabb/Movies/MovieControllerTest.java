package com.example.springlabb.Movies;

import com.example.springlabb.DTO.EntityDTO;
import com.example.springlabb.DTO.UpdateEntityDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Test
    void listMoviesShouldReturnMovieListView() throws Exception {
        when(movieService.getMovieList()).thenReturn(List.of());

        mockMvc.perform(get("/movieList"))
                .andExpect(status().isOk())
                .andExpect(view().name("movieList"))
                .andExpect(model().attributeExists("movie"));
    }

    @Test
    void addMovieShouldReturnFormView() throws Exception{
        mockMvc.perform(get("/movieList/addMovie"))
                .andExpect(status().isOk())
                .andExpect(view().name("addMovie"))
                .andExpect(model().attributeExists("addMovie"));
    }

    @Test
    void addMovieShouldReturnToMovieListWhenSuccessful() throws Exception{
        mockMvc.perform(post("/movieList/addMovie")
                .param("title", "test")
                .param("year", "2001")
                .param("description", "this is a test")
                .param("director", "test director")
                .param("length", "2:32"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movieList"));
    }

    @Test
    void addMovieShouldReturnFormViewWhenThereIsErrors() throws Exception{
        mockMvc.perform(post("/movieList/addMovie")
                        .param("title", "test")
                        .param("year", "")
                        .param("description", "this is a test")
                        .param("director", "")
                        .param("length", "2:32"))
                .andExpect(status().isOk())
                .andExpect(view().name("addMovie"))
                .andExpect(model().hasErrors());
    }

    @Test
    void MovieInfoShouldReturnEntityDto() throws Exception{
        EntityDTO entityTest = new EntityDTO(1l, "test", "1996", "test description", "test director", "2:32");
        String movieId = "1";

        when(movieService.getMovieByID(movieId)).thenReturn(entityTest);

        mockMvc.perform(get("/movieList/movieInfo/" + movieId))
                .andExpect(status().isOk())
                .andExpect(view().name("movieInfo"))
                .andExpect(model().attributeExists("getMovieById"));
    }

    @Test
    void MovieInfoShouldReturnErrorWhenMovieNotFoundAndRedirect() throws Exception {
        String movieId = "2";

        when(movieService.getMovieByID(movieId))
                .thenThrow(new MovieNotFoundException("Movie with id " + movieId + " not found"));

        mockMvc.perform(get("/movieList/movieInfo/" + movieId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/errorpage"));
    }

    @Test
    void updateMovieShouldReturnFormView() throws Exception{
        EntityDTO entityTest = new EntityDTO(1l, "test", "1996", "test description", "test director", "2:32");
        String movieId = "1";

        when(movieService.getMovieByID(movieId)).thenReturn(entityTest);

        mockMvc.perform(get("/movieList/movieInfo/" + movieId +"/updateMovie"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateMovie"))
                .andExpect(model().attributeExists("updateMovie"));
    }

    @Test
    void updateMovieShouldReturnErrorWhenMovieNotFoundAndRedirect() throws Exception {
        String movieId = "2";

        when(movieService.getMovieByID(movieId))
                .thenThrow(new MovieNotFoundException("Movie with id " + movieId + " not found"));

        mockMvc.perform(get("/movieList/movieInfo/" + movieId +"/updateMovie"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/errorpage"));
    }


    @Test
    void errorPageShouldReturnErrorPage() throws Exception{
        mockMvc.perform(get("/errorpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("/errorpage"));
    }

}