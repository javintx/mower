package com.mower.domain;

import com.mower.domain.exception.UnknownCardinalPointCode;

import java.util.Arrays;

public enum CardinalPoint {
  NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

  private final String code;

  CardinalPoint(String code) {
    this.code = code;
  }

  public static CardinalPoint fromCode(String cardinalPointCode) {
    return Arrays.stream(values())
        .filter(cardinalPoint -> cardinalPoint.code.equals(cardinalPointCode.toUpperCase()))
        .findAny()
        .orElseThrow(() -> new UnknownCardinalPointCode(cardinalPointCode));
  }

  public String code() {
    return code;
  }
}
