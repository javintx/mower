package com.mower.domain.exception;

public final class CoordinatesAreOutside extends RuntimeException {

  private static final String CAUSE = "The coordinates (%d, %d) are outside";
  private static final long serialVersionUID = 4458305209369445798L;

  public CoordinatesAreOutside(int coordinateX, int coordinateY) {
    super(String.format(CAUSE, coordinateX, coordinateY));
  }
}
