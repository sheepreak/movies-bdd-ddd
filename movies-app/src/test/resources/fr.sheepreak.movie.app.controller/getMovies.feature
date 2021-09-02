Feature: User requests a list of movies

  Scenario: user gets movies by their Director
    Given a movie exists with title "The Shining" and director "Stanley Kubrick"
    And a movie exists with title "Clockwork Orange" and director "Stanley Kubrick"
    When user gets movie with director "Stanley Kubrick"
    Then the call returns a 200 status
    And 2 movies are returned
