package com.mower.domain.exception;

public final class UnknownCommandCode extends RuntimeException {

  public static final String CAUSE = "Unknown command code %s";
  private static final long serialVersionUID = 1141308357161570310L;

  public UnknownCommandCode(String code) {
    super(String.format(CAUSE, code));
  }
}
