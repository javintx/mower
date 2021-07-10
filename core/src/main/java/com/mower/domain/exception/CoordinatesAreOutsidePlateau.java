package com.mower.domain.exception;

import com.mower.domain.valueobjects.Coordinates;

public class CoordinatesAreOutsidePlateau extends RuntimeException {

  private static final String CAUSE = "The coordinates %s are outside the plateau";

  public CoordinatesAreOutsidePlateau(final Coordinates coordinates) {
    super(String.format(CAUSE, coordinates));
  }
}
