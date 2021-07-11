package com.mower.domain;

import com.mower.domain.exception.CoordinatesAreOccupied;
import com.mower.domain.exception.CoordinatesAreOutsidePlateau;
import com.mower.domain.exception.PlateauSizeMustBePositiveNumbers;
import com.mower.domain.valueobjects.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

  public static final int MINIMUM_PLATEAU_COORDINATE = 0;
  private final int width;
  private final int height;
  private final List<Coordinates> usedCoordinates;

  public Plateau(int width, int height) {
    verifyPlateauSize(width, height);
    this.width = width;
    this.height = height;
    this.usedCoordinates = new ArrayList<>();
  }

  public void verifyCoordinates(final Coordinates coordinates) {
    verifyAreInside(coordinates);
    verifyAreUnoccupied(coordinates);
  }

  public void occupyCoordinate(final Coordinates coordinates) {
    this.usedCoordinates.add(coordinates);
  }

  private void verifyPlateauSize(int width, int height) {
    if (width <= MINIMUM_PLATEAU_COORDINATE || height <= MINIMUM_PLATEAU_COORDINATE) {
      throw new PlateauSizeMustBePositiveNumbers(width, height);
    }
  }

  private void verifyAreInside(final Coordinates coordinates) {
    if (coordinates.coordinateX() > width
        || coordinates.coordinateY() > height) {
      throw new CoordinatesAreOutsidePlateau(coordinates);
    }
  }

  private void verifyAreUnoccupied(final Coordinates coordinates) {
    if (this.usedCoordinates.contains(coordinates)) {
      throw new CoordinatesAreOccupied(coordinates);
    }
  }

}
