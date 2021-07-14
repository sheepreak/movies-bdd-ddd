Feature: When user creates a movie, a movie is created

  Background:
    Given a movie with title "Star Wars" and director "George Lucas"

  Scenario: user create Movie
    When user creates Movie
    Then movie is created with title "Star Wars" and director "George Lucas"
