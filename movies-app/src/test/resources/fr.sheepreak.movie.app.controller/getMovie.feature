Feature: When user requests a movie from the base by the title, the movie is returned

  Scenario: user gets a movie by title
    Given a movie exists with title "Jurassic Park" and director "Steven Spielberg"
    When user gets movie with title "Jurassic Park"
    Then the call returns a 200 status
    And a movie is returned with title "Jurassic Park" and director "Steven Spielberg"

  Scenario: user tries to get a non-existing movie
    Given no existing movie
    When user gets movie with title "Whatever"
    Then the call returns a 404 status