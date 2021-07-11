package com.mower.application;

import com.mower.domain.CardinalPoint;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static com.mower.domain.CardinalPoint.NORTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandConsoleShould {

  private static final int ANY_COORDINATE_X = 1;
  private static final int ANY_COORDINATE_Y = 2;
  private static final CardinalPoint ANY_CARDINAL_POINT = NORTH;
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

  @Mock
  Scanner scannerMocked;
  private CommandConsole commandConsole;

  @BeforeEach
  void setUp() {
    commandConsole = new CommandConsole(scannerMocked);
  }

  @Test
  void readPlateau() {
    when(scannerMocked.nextLine()).thenReturn(INVALID_INPUT_PLATEAU_DIMENSIONS, VALID_INPUT_PLATEAU_DIMENSIONS);
    assertThat(commandConsole.readPlateau()).isNotNull();
    verify(scannerMocked, new Times(2)).nextLine();
  }

  @Test
  void readMower() {
    when(scannerMocked.nextLine())
        .thenReturn(INPUT_MOWER_DEFINITION_OUTSIDE_PLATEAU)
        .thenReturn(INPUT_MOWER_DEFINITION_IN_OCCUPIED_COORDINATE)
        .thenReturn(VALID_INPUT_MOWER_DEFINITION);
    assertThat(commandConsole.readMowerGiven(plateauWithOccupiedCoordinates()).coordinates()).isEqualTo(validMowerCoordinates());
    verify(scannerMocked, new Times(3)).nextLine();
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

  private Plateau plateauWithOccupiedCoordinates() {
    var plateau = new Plateau(PLATEAU_WIDTH, PLATEAU_HEIGHT);
    plateau.occupyCoordinate(occupiedCoordinates());
    return plateau;
  }

  private Coordinates occupiedCoordinates() {
    return new Coordinates(OCCUPIED_COORDINATE_X, OCCUPIED_COORDINATE_Y);
  }

  private Coordinates validMowerCoordinates() {
    return new Coordinates(VALID_MOWER_COORDINATE_X, VALID_MOWER_COORDINATE_Y);
  }

}