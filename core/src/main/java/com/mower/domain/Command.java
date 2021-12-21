package com.mower.domain;

import com.mower.domain.exception.UnknownCommandCode;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Command {
  LEFT("L"),
  RIGHT("R"),
  MOVE("M");

  private final String code;

  Command(String code) {
    this.code = code;
  }

  public static Command fromCode(String commandCode) {
    return Arrays.stream(values())
        .filter(command -> command.code.equalsIgnoreCase(commandCode))
        .findAny()
        .orElseThrow(() -> new UnknownCommandCode(commandCode));
  }
}
