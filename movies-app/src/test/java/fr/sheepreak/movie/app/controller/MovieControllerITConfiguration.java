package fr.sheepreak.movie.app.controller;

import fr.sheepreak.movie.app.config.MovieApplicationConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@TestConfiguration
@EnableWebMvc
@EnableAutoConfiguration
@Import(MovieApplicationConfiguration.class)
public class MovieControllerITConfiguration {

  @Bean
  //@Scope("cucumber-glue")
  public MovieWorld movieWorld() {
    return new MovieWorld();
  }
}
