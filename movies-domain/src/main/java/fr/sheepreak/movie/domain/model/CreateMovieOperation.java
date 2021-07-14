package fr.sheepreak.movie.domain.model;

public class CreateMovieOperation {

  private String title;

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
