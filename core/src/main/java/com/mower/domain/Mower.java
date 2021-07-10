package com.mower.domain;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;

public class Mower {
  private Coordinates coordinates;
  private CardinalPoint cardinalPoint;

  public Mower(final Coordinates initialCoordinates, CardinalPoint cardinalPoint) {
    this.coordinates = initialCoordinates;
    this.cardinalPoint = cardinalPoint;
  }

  public Coordinates coordinates() {
    return coordinates;
  }

  public CardinalPoint cardinalPoint() {
    return cardinalPoint;
  }

  public void executeCommand(Command command) {
    command.execute(this);
  }

  public void moveForward() {
    switch (this.cardinalPoint) {
      case NORTH:
        this.coordinates = new Coordinates(coordinates.coordinateX(), coordinates.coordinateY() + 1);
        break;
      case EAST:
        this.coordinates = new Coordinates(coordinates.coordinateX() + 1, coordinates.coordinateY());
        break;
      case SOUTH:
        this.coordinates = new Coordinates(coordinates.coordinateX(), coordinates.coordinateY() - 1);
        break;
      case WEST:
        this.coordinates = new Coordinates(coordinates.coordinateX() - 1, coordinates.coordinateY());
        break;
    }
  }

  public void spinRight() {
    switch (this.cardinalPoint) {
      case NORTH:
        this.cardinalPoint = EAST;
        break;
      case EAST:
        this.cardinalPoint = SOUTH;
        break;
      case SOUTH:
        this.cardinalPoint = WEST;
        break;
      case WEST:
        this.cardinalPoint = NORTH;
        break;
    }
  }

  public void spinLeft() {
    switch (this.cardinalPoint) {
      case NORTH:
        this.cardinalPoint = WEST;
        break;
      case EAST:
        this.cardinalPoint = NORTH;
        break;
      case SOUTH:
        this.cardinalPoint = EAST;
        break;
      case WEST:
        this.cardinalPoint = SOUTH;
        break;
    }
  }
}
