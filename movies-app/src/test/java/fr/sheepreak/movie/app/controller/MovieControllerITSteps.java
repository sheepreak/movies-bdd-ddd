package fr.sheepreak.movie.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sheepreak.movie.domain.infrastructure.MovieRepository;
import fr.sheepreak.movie.domain.model.CreateMovieOperation;
import fr.sheepreak.movie.domain.model.Movie;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = MovieControllerITConfiguration.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
public class MovieControllerITSteps {

  private MockMvc mockMvc;

  private MovieWorld movieWorld;

  private MovieRepository movieRepository;

  private ObjectMapper objectMapper;

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

  @Given("a movie with title {string} and director {string}")
  public void aMovieWithTitleAndDirector(String title, String director) {
    movieWorld.setTitle(title);
    movieWorld.setDirector(director);
  }

  @When("user creates Movie")
  public void userCreatesMovie() throws Exception {
    MvcResult mvcResult =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/api/movies")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(
                        objectMapper.writeValueAsBytes(
                            new CreateMovieOperation(
                                movieWorld.getTitle(), movieWorld.getDirector()))))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();

    String location = mvcResult.getResponse().getHeader("Location");
    Assertions.assertNotNull(location);
    movieWorld.setId(Long.valueOf(location.substring(location.lastIndexOf("/") + 1)));
  }

  @Then("movie is created with title {string} and director {string}")
  public void movieIsCreatedWithTitleAndDirector(String title, String director) {
    Movie movie = movieRepository.getById(movieWorld.getId());

    Assertions.assertAll(
        () -> Assertions.assertEquals(movie.getTitle(), title),
        () -> Assertions.assertEquals(movie.getDirector(), director));
  }
}
