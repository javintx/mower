package com.mower.application;

import com.mower.domain.CardinalPoint;
import com.mower.domain.Mower;
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

  @Mock
  Scanner scannerMocked;
  private CommandConsole commandConsole;

  @BeforeEach
  void setUp() {
    commandConsole = new CommandConsole(scannerMocked);
  }

  @Test
  void readPlateau() {
    when(scannerMocked.nextLine()).thenReturn(VALID_INPUT_PLATEAU_DIMENSIONS);
    assertThat(commandConsole.readPlateau()).isNotNull();
  }

  @Test
  void readPlateauWithErrorFirst() {
    when(scannerMocked.nextLine()).thenReturn(INVALID_INPUT_PLATEAU_DIMENSIONS, VALID_INPUT_PLATEAU_DIMENSIONS);
    assertThat(commandConsole.readPlateau()).isNotNull();
    verify(scannerMocked, new Times(2)).nextLine();
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
