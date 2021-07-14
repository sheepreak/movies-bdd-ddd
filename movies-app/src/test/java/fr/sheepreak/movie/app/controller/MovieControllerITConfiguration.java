package fr.sheepreak.movie.app.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@TestConfiguration
@EnableWebMvc
@EnableAutoConfiguration
public class MovieControllerITConfiguration {

    @Bean
    public MovieWorld movieWorld() {
        return new MovieWorld();
    }
}
