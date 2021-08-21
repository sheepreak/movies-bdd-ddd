package fr.sheepreak.movie.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateMovieOperation {

  @NotNull
  @Size(min = 1, max = 191)
  private String title;

  @NotNull
  @Pattern(regexp = "^[A-Za-z ]+$")
  private String director;

  public CreateMovieOperation() {}

  public CreateMovieOperation(String title, String director) {
    this.title = title;
    this.director = director;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }
}
