package fr.sheepreak.movie.repository.exception;

public class NotFoundException extends RuntimeException {

  private String message;

  public NotFoundException(String message) {
    super();
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
