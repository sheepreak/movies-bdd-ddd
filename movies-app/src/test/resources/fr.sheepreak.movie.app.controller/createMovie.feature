Feature: When user creates a movie, a movie is created

  Scenario: user creates Movie
    Given no existing movie
    When user creates a movie with title "Star Wars" and director "George Lucas"
    Then the call returns a 201 status
    And a movie is returned with title "Star Wars" and director "George Lucas"


  # integrer ici la feature de validation des champs du flim

  # partir d'une feature et dérouler tous les scénarios possibles selon les RDG

  # sonar ? démontrer la qualité