package com.mower.domain;

import com.mower.domain.exception.CoordinatesAreOccupied;
import com.mower.domain.exception.CoordinatesAreOutside;
import com.mower.domain.valueobjects.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlateauShould {

  private static final int ZERO_DIMENSION = 0;
  private static final int VALID_PLATEAU_WIDTH = 5;
  private static final int VALID_PLATEAU_HEIGHT = 5;
  private static final int OUTSIDE_COORDINATE_X = 6;
  private static final int OUTSIDE_COORDINATE_Y = 6;
  private static final int VALID_COORDINATE_X = 3;
  private static final int VALID_COORDINATE_Y = 3;

  @Test
  void throwCoordinatesAreOutsidePlateauIfOutsizeCoordinates() {
    var validPlateau = validPlateau();
    var outsideCoordinates = outsideCoordinates();
    assertThrows(CoordinatesAreOutside.class, () -> validPlateau.verifyCoordinates(outsideCoordinates));
  }

  @Test
  void throwCoordinatesAreOutsidePlateauIfOutsizeCoordinatesByCoordinateX() {
    var validPlateau = validPlateau();
    var outsideCoordinatesByCoordinateX = outsideCoordinatesByCoordinateX();
    assertThrows(CoordinatesAreOutside.class, () -> validPlateau.verifyCoordinates(outsideCoordinatesByCoordinateX));
  }

  @Test
  void throwCoordinatesAreOutsidePlateauIfOutsizeCoordinatesByCoordinateY() {
    var validPlateau = validPlateau();
    var outsideCoordinatesByCoordinateY = outsideCoordinatesByCoordinateY();
    assertThrows(CoordinatesAreOutside.class, () -> validPlateau.verifyCoordinates(outsideCoordinatesByCoordinateY));
  }

  @Test
  void throwCoordinatesAreOccupiedIfCoordinatesAreOccupiedInThePlateau() {
    var validPlateauWithOccupiedCoordinate = validPlateauWithOccupiedCoordinate();
    var validCoordinates = validCoordinates();
    assertThrows(CoordinatesAreOccupied.class, () -> validPlateauWithOccupiedCoordinate.verifyCoordinates(validCoordinates));
  }

  @Test
  void verifyCoordinates() {
    var validPlateau = validPlateau();
    var validCoordinates = validCoordinates();
    assertDoesNotThrow(() -> validPlateau.verifyCoordinates(validCoordinates));
  }

  private Plateau validPlateau() {
    return new Plateau(validPlateauCoordinates());
  }

  private Plateau validPlateauWithOccupiedCoordinate() {
    var plateau = new Plateau(validPlateauCoordinates());
    plateau.occupyCoordinate(validCoordinates());
    return plateau;
  }

  private Coordinates validPlateauCoordinates() {
    return new Coordinates(VALID_PLATEAU_WIDTH, VALID_PLATEAU_HEIGHT);
  }

  private Coordinates plateauCoordinatesWithWrongWidthCoordinate() {
    return new Coordinates(ZERO_DIMENSION, VALID_PLATEAU_HEIGHT);
  }

  private Coordinates plateauCoordinatesWithWrongHeightCoordinate() {
    return new Coordinates(VALID_PLATEAU_WIDTH, ZERO_DIMENSION);
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
