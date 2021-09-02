package fr.sheepreak.movie.repository.hibernate;

import fr.sheepreak.movie.domain.infrastructure.MovieRepository;
import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;
import fr.sheepreak.movie.repository.entity.MovieEntity;
import fr.sheepreak.movie.repository.exception.NotFoundException;
import fr.sheepreak.movie.repository.mapper.MovieMapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

public class MovieRepositoryImpl implements MovieRepository {

  private final MovieJpaRepository movieJpaRepository;

  private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

  public MovieRepositoryImpl(MovieJpaRepository movieJpaRepository) {
    this.movieJpaRepository = movieJpaRepository;
  }

  @Override
  public Movie getById(Long id) {
    return movieMapper.movieEntityToDto(
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

    return movieMapper.movieEntityToDto(movieJpaRepository.save(movieEntity));
  }

  @Override
  public Movie getByTitle(String title) {
    return movieMapper.movieEntityToDto(
        movieJpaRepository
            .findByTitle(title)
            .orElseThrow(
                () ->
                    new NotFoundException(
                        String.format("No movie was found with given title %s", title))));
  }

  @Override
  public List<Movie> getByDirector(String director) {
    return movieJpaRepository.findAllByDirector(director).stream()
        .map(movieMapper::movieEntityToDto)
        .collect(Collectors.toList());
  }
}
