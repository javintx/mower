package com.mower.domain.exception;

public class UnknownCommandCode extends RuntimeException {

  public static final String CAUSE = "Unknown command code %s";

  public UnknownCommandCode(String code) {
    super(String.format(CAUSE, code));
  }
}
