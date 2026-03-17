package com.example.springlabb.Movies;

import com.example.springlabb.DTO.CreateEntityDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Movie> getAllMovies(){
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
    public String addMovie(Model model){
        log.info("addMovie");
        return "addMovie";
    }

    @Transactional
    @PostMapping("/movieList/addMovie")
    public String addMovieForm(@Valid CreateEntityDTO createEntityDTO, Model model, RedirectAttributes redirectAttributes){
        log.info("started creation of new movie entity");
        //create code block for form handling
        return null;
    }


}
