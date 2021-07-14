package fr.sheepreak.movie.repository.configuration;

import fr.sheepreak.movie.domain.infrastructure.MovieRepository;
import fr.sheepreak.movie.repository.hibernate.MovieRepositoryImpl;
import fr.sheepreak.movie.repository.hibernate.MovieJpaRepository;
import fr.sheepreak.movie.repository.mapper.MovieMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("fr.sheepreak.movie.repository")
@EntityScan("fr.sheepreak.movie.repository")
public class MovieRepositoryConfiguration {

    @Bean
    public MovieRepository movieRepository(MovieJpaRepository movieJpaRepository) {
        return new MovieRepositoryImpl(movieJpaRepository);
    }
}
