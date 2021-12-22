package com.mower.domain.exception;

public final class CoordinatesMustBePositiveNumbers extends RuntimeException {

  public static final String CAUSE = "Invalid coordinates (%d, %d). They should be positive";
  private static final long serialVersionUID = -1238431735798408308L;

  public CoordinatesMustBePositiveNumbers(int coordinateX, int coordinateY) {
    super(String.format(CAUSE, coordinateX, coordinateY));
  }
}
