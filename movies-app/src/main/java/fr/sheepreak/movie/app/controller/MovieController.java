package fr.sheepreak.movie.app.controller;

import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;
import fr.sheepreak.movie.domain.service.MovieService;
import fr.sheepreak.movie.repository.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping
  public ResponseEntity<Void> createMovie(
      @RequestBody @Valid CreateMovieOperation createMovieOperation) {
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(movieService.createMovie(createMovieOperation))
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping
  public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title) {
    try {
      return ResponseEntity.ok(movieService.getMovieByTitle(title));
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
