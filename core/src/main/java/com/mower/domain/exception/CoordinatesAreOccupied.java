package com.mower.domain.exception;

import com.mower.domain.valueobjects.Coordinates;

public class CoordinatesAreOccupied extends RuntimeException {
  private static final String CAUSE = "The coordinates (%d, %d) are occupied in the plateau";

  public CoordinatesAreOccupied(final Coordinates coordinates) {
    super(String.format(CAUSE, coordinates.coordinateX(), coordinates.coordinateY()));
  }
}
