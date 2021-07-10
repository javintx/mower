package com.mower.application;

import com.mower.domain.CardinalPoint;
import com.mower.domain.Mower;
import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.NORTH;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandConsoleShould {

  private static final int ANY_COORDINATE_X = 1;
  private static final int ANY_COORDINATE_Y = 2;
  private static final CardinalPoint ANY_CARDINAL_POINT = NORTH;

  private CommandConsole commandConsole;

  @BeforeEach
  void setUp() {
    commandConsole = new CommandConsole();
  }

  @Test
  void readPlateau() {
    assertThat(commandConsole.readPlateau()).isNotNull();
  }

  @Test
  void readMower() {
    assertThat(commandConsole.readMower()).isNotNull();
  }

  @Test
  void readMowerCommands() {
    assertThat(commandConsole.readMowerCommands()).isNotNull();
  }

  @Test
  void readIsFinished() {
    assertThat(commandConsole.readIsFinished()).isTrue();
  }

  @Test
  void printSituationOfMower() {
    commandConsole.printSituationOf(anyMower());
  }

  private Mower anyMower() {
    return new Mower(anyCoordinates(), anyFaceTo());
  }

  private Coordinates anyCoordinates() {
    return new Coordinates(ANY_COORDINATE_X, ANY_COORDINATE_Y);
  }

  private FaceTo anyFaceTo() {
    return new FaceTo(ANY_CARDINAL_POINT);
  }

}
