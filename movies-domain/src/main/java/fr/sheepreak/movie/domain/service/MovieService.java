package fr.sheepreak.movie.domain.service;

import fr.sheepreak.movie.domain.infrastructure.MovieRepository;
import fr.sheepreak.movie.domain.model.CreateMovieOperation;

import javax.transaction.Transactional;

public class MovieService {

  private MovieRepository movieRepository;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Transactional
  public Long createMovie(CreateMovieOperation createMovieOperation) {
    return movieRepository.save(createMovieOperation).getId();
  }
}
