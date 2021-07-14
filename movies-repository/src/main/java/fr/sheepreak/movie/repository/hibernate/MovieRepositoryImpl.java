package fr.sheepreak.movie.repository.hibernate;

import fr.sheepreak.movie.domain.infrastructure.MovieRepository;
import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;
import fr.sheepreak.movie.repository.entity.MovieEntity;
import fr.sheepreak.movie.repository.exception.NotFoundException;
import fr.sheepreak.movie.repository.mapper.MovieMapper;
import org.mapstruct.factory.Mappers;

public class MovieRepositoryImpl implements MovieRepository {

  private MovieJpaRepository movieJpaRepository;

  private MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

  public MovieRepositoryImpl(MovieJpaRepository movieJpaRepository) {
    this.movieJpaRepository = movieJpaRepository;
  }

  @Override
  public Movie getById(Long id) {
    return movieMapper.movieEntitytoDto(
        movieJpaRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new NotFoundException(
                        String.format("No movie was found with given id %s", id))));
  }

  @Override
  public Movie save(CreateMovieOperation createMovieOperation) {
    MovieEntity movieEntity = new MovieEntity();

    movieMapper.createMovieFromOperation(movieEntity, createMovieOperation);

    return movieMapper.movieEntitytoDto(movieJpaRepository.save(movieEntity));
  }

  @Override
  public Movie getByTitle(String title) {
    return movieMapper.movieEntitytoDto(
        movieJpaRepository
            .findByTitle(title)
            .orElseThrow(
                () ->
                    new NotFoundException(
                        String.format("No movie was found with given title %s", title))));
  }
}
