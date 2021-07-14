package fr.sheepreak.movie.app.controller;

import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.service.MovieService;
import fr.sheepreak.movie.repository.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

  private MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping
  public ResponseEntity<Void> createMovie(@RequestBody CreateMovieOperation createMovieOperation) {
    try {
      URI location =
          ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(movieService.createMovie(createMovieOperation))
              .toUri();

      return ResponseEntity.created(location).build();
    } catch (NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
