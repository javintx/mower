package com.mower.domain.valueobjects;

import com.mower.domain.exception.CoordinatesMustBePositiveNumbers;
import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinateShould {

  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 2;

  private static final int OTHER_COORDINATE_X = 3;
  private static final int OTHER_COORDINATE_Y = 4;

  private static final int LESS_THAN_ZERO_DIMENSION = -1;

  @Test
  void throwCoordinatesMustBePositiveNumbersIfCreatesACoordinatesWithDimensionsZeroOrLess() {
    assertThrows(CoordinatesMustBePositiveNumbers.class,
        () -> new Coordinates(LESS_THAN_ZERO_DIMENSION, LESS_THAN_ZERO_DIMENSION));
  }

  @Test
  void moveTowardsNorth() {
    var coordinates = coordinates();
    coordinates.moveTowards(NORTH);
    assertThat(coordinates).isEqualTo(coordinatesMovedToNorth());
  }

  @Test
  void moveTowardsEast() {
    var coordinates = coordinates();
    coordinates.moveTowards(EAST);
    assertThat(coordinates).isEqualTo(coordinatesMovedToEast());
  }

  @Test
  void moveTowardsSouth() {
    var coordinates = coordinates();
    coordinates.moveTowards(SOUTH);
    assertThat(coordinates).isEqualTo(coordinatesMovedToSouth());
  }

  @Test
  void moveTowardsWest() {
    var coordinates = coordinates();
    coordinates.moveTowards(WEST);
    assertThat(coordinates).isEqualTo(coordinatesMovedToWest());
  }

  @Test
  void returnCoordinateX() {
    assertThat(COORDINATE_X).isEqualTo(coordinates().coordinateX());
  }

  @Test
  void returnCoordinateY() {
    assertThat(COORDINATE_Y).isEqualTo(coordinates().coordinateY());
  }

  @Test
  void ensureTwoCoordinatesWithSameValuesAreEquals() {
    assertThat(coordinates()).isEqualTo(coordinates());
  }

  @Test
  void ensureTwoCoordinatesWithDifferentValuesAreNotEquals() {
    assertThat(coordinates()).isNotEqualTo(otherCoordinates());
  }

  @Test
  void ensureTwoCoordinatesWithSameValuesHaveSameHashCode() {
    assertThat(coordinates().hashCode()).isEqualTo(coordinates().hashCode());
  }

  @Test
  void ensureTwoCoordinatesWithDifferentValuesHaveDifferentHashCode() {
    assertThat(coordinates().hashCode()).isNotEqualTo(otherCoordinates().hashCode());
  }

  private Coordinates coordinates() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private Coordinates otherCoordinates() {
    return new Coordinates(OTHER_COORDINATE_X, OTHER_COORDINATE_Y);
  }

  private Coordinates coordinatesMovedToNorth() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y + 1);
  }

  private Coordinates coordinatesMovedToEast() {
    return new Coordinates(COORDINATE_X + 1, COORDINATE_Y);
  }

  private Coordinates coordinatesMovedToSouth() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y - 1);
  }

  private Coordinates coordinatesMovedToWest() {
    return new Coordinates(COORDINATE_X - 1, COORDINATE_Y);
  }
}
