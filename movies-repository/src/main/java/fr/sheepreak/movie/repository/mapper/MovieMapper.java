package fr.sheepreak.movie.repository.mapper;

import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;
import fr.sheepreak.movie.repository.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface MovieMapper {

  @Mapping(source = "title", target = "title")
  @Mapping(source = "director", target = "director")
  void createMovieFromOperation(
      @MappingTarget MovieEntity movieEntity, CreateMovieOperation createMovieOperation);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "title", target = "title")
  @Mapping(source = "director", target = "director")
  Movie movieEntitytoDto(MovieEntity movieEntity);
}
