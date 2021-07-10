package com.mower.domain;

import com.mower.domain.exception.CoordinatesAreOutsidePlateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlateauShould {

  private static final int VALID_PLATEAU_WIDTH = 5;
  private static final int VALID_PLATEAU_HEIGHT = 5;
  private static final int OUTSIDE_COORDINATE_X = 6;
  private static final int OUTSIDE_COORDINATE_Y = 6;
  private static final int VALID_COORDINATE_X = 3;
  private static final int VALID_COORDINATE_Y = 3;

  @Test
  void throwCoordinatesAreOutsidePlateauIfOutsizeCoordinates() {
    assertThrows(CoordinatesAreOutsidePlateau.class, () -> validPlateau().verifyCoordinates(outsideCoordinates()));
  }

  @Test
  void throwCoordinatesAreOutsidePlateauIfOutsizeCoordinatesByCoordinateX() {
    assertThrows(CoordinatesAreOutsidePlateau.class, () -> validPlateau().verifyCoordinates(outsideCoordinatesByCoordinateX()));
  }

  @Test
  void throwCoordinatesAreOutsidePlateauIfOutsizeCoordinatesByCoordinateY() {
    assertThrows(CoordinatesAreOutsidePlateau.class, () -> validPlateau().verifyCoordinates(outsideCoordinatesByCoordinateY()));
  }

  @Test
  void verifyCoordinates() {
    validPlateau().verifyCoordinates(validCoordinates());
  }

  private Plateau validPlateau() {
    return new Plateau(VALID_PLATEAU_WIDTH, VALID_PLATEAU_HEIGHT);
  }

  private Coordinates outsideCoordinates() {
    return new Coordinates(OUTSIDE_COORDINATE_X, OUTSIDE_COORDINATE_Y);
  }

  private Coordinates outsideCoordinatesByCoordinateX() {
    return new Coordinates(OUTSIDE_COORDINATE_X, VALID_COORDINATE_Y);
  }

  private Coordinates outsideCoordinatesByCoordinateY() {
    return new Coordinates(VALID_COORDINATE_X, OUTSIDE_COORDINATE_Y);
  }

  private Coordinates validCoordinates() {
    return new Coordinates(VALID_COORDINATE_X, VALID_COORDINATE_Y);
  }

}
