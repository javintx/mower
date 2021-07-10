package com.mower.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CoordinateShould {

  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 2;

  private static final int OTHER_COORDINATE_X = 3;
  private static final int OTHER_COORDINATE_Y = 4;

  @Test
  void returnCoordinateX() {
    assertEquals(COORDINATE_X, coordinates().coordinateX());
  }

  @Test
  void returnCoordinateY() {
    assertEquals(COORDINATE_Y, coordinates().coordinateY());
  }

  @Test
  void ensureTwoCoordinatesWithSameValuesAreEquals() {
    assertEquals(coordinates(), coordinates());
  }

  @Test
  void ensureTwoCoordinatesWithDifferentValuesAreNotEquals() {
    assertNotEquals(coordinates(), otherCoordinates());
  }

  @Test
  void ensureTwoCoordinatesWithSameValuesHaveSameHashCode() {
    assertEquals(coordinates().hashCode(), coordinates().hashCode());
  }

  @Test
  void ensureTwoCoordinatesWithDifferentValuesHaveDifferentHashCode() {
    assertNotEquals(coordinates().hashCode(), otherCoordinates().hashCode());
  }

  private Coordinates coordinates() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private Coordinates otherCoordinates() {
    return new Coordinates(OTHER_COORDINATE_X, OTHER_COORDINATE_Y);
  }
}
