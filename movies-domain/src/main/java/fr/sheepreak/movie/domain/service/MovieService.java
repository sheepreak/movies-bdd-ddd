package fr.sheepreak.movie.domain.service;

import fr.sheepreak.movie.domain.infrastructure.MovieRepository;
import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;

import javax.transaction.Transactional;
import java.util.List;

public class MovieService {

  private final MovieRepository movieRepository;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Transactional
  public Long createMovie(CreateMovieOperation createMovieOperation) {
    return movieRepository.save(createMovieOperation).getId();
  }

  public Movie getMovieByTitle(String title) {
    return movieRepository.getByTitle(title);
  }

  public List<Movie> getMoviesByDirector(String director) {
    return movieRepository.getByDirector(director);
  }
}
