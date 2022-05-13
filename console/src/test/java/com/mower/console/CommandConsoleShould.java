package com.mower.console;

import com.mower.domain.CardinalPoint;
import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CommandConsoleShould {

  private static final String VALID_INPUT_PLATEAU_DIMENSIONS = "5 5";
  private static final String INVALID_INPUT_PLATEAU_DIMENSIONS = "INVALID DIMENSIONS";
  private static final int PLATEAU_WIDTH = 5;
  private static final int PLATEAU_HEIGHT = 5;
  private static final String INPUT_MOWER_DEFINITION_OUTSIDE_PLATEAU = "6 6 N";
  private static final String INPUT_MOWER_DEFINITION_IN_OCCUPIED_COORDINATE = "3 3 N";
  private static final String VALID_INPUT_MOWER_DEFINITION = "1 2 N";
  private static final int VALID_MOWER_COORDINATE_X = 1;
  private static final int VALID_MOWER_COORDINATE_Y = 2;
  private static final int OCCUPIED_COORDINATE_X = 3;
  private static final int OCCUPIED_COORDINATE_Y = 3;
  private static final String INVALID_INPUT_MOWER_COMMANDS = "INVALID MOWER COMMANDS";
  private static final String VALID_INPUT_MOWER_COMMANDS = "LRM";
  private static final List<Command> EXPECTED_MOWER_COMMANDS = List.of(LEFT, RIGHT, MOVE);
  private static final String VALID_INPUT_IS_FINISHED = "y";
  private static final String VALID_INPUT_IS_NOT_FINISHED = "N";
  private static final String INVALID_INPUT_IS_FINISHED = "INVALID";

  private Scanner scannerMocked;
  private CommandConsole commandConsole;

  @Test
  void readPlateau() {
    scannerMocked = scannerMocked(INVALID_INPUT_PLATEAU_DIMENSIONS, VALID_INPUT_PLATEAU_DIMENSIONS);
    commandConsole = new CommandConsole(scannerMocked);

    assertThat(commandConsole.readPlateau()).isNotNull();
  }


  @Test
  void readMower() {
    scannerMocked = scannerMocked(INPUT_MOWER_DEFINITION_OUTSIDE_PLATEAU,
      INPUT_MOWER_DEFINITION_IN_OCCUPIED_COORDINATE,
      VALID_INPUT_MOWER_DEFINITION);
    commandConsole = new CommandConsole(scannerMocked);

    assertThat(commandConsole.readMowerGiven(plateauWithOccupiedCoordinates()).coordinates()).isEqualTo(validMowerCoordinates());
  }

  @Test
  void readMowerCommands() {
    scannerMocked = scannerMocked(INVALID_INPUT_MOWER_COMMANDS, VALID_INPUT_MOWER_COMMANDS);
    commandConsole = new CommandConsole(scannerMocked);

    assertThat(commandConsole.readMowerCommands()).isEqualTo(expectedMowerCommands());
  }

  @Test
  void readIsFinished() {
    scannerMocked = scannerMocked(INVALID_INPUT_IS_FINISHED, VALID_INPUT_IS_FINISHED);
    commandConsole = new CommandConsole(scannerMocked);

    assertThat(commandConsole.readIsFinished()).isTrue();
  }

  @Test
  void readIsNotFinished() {
    scannerMocked = scannerMocked(VALID_INPUT_IS_NOT_FINISHED);
    commandConsole = new CommandConsole(scannerMocked);

    assertThat(commandConsole.readIsFinished()).isFalse();
  }

  @Test
  void printSituationOfMower() {
    var mower = new Mower(validMowerCoordinates(), new FaceTo(CardinalPoint.EAST));
    commandConsole = new CommandConsole(null);
    assertDoesNotThrow(() -> commandConsole.printSituationOf(mower));
  }

  private Scanner scannerMocked(String... commands) {
    return new Scanner(String.join("\n", commands));
  }

  private Plateau plateauWithOccupiedCoordinates() {
    var plateau = new Plateau(plateauCoordinates());
    plateau.occupyCoordinate(occupiedCoordinates());
    return plateau;
  }

  private Coordinates plateauCoordinates() {
    return new Coordinates(PLATEAU_WIDTH, PLATEAU_HEIGHT);
  }

  private Coordinates occupiedCoordinates() {
    return new Coordinates(OCCUPIED_COORDINATE_X, OCCUPIED_COORDINATE_Y);
  }

  private Coordinates validMowerCoordinates() {
    return new Coordinates(VALID_MOWER_COORDINATE_X, VALID_MOWER_COORDINATE_Y);
  }

  private List<Command> expectedMowerCommands() {
    return EXPECTED_MOWER_COMMANDS;
  }

}
