package fr.sheepreak.movie.app.config;

import fr.sheepreak.movie.domain.infrastructure.MovieRepository;
import fr.sheepreak.movie.domain.service.MovieService;
import fr.sheepreak.movie.repository.configuration.MovieRepositoryConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MovieRepositoryConfiguration.class)
public class MovieApplicationConfiguration {

  @Bean
  public MovieService movieService(MovieRepository movieRepository) {
    return new MovieService(movieRepository);
  }
}
