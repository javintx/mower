package com.mower.domain;

import com.mower.domain.exception.CoordinatesAreOutsidePlateau;

public class Plateau {

  public static final int MINIMUM_PLATEAU_COORDINATE = 0;
  private final int width;
  private final int height;

  public Plateau(int width, int height) {
    this.width = width;
    this.height = height;
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
