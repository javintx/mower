package com.mower.domain.valueobjects;

import com.mower.domain.exception.CoordinatesAreOutside;
import com.mower.domain.exception.CoordinatesMustBePositiveNumbers;
import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinateShould {

  public static final String EXPECTED_COORDINATES_SITUATION = "1 2";
  private static final int COORDINATE_ZERO = 0;
  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 2;
  private static final int OTHER_COORDINATE_X = 3;
  private static final int OTHER_COORDINATE_Y = 4;
  private static final int LESS_THAN_ZERO_DIMENSION = -1;

  @Test
  void throwCoordinatesMustBePositiveNumbersIfCreatesACoordinatesWithCoordinateXZeroOrLess() {
    assertThrows(CoordinatesMustBePositiveNumbers.class,
        () -> new Coordinates(LESS_THAN_ZERO_DIMENSION, COORDINATE_Y));
  }

  @Test
  void throwCoordinatesMustBePositiveNumbersIfCreatesACoordinatesWithCoordinateYZeroOrLess() {
    assertThrows(CoordinatesMustBePositiveNumbers.class,
        () -> new Coordinates(COORDINATE_X, LESS_THAN_ZERO_DIMENSION));
  }

  @Test
  void printSituation() {
    assertThat(coordinates().situation()).isEqualTo(EXPECTED_COORDINATES_SITUATION);
  }

  @Test
  void verifyInsideCoordinates() {
    var coordinatesInside = coordinatesInside();
    var zeroCoordinates = zeroCoordinates();
    var otherCoordinates = otherCoordinates();
    assertDoesNotThrow(() -> coordinatesInside.verifyAreInside(zeroCoordinates, otherCoordinates));
  }

  @Test
  void throwCoordinatesAreOutsideWhenVerifyOutsideAboveCoordinatesByHeight() {
    var otherCoordinatesAboveByHeight = otherCoordinatesAboveByHeight();
    var zeroCoordinates = zeroCoordinates();
    var otherCoordinates = otherCoordinates();
    assertThrows(CoordinatesAreOutside.class,
        () -> otherCoordinatesAboveByHeight.verifyAreInside(zeroCoordinates, otherCoordinates));
  }

  @Test
  void throwCoordinatesAreOutsideWhenVerifyOutsideAboveCoordinatesByWidth() {
    var otherCoordinatesAboveByWidth = otherCoordinatesAboveByWidth();
    var zeroCoordinates = zeroCoordinates();
    var otherCoordinates = otherCoordinates();
    assertThrows(CoordinatesAreOutside.class,
        () -> otherCoordinatesAboveByWidth.verifyAreInside(zeroCoordinates, otherCoordinates));
  }

  @Test
  void throwCoordinatesAreOutsideWhenVerifyOutsideBelowCoordinatesByHeight() {
    var coordinatesBelowByHeight = coordinatesBelowByHeight();
    var coordinates = coordinates();
    var otherCoordinates = otherCoordinates();
    assertThrows(CoordinatesAreOutside.class,
        () -> coordinatesBelowByHeight.verifyAreInside(coordinates, otherCoordinates));
  }

  @Test
  void throwCoordinatesAreOutsideWhenVerifyOutsideBelowCoordinatesByWidth() {
    var coordinatesBelowByWidth = coordinatesBelowByWidth();
    var coordinates = coordinates();
    var otherCoordinates = otherCoordinates();
    assertThrows(CoordinatesAreOutside.class,
        () -> coordinatesBelowByWidth.verifyAreInside(coordinates, otherCoordinates));
  }

  @Test
  void moveTowardsNorth() {
    assertThat(coordinates().moveTowards(NORTH)).isEqualTo(coordinatesMovedToNorth());
  }

  @Test
  void moveTowardsEast() {
    assertThat(coordinates().moveTowards(EAST)).isEqualTo(coordinatesMovedToEast());
  }

  @Test
  void moveTowardsSouth() {
    assertThat(coordinates().moveTowards(SOUTH)).isEqualTo(coordinatesMovedToSouth());
  }

  @Test
  void moveTowardsWest() {
    assertThat(coordinates().moveTowards(WEST)).isEqualTo(coordinatesMovedToWest());
  }

  @Test
  void ensureTwoCoordinatesWithSameValuesAreEquals() {
    assertThat(coordinates()).isEqualTo(coordinates());
  }

  @Test
  void ensureSameCoordinatesIsEqualsToItself() {
    var coordinates = coordinates();
    assertThat(coordinates).isEqualTo(coordinates);
  }

  @Test
  void ensureOtherClassIsNotEqualsToCoordinates() {
    assertNotEquals(coordinates(), "String class");
  }

  @Test
  void ensureNullIsNotEqualsToCoordinates() {
    assertNotEquals(coordinates(), null);
  }

  @Test
  void ensureTwoCoordinatesWithDifferentCoordinateXAreNotEquals() {
    assertNotEquals(coordinates(), coordinatesAboveByWidth());
  }

  @Test
  void ensureTwoCoordinatesWithDifferentCoordinateYAreNotEquals() {
    assertNotEquals(coordinates(), coordinatesAboveByHeight());
  }

  @Test
  void ensureTwoCoordinatesWithSameValuesHaveSameHashCode() {
    assertThat(coordinates()).hasSameHashCodeAs(coordinates());
  }

  @Test
  void ensureTwoCoordinatesWithDifferentValuesHaveDifferentHashCode() {
    assertThat(coordinates()).doesNotHaveSameHashCodeAs(otherCoordinates());
  }

  private Coordinates zeroCoordinates() {
    return new Coordinates(COORDINATE_ZERO, COORDINATE_ZERO);
  }

  private Coordinates coordinates() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private Coordinates coordinatesInside() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private Coordinates otherCoordinatesAboveByHeight() {
    return new Coordinates(OTHER_COORDINATE_X, OTHER_COORDINATE_Y + 1);
  }

  private Coordinates otherCoordinatesAboveByWidth() {
    return new Coordinates(OTHER_COORDINATE_X + 1, OTHER_COORDINATE_Y);
  }

  private Coordinates coordinatesAboveByHeight() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y + 1);
  }

  private Coordinates coordinatesAboveByWidth() {
    return new Coordinates(COORDINATE_X + 1, COORDINATE_Y);
  }

  private Coordinates coordinatesBelowByHeight() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y - 1);
  }

  private Coordinates coordinatesBelowByWidth() {
    return new Coordinates(COORDINATE_X - 1, COORDINATE_Y);
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
