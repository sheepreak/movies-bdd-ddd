# Movie API application

Entrypoint for the application. Will define the UI, as well as which repository shall be used by the domain app.

## Program

Needs to contain : 

- App launch class

- App configuration

- ReST controllers calling the domain services 

- A way to say which repository is being used (through instanciated beans is my guess, like instanciating the repository interface as a bean so it instanciates the actuel implementation of the repo as a bean)

- behavior tests