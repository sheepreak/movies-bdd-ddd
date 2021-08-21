Feature: When user creates a movie, a movie is created

  Scenario: user creates Movie
    Given no existing movie
    When user creates a movie with title "Star Wars" and director "George Lucas"
    Then the call returns a 201 status
    And a movie is returned with title "Star Wars" and director "George Lucas"

  Scenario: user creates a Movie with bad title and director
    Given no existing movie
    When user creates a movie with title "Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Alien, Flesh Eating, Hellbound, Zombified Living Dead Part 2 bis" and director "23 456"
    Then the call returns a 400 status
    And has 2 validation errors
  # integrer ici la feature de validation des champs du flim

  # partir d'une feature et dérouler tous les scénarios possibles selon les RDG

  # sonar ? démontrer la qualité