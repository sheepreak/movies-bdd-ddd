package fr.sheepreak.movie.domain.infrastructure;

import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;

import java.util.List;

public interface MovieRepository {

  Movie getById(Long id);

  Movie save(CreateMovieOperation createMovieOperation);

  Movie getByTitle(String title);

  List<Movie> getByDirector(String director);
}
