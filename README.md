# mower

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

- The coordinates are always positive numbers.
- The mower executes the command and then verify if it has any problem.
    + When mower move outside the plateau, the mower stops and does not occupy any coordinate in the plateau.
    + When mower crashed with any previous mower, the mower stops and occupy the same coordinate as crashed mower.
- The application is command line interactive. The user experience is guided through messages.

## Architecture

- Hexagonal architecture implemented using Test-driven development
  ([TDD](https://en.wikipedia.org/wiki/Test-driven_development)) in Java 11.
- JUnit5 with Mockito is used for unit tests.
    + mock-maker-inline extension used for final classes.
- The mower application has been done respecting the [SOLID](https://en.wikipedia.org/wiki/SOLID)
  and [KISS](https://en.wikipedia.org/wiki/KISS_principle) principles and applying Clean Code as far as possible.

### Core hexagon

Hexagon with the domain and the use case logic of the mower application.

### App hexagon

Hexagon with the application logic of the mower application. It contains the java main method.

# Requirements

- It is necessary to have Java 11 or above installed.
  See [here](https://www.oracle.com/es/java/technologies/javase-downloads.html) and follow instructions to download.

---

# HOW-TO

## Build the project

In the `rootDirectory` execute:

> gradlew clean build

## Execute tests

In the `rootDirectory` execute:

> gradlew clean test

## Execute the application

In the `rootDirectory/build/libs` execute:

> java -jar mower-1.0.0.jar

# User manual

After execute the application, the user will be guided through console:

- First, it is necessary to insert the plateau dimensions by insert the width, and the height (represented by a positive
  numbers).
- Secondly, it is necessary to insert the mower coordinates (represented by a positive numbers) and orientation
  (represented by one of the four cardinal points: 'N', 'E', 'S', W').
    + A mower can only be valid if their coordinates are inside the plateau and there is no previous mower in the same
      coordinates.
- In third place, it is necessary to insert the mower commands (represented by the letters: 'L', 'R' or 'M').

After the commands inserted, the mower executes them and prints their final position if is inside the plateau.

- At the end, it is necessary to insert 'N'/'n'' to continue adding mowers and commands, or 'Y'/'y' to exiting the
  application.

---

# WHERE-TO

## Find the test reports

> {MODULE_NAME}/build/report/tests/test/index.html

Where "MODULE_NAME" could be each module of the mower application.

--- 

# TO-DO

- MowerApp: Find another way to inject for test purposes.
- Add e2e tests
- Plateau could have 2 coordinates to define the size
    + The verifyAreInside method in the plateau could be moved to Coordinates class.
- Coordinates could have their own situation method and with the previous improvement, the coordinateX and coordinateY
  methods in Coordinates class could be deleted.
