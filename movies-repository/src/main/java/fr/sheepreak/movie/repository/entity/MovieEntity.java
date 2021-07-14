package fr.sheepreak.movie.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "movie")
public class MovieEntity {

  @Id
  @GeneratedValue(generator = "movie_seq")
  @Column
  private Long id;

  @Column(unique = true)
  private String title;

  @Column private String director;

  public MovieEntity() {}

  public Long getId() {
    return id;
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
