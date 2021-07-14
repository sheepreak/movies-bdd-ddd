package fr.sheepreak.movie.app.controller;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:fr.sheepreak.movie.app.controller",
    plugin = "pretty",
    glue = "fr.sheepreak.movie.app.controller")
public class MovieControllerIT {}
