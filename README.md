# mower application

![workflow](https://github.com/javintx/mower/actions/workflows/gradle.yml/badge.svg)

## The mower challenge

The mower only have 3 commands that controls the mower movements:

- "L": Spin the mower to left.
- "R": Spin the mower to right.
- "M": Move the mower forward.

The mower has an initial position in the plateau represented by coordinates (X, Y). Assume that the square directly
North from (X, Y) is (X, Y + 1)

And the mower could face to one of the cardinal points:

- "N": North
- "E": East
- "S": South
- "W": West

## Assumptions

- The mower executes the command and then verify if it has any problem.
    - When mower move outside the plateau, the mower stops and does not occupy any coordinate in the plateau.
    - When mower crashed with any previous mower, the mower stops and occupy the same coordinate as crashed mower.

## Architecture

- Hexagonal architecture implemented with TDD in Java 11.
- JUnit5 with Mockito is used for unit tests.
    + mock-maker-inline extension used for final classes.

### Core hexagon

Hexagon with the domain and use case logic of the mower application.

### App hexagon

Hexagon with the application logic of the mower application.

# Requirements

- It is necessary to have Java 11 or above installed.
  See [here](https://www.oracle.com/es/java/technologies/javase-downloads.html) and follow instructions to download.

# HOW-TO

## Build the project

In the `rootDirectory` execute:

> gradlew clean build

## Execute tests

In the `rootDirectory` execute:

> gradlew clean test

## Execute the application

Pending to add script

# User manual

Pending to add user manual

# WHERE-TO

## Find the test reports

> {MODULE_NAME}/build/report/tests/test/index.html

Where "MODULE_NAME" could be each module of the mower application.

# TO-DO

- Command console: Refactor print message method and add new for user interaction.
- Create executable application jar.
- Complete README.md
- Add e2e tests
- Plateau could have 2 coordinates to define the size
    + The verifyAreInside method in the plateau could be moved to Coordinates class.
- Coordinates could have their own situation method and with the previous improvement, the coordinateX and coordinateY
  methods in Coordinates class could be deleted.
