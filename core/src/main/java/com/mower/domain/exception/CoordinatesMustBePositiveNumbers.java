package com.mower.domain.exception;

public class CoordinatesMustBePositiveNumbers extends RuntimeException {

  public static final String CAUSE = "Invalid coordinates (%d, %d). They should be positive";

  public CoordinatesMustBePositiveNumbers(int coordinateX, int coordinateY) {
    super(String.format(CAUSE, coordinateX, coordinateY));
  }
}
