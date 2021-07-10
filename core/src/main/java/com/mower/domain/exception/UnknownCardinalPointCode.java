package com.mower.domain.exception;

public class UnknownCardinalPointCode extends RuntimeException {

  public static final String CAUSE = "Unknown cardinal point code %s";

  public UnknownCardinalPointCode(String code) {
    super(String.format(CAUSE, code));
  }
}
