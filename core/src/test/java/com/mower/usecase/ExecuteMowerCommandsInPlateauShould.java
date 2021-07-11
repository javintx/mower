package com.mower.usecase;

import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.exception.CoordinatesAreOccupied;
import com.mower.domain.exception.CoordinatesAreOutside;
import com.mower.domain.valueobjects.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExecuteMowerCommandsInPlateauShould {

  @Mock
  Plateau plateauMocked;
  @Mock
  Mower mowerMocked;
  @Mock
  Coordinates coordinatesMocked;
  private ExecuteMowerCommandsInPlateau executeMowerCommandsInPlateau;

  @BeforeEach
  void setUp() {
    this.executeMowerCommandsInPlateau = new ExecuteMowerCommandsInPlateau();
  }

  @Test
  void executeWithoutCommands() {
    when(mowerMocked.coordinates()).thenReturn(coordinatesMocked);
    this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, emptyCommands());
    verify(plateauMocked, new Times(emptyCommands().size())).verifyCoordinates(any(Coordinates.class));
    verify(mowerMocked, new Times(emptyCommands().size())).executeCommand(any(Command.class));
    verify(plateauMocked).occupyCoordinate(any(Coordinates.class));
  }

  @Test
  void executeWithCommands() {
    when(mowerMocked.coordinates()).thenReturn(coordinatesMocked);
    this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, commands());
    verify(plateauMocked, new Times(commands().size())).verifyCoordinates(any(Coordinates.class));
    verify(mowerMocked, new Times(commands().size())).executeCommand(any(Command.class));
    verify(plateauMocked).occupyCoordinate(any(Coordinates.class));
  }

  @Test
  void executeWithThrowCoordinatesAreOutsidePlateau() {
    when(mowerMocked.coordinates()).thenReturn(coordinatesMocked);
    doThrow(CoordinatesAreOutside.class).when(plateauMocked).verifyCoordinates(any(Coordinates.class));
    assertThrows(CoordinatesAreOutside.class, () -> this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, commands()));
    verify(mowerMocked, new Times(1)).executeCommand(any(Command.class));
    verify(mowerMocked, new Times(1)).coordinates();
    verify(plateauMocked, new Times(0)).occupyCoordinate(any(Coordinates.class));
  }

  @Test
  void executeWithThrowCoordinatesAreOccupied() {
    when(mowerMocked.coordinates()).thenReturn(coordinatesMocked);
    doThrow(CoordinatesAreOccupied.class).when(plateauMocked).verifyCoordinates(any(Coordinates.class));
    assertThrows(CoordinatesAreOccupied.class, () -> this.executeMowerCommandsInPlateau.executeWith(plateauMocked, mowerMocked, commands()));
    verify(mowerMocked, new Times(1)).executeCommand(any(Command.class));
    verify(mowerMocked, new Times(1)).coordinates();
    verify(plateauMocked, new Times(0)).occupyCoordinate(any(Coordinates.class));
  }

  private List<Command> commands() {
    return List.of(LEFT, RIGHT, MOVE);
  }

  private List<Command> emptyCommands() {
    return List.of();
  }

}
