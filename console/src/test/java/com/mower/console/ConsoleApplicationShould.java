package com.mower.console;

import com.mower.domain.CardinalPoint;
import com.mower.usecase.ExecuteMowerCommandsInPlateau;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static com.mower.domain.CardinalPoint.NORTH;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ConsoleApplicationShould {

  private static final int ANY_WIDTH = 5;
  private static final int ANY_HEIGHT = 5;
  private static final int ANY_INITIAL_COORDINATE_X = 1;
  private static final int ANY_INITIAL_COORDINATE_Y = 1;
  private static final int ANY_OTHER_INITIAL_COORDINATE_X = 1;
  private static final int ANY_OTHER_INITIAL_COORDINATE_Y = 0;
  private static final CardinalPoint ANY_CARDINAL_POINT = NORTH;
  private static final String ANY_MOWER_COMMANDS = "LRM";
  private static final String TO_OUTSIDE_MOWER_COMMANDS = "MMMMMMM";
  private static final String NO_MOWER_COMMANDS = "";
  private static final String ANY_OTHER_MOWER_COMMANDS = "M";
  private static final String FINISHED_VALUE = "y";
  private static final String NO_FINISHED_VALUE = "n";

  private CommandConsole commandConsole;
  private ConsoleApplication consoleApplication;

  @Test
  void executeMowerCommandsInPlateau() {
    commandConsole = commandConsole(anyPlateau(), anyMower(), ANY_MOWER_COMMANDS, FINISHED_VALUE);
    consoleApplication = new ConsoleApplication(commandConsole);

    assertDoesNotThrow(() -> consoleApplication.start(new ExecuteMowerCommandsInPlateau()));
  }


  @Test
  void executeMowerCommandsInPlateauThatGoOutsideOfThePlateau() {
    commandConsole = commandConsole(anyPlateau(), anyMower(), TO_OUTSIDE_MOWER_COMMANDS, FINISHED_VALUE);
    consoleApplication = new ConsoleApplication(commandConsole);

    assertDoesNotThrow(() -> consoleApplication.start(new ExecuteMowerCommandsInPlateau()));
  }

  @Test
  void executeMowerCommandsInPlateauWhereCrashesTwoMowers() {
    commandConsole = commandConsole(
      anyPlateau(), anyMower(), NO_MOWER_COMMANDS, NO_FINISHED_VALUE,
      anyOtherMower(), ANY_OTHER_MOWER_COMMANDS, FINISHED_VALUE
    );
    consoleApplication = new ConsoleApplication(commandConsole);

    assertDoesNotThrow(() -> consoleApplication.start(new ExecuteMowerCommandsInPlateau()));
  }

  private CommandConsole commandConsole(String... commands) {
    return new CommandConsole(new Scanner(String.join("\n", commands)));
  }

  private String anyPlateau() {
    return ANY_WIDTH + " " + ANY_HEIGHT;
  }

  private String anyMower() {
    return ANY_INITIAL_COORDINATE_X + " " + ANY_INITIAL_COORDINATE_Y + " " + ANY_CARDINAL_POINT.code();
  }

  private String anyOtherMower() {
    return ANY_OTHER_INITIAL_COORDINATE_X + " " + ANY_OTHER_INITIAL_COORDINATE_Y + ANY_CARDINAL_POINT.code();
  }

}
