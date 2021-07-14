Feature: When user requests a movie from the base by the title, the movie is returned

  Background:
    Given a movie inserted in base with title "Jurassic Park" and director "Steven Spielberg"

  Scenario: user gets a movie by title
    When user gets movie with title "Jurassic Park"
    Then movie is returned with title "Jurassic Park" and director "Steven Spielberg"