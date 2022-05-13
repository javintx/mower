package com.mower.usecase;

import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;

import java.util.List;

public final class ExecuteMowerCommandsInPlateau implements ExecuteMowerCommandsInPlateauUseCase {

  @Override
  public void executeWith(final Plateau plateau, final Mower mower, final List<Command> commands) {
    plateau.verifyCoordinates(mower.coordinates());
    for (Command command : commands) {
      mower.executeCommand(command);
      plateau.verifyCoordinates(mower.coordinates());
    }
    plateau.occupyCoordinate(mower.coordinates());
  }
}
