package fr.sheepreak.movie.repository.hibernate;

import fr.sheepreak.movie.repository.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieJpaRepository extends CrudRepository<MovieEntity, Long> {
  Optional<MovieEntity> findByTitle(String title);
}
