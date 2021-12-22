package com.mower.console;

import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.exception.CoordinatesAreOccupied;
import com.mower.domain.exception.CoordinatesAreOutside;
import com.mower.usecase.ExecuteMowerCommandsInPlateauUseCase;

public class ConsoleApplication {

  private final CommandConsole commandConsole;

  public ConsoleApplication(final CommandConsole commandConsole) {
    this.commandConsole = commandConsole;
  }

  public void start(final ExecuteMowerCommandsInPlateauUseCase executeMowerCommandsInPlateau) {
    var plateau = commandConsole.readPlateau();
    readAndExecuteCommands(commandConsole, plateau, executeMowerCommandsInPlateau);
  }

  private void readAndExecuteCommands(final CommandConsole commandConsole, final Plateau plateau, final ExecuteMowerCommandsInPlateauUseCase executeMowerCommandsInPlateau) {
    do {
      readAndExecuteCommand(commandConsole, plateau, executeMowerCommandsInPlateau);
    } while (!commandConsole.readIsFinished());
  }

  private void readAndExecuteCommand(final CommandConsole commandConsole, final Plateau plateau, final ExecuteMowerCommandsInPlateauUseCase executeMowerCommandsInPlateau) {
    var mower = commandConsole.readMowerGiven(plateau);
    var mowerCommands = commandConsole.readMowerCommands();
    try {
      executeMowerCommandsInPlateau.executeWith(plateau, mower, mowerCommands);
      commandConsole.printSituationOf(mower);
    } catch (CoordinatesAreOutside coordinatesAreOutside) {
      mowerHasComeOffThePlateau(commandConsole, coordinatesAreOutside);
    } catch (CoordinatesAreOccupied coordinatesAreOccupied) {
      mowerHasCrashedWithAnotherMower(commandConsole, mower, coordinatesAreOccupied);
    }
  }

  private void mowerHasCrashedWithAnotherMower(final CommandConsole commandConsole, final Mower mower, final CoordinatesAreOccupied coordinatesAreOccupied) {
    commandConsole.printErrorMessage(coordinatesAreOccupied.getMessage());
    commandConsole.printSituationOf(mower);
  }

  private void mowerHasComeOffThePlateau(final CommandConsole commandConsole, final CoordinatesAreOutside coordinatesAreOutside) {
    commandConsole.printErrorMessage(coordinatesAreOutside.getMessage());
  }
}
