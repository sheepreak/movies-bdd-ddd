package fr.sheepreak.movie.app.controller;

public class MovieWorld {

  private Long id;
  private String title;
  private String director;

  public MovieWorld() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void reset() {
    id = null;
    title = null;
    director = null;
  }
}
