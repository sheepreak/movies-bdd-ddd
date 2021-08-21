package fr.sheepreak.movie.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sheepreak.movie.domain.infrastructure.MovieRepository;
import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = MovieControllerITConfiguration.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
public class MovieControllerITSteps {
  private static final String MOVIE_URL = "/api/movies";

  private MockMvc mockMvc;
  private MovieWorld movieWorld;
  private MovieRepository movieRepository;
  private ObjectMapper objectMapper;

  private ResultActions resultActions;

  public MovieControllerITSteps(
      MockMvc mockMvc,
      MovieWorld movieWorld,
      MovieRepository movieRepository,
      ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.movieWorld = movieWorld;
    this.movieRepository = movieRepository;
    this.objectMapper = objectMapper;
  }

  @BeforeEach
  void init() {
    resultActions = null;
    movieWorld.reset();
  }

  @Given("a movie with title {string} and director {string}")
  public void aMovieWithTitleAndDirector(String title, String director) {
    movieWorld.setTitle(title);
    movieWorld.setDirector(director);
  }

  @When("user creates a movie with title {string} and director {string}")
  public void userCreatesMovie(String title, String director) throws Exception {
    resultActions =
        mockMvc.perform(
            MockMvcRequestBuilders.post(MOVIE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(
                    objectMapper.writeValueAsBytes(new CreateMovieOperation(title, director))));

    String location = resultActions.andReturn().getResponse().getHeader("Location");
    assertNotNull(location);
    movieWorld.setId(Long.valueOf(location.substring(location.lastIndexOf("/") + 1)));
  }

  @Then("a movie is returned with title {string} and director {string}")
  public void movieIsReturnedWithTitleAndDirector(String title, String director) {
    if (Objects.isNull(movieWorld.getTitle()) || Objects.isNull(movieWorld.getDirector())) {
      Movie movie = movieRepository.getById(movieWorld.getId());
      movieWorld.setDirector(movie.getDirector());
      movieWorld.setTitle(movie.getTitle());
    }

    assertAll(
        () -> assertEquals(title, movieWorld.getTitle()),
        () -> assertEquals(director, movieWorld.getDirector()));
  }

  @Given("a movie exists with title {string} and director {string}")
  public void aMovieInsertedInBaseWithTitleAndDirector(String title, String director) {
    movieRepository.save(new CreateMovieOperation(title, director));
  }

  @When("user gets movie with title {string}")
  public void userGetsMovieWithTitle(String title) throws Exception {
    resultActions = mockMvc.perform(MockMvcRequestBuilders.get(MOVIE_URL).param("title", title));

    Movie movie =
        objectMapper.readValue(
            resultActions.andReturn().getResponse().getContentAsString(), Movie.class);
    movieWorld.setId(movie.getId());
    movieWorld.setTitle(movie.getTitle());
    movieWorld.setDirector(movie.getDirector());
  }

  @Given("no existing movie")
  @Sql("/sql/cleanMovies.sql")
  public void noExistingMovie() {}

  @Then("the call returns a {int} status")
  public void theCallReturnsAStatus(int status) throws Exception {
    resultActions.andExpect(MockMvcResultMatchers.status().is(status));
  }
}
