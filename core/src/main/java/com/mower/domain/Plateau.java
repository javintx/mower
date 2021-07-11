package com.mower.domain;

import com.mower.domain.exception.CoordinatesAreOccupied;
import com.mower.domain.valueobjects.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

  public static final int ZERO_COORDINATE = 0;
  private final Coordinates upperRightCoordinates;
  private final Coordinates bottomLeftCoordinates = new Coordinates(ZERO_COORDINATE, ZERO_COORDINATE);
  private final List<Coordinates> usedCoordinates = new ArrayList<>();

  public Plateau(final Coordinates upperRightSizeCoordinates) {
    this.upperRightCoordinates = upperRightSizeCoordinates;
  }

  public void verifyCoordinates(final Coordinates coordinates) {
    verifyAreInside(coordinates);
    verifyAreUnoccupied(coordinates);
  }

  public void occupyCoordinate(final Coordinates coordinates) {
    this.usedCoordinates.add(coordinates);
  }

  private void verifyAreInside(final Coordinates coordinates) {
    coordinates.verifyAreInside(bottomLeftCoordinates, upperRightCoordinates);
  }

  private void verifyAreUnoccupied(final Coordinates coordinates) {
    if (this.usedCoordinates.contains(coordinates)) {
      throw new CoordinatesAreOccupied(coordinates);
    }
  }

}
