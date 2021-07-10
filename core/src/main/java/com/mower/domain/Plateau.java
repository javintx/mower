package com.mower.domain;

import com.mower.domain.exception.CoordinatesAreOutsidePlateau;
import com.mower.domain.exception.PlateauSizeMustBePositiveNumbers;
import com.mower.domain.valueobjects.Coordinates;

public class Plateau {

  public static final int MINIMUM_PLATEAU_COORDINATE = 0;
  private final int width;
  private final int height;

  public Plateau(int width, int height) {
    verifyPlateauSize(width, height);
    this.width = width;
    this.height = height;
  }

  private void verifyPlateauSize(int width, int height) {
    if (width <= MINIMUM_PLATEAU_COORDINATE || height <= MINIMUM_PLATEAU_COORDINATE) {
      throw new PlateauSizeMustBePositiveNumbers(width, height);
    }
  }

  // TODO: May be the plateau could have 2 coordinates to define the plateau size and the this method could be moved to Coordinates class?
  public void verifyCoordinates(final Coordinates coordinates) {
    if (coordinates.coordinateX() > width
        || coordinates.coordinateY() > height
        || coordinates.coordinateX() < MINIMUM_PLATEAU_COORDINATE
        || coordinates.coordinateY() < MINIMUM_PLATEAU_COORDINATE) {
      throw new CoordinatesAreOutsidePlateau(coordinates);
    }
  }

}
