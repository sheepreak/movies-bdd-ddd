# Movie API Repository app

Will contain one implementation of the repository used by the app to persist objects from the domain into a database.

This instance will use an H2 or PGSQL base as well as JPA and Hibernate.

## Program

Needs to contain :

- Database configuration and communication

- JPA repository containing all the necessary methods

- Repository class that implements the repository from the domain and uses the JPA repo

- Mappers for the entities into the DTOs and eventual operations

- Unit tests for database insertion behavior ?