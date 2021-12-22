package com.mower.domain.exception;

public final class UnknownCardinalPointCode extends RuntimeException {

  public static final String CAUSE = "Unknown cardinal point code %s";
  private static final long serialVersionUID = 7646353868531908822L;

  public UnknownCardinalPointCode(String code) {
    super(String.format(CAUSE, code));
  }
}
