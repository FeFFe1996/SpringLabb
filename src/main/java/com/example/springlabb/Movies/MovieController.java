package com.example.springlabb.Movies;

import com.example.springlabb.DTO.CreateEntityDTO;
import com.example.springlabb.DTO.EntityDTO;
import com.example.springlabb.DTO.UpdateEntityDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    private MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @ModelAttribute("allMovies")
    public List<EntityDTO> getAllMovies(){
        return this.movieService.getMovieList();
    }

    @GetMapping("/")
    public String index(Model model){
        log.info("index");
        return "index";
    }

    @GetMapping("/movieList")
    public String movieList(Model model){
        log.info("movieList");
        model.addAttribute("movie", getAllMovies());
        return "movieList";
    }

    @GetMapping("/movieList/addMovie")
    public String addMovieForm(Model model){
        log.info("addMovie");
        model.addAttribute("addMovie", new Movie());
        return "addMovie";
    }

    @Transactional
    @PostMapping("/movieList/addMovie")
    public String addMovie(@Valid @ModelAttribute("addMovie") CreateEntityDTO createEntityDTO, BindingResult result){
        log.info("started creation of new movie entity");
        if (result.hasErrors()){
            return "addMovie";
        }
        movieService.createNewMovie(createEntityDTO);
        return "redirect:/movieList";
    }

    @GetMapping("/movieList/movieInfo/{id}")
    public String getMovieInfo(@PathVariable("id") String id, Model model) {
        log.info("MovieInfo. Controller received ID: {}", id);
        try{
            model.addAttribute("getMovieById", movieService.getMovieByID(id));
        }catch (MovieNotFoundException e){
            log.error(e.getMessage());
            return "redirect:/errorpage";
        }

        return "movieInfo";
    }

    @GetMapping("/movieList/movieInfo/{id}/updateMovie")
    public String updateMovieForm(@PathVariable("id") String id,Model model){
        log.info("updateMovie. Updating movie with ID: {}", id);
        try{
            EntityDTO entity = movieService.getMovieByID(id);

            UpdateEntityDTO updateEntityDTO = new UpdateEntityDTO(entity.id(), entity.title(), entity.year(), entity.description(), entity.director(), entity.length());

            model.addAttribute("updateMovie", updateEntityDTO);

        }catch (MovieNotFoundException e){
            log.error(e.getMessage());
            return "redirect:/errorpage";
        }
        return "updateMovie";
    }

    @Transactional
    @PostMapping("/movieList/movieInfo/{id}/updateMovie")
    public String updateMovie(@Valid @ModelAttribute("updateMovie") UpdateEntityDTO updateEntityDTO, BindingResult result){
        log.info("updating movie entity");
        if (result.hasErrors()){
            return "updateMovie";
        }
        movieService.updateMovie(updateEntityDTO);
        return "redirect:/movieList/movieInfo/{id}";
    }

    @GetMapping("/errorpage")
    public String getErrorPage(){
        log.info("return to error page");
        return "/errorpage";
    }




}
