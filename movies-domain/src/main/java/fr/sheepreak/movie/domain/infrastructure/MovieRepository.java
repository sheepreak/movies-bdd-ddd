package fr.sheepreak.movie.domain.infrastructure;

import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;

public interface MovieRepository {

  Movie getById(Long id);

  Movie save(CreateMovieOperation createMovieOperation);

  Movie getByTitle(String title);
}
