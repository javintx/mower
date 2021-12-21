package com.mower.domain.valueobjects;

import com.mower.domain.CardinalPoint;
import com.mower.domain.exception.CoordinatesAreOutside;
import com.mower.domain.exception.CoordinatesMustBePositiveNumbers;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;

public final class Coordinates {
  private static final Map<CardinalPoint, Function<Coordinates, Coordinates>> MOVEMENTS = Map.of(
      NORTH, Coordinates::upward,
      EAST, Coordinates::forward,
      SOUTH, Coordinates::downward,
      WEST, Coordinates::backward
  );

  private static final int MINIMUM_COORDINATE_VALUE = 0;
  private final int coordinateX;
  private final int coordinateY;

  public Coordinates(int initialCoordinateX, int initialCoordinateY) {
    validatePositiveCoordinates(initialCoordinateX, initialCoordinateY);
    this.coordinateX = initialCoordinateX;
    this.coordinateY = initialCoordinateY;
  }

  public Coordinates moveTowards(CardinalPoint cardinalPoint) {
    return MOVEMENTS.get(cardinalPoint).apply(this);
  }

  private void validatePositiveCoordinates(int initialCoordinateX, int initialCoordinateY) {
    if (initialCoordinateX < MINIMUM_COORDINATE_VALUE || initialCoordinateY < MINIMUM_COORDINATE_VALUE) {
      throw new CoordinatesMustBePositiveNumbers(initialCoordinateX, initialCoordinateY);
    }
  }

  public void verifyAreInside(final Coordinates bottomLeftCoordinates, final Coordinates upperRightCoordinates) {
    if (bottomLeftCoordinates.coordinateX > this.coordinateX
        || bottomLeftCoordinates.coordinateY > this.coordinateY
        || upperRightCoordinates.coordinateX < this.coordinateX
        || upperRightCoordinates.coordinateY < this.coordinateY) {
      throw new CoordinatesAreOutside(this.coordinateX, this.coordinateY);
    }
  }

  public String situation() {
    return coordinateX + " " + coordinateY;
  }

  private Coordinates forward() {
    return new Coordinates(coordinateX + 1, coordinateY);
  }

  private Coordinates backward() {
    return new Coordinates(coordinateX - 1, coordinateY);
  }

  private Coordinates upward() {
    return new Coordinates(coordinateX, coordinateY + 1);
  }

  private Coordinates downward() {
    return new Coordinates(coordinateX, coordinateY - 1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinates that = (Coordinates) o;
    return coordinateX == that.coordinateX && coordinateY == that.coordinateY;
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinateX, coordinateY);
  }
}
