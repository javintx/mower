package com.mower.usecase;

import com.mower.domain.CardinalPoint;
import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.exception.CoordinatesAreOccupied;
import com.mower.domain.exception.CoordinatesAreOutside;
import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExecuteMowerCommandsInPlateauUseCaseShould {

  private ExecuteMowerCommandsInPlateauUseCase executeMowerCommandsInPlateau;

  @BeforeEach
  void setUp() {
    this.executeMowerCommandsInPlateau = new ExecuteMowerCommandsInPlateau();
  }

  @Test
  void executeWithoutCommands() {
    var mowerMocked = mower();
    var plateauMocked = plateau();
    assertDoesNotThrow(() -> this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, emptyCommands()));
  }

  @Test
  void executeWithCommands() {
    var mowerMocked = mower();
    var plateauMocked = plateau();
    var commands = commands();

    assertDoesNotThrow(() -> this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, commands));
  }

  @Test
  void executeWithThrowCoordinatesAreOutsidePlateau() {
    var mowerMocked = mowerOutsidePlateau();
    var plateauMocked = plateau();
    var commands = commands();

    assertThrows(CoordinatesAreOutside.class, () -> this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, commands));
  }

  @Test
  void executeWithThrowCoordinatesAreOccupied() {
    var mowerMocked = mower();
    var plateauMocked = plateau();
    var commands = commands();

    this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, commands);
    assertThrows(CoordinatesAreOccupied.class, () -> this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, commands));
  }

  private List<Command> commands() {
    return List.of(LEFT, RIGHT, MOVE);
  }

  private List<Command> emptyCommands() {
    return List.of();
  }

  private Mower mowerOutsidePlateau() {
    return new Mower(invalidMowerCoordinates(), initialMowerFaceTo());
  }

  private Mower mower() {
    return new Mower(validMowerCoordinates(), initialMowerFaceTo());
  }

  private FaceTo initialMowerFaceTo() {
    return new FaceTo(CardinalPoint.EAST);
  }

  private Coordinates validMowerCoordinates() {
    return new Coordinates(0, 0);
  }

  private Coordinates invalidMowerCoordinates() {
    return new Coordinates(6, 6);
  }

  private Plateau plateau() {
    return new Plateau(plateauCoordinates());
  }

  private Coordinates plateauCoordinates() {
    return new Coordinates(5, 5);
  }

}
