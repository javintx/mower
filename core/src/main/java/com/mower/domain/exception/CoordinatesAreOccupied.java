package com.mower.domain.exception;

import com.mower.domain.valueobjects.Coordinates;

public class CoordinatesAreOccupied extends RuntimeException {

  private static final String CAUSE = "The coordinates (%s) are occupied in the plateau";
  private static final long serialVersionUID = -2622339226115315281L;

  public CoordinatesAreOccupied(final Coordinates coordinates) {
    super(String.format(CAUSE, coordinates.situation()));
  }
}
