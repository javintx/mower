package com.mower.domain;

import com.mower.domain.exception.UnknownCommandCode;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Command {
  LEFT("L", Mower::spinLeft),
  RIGHT("R", Mower::spinRight),
  MOVE("M", Mower::moveForward);

  private final String code;
  private final Consumer<Mower> action;

  Command(String code, final Consumer<Mower> action) {
    this.code = code;
    this.action = action;
  }

  public static Command fromCode(String commandCode) {
    return Arrays.stream(values())
        .filter(command -> command.code.equalsIgnoreCase(commandCode))
        .findAny()
        .orElseThrow(() -> new UnknownCommandCode(commandCode));
  }

  public void execute(final Mower mower) {
    action.accept(mower);
  }
}
