package com.mower.domain.exception;

public class PlateauSizeMustBePositiveNumbers extends RuntimeException {

  public static final String CAUSE = "Invalid plateau size (%d, %d). They should be positive";

  public PlateauSizeMustBePositiveNumbers(int width, int height) {
    super(String.format(CAUSE, width, height));
  }
}
