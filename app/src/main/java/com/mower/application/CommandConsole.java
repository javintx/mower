package com.mower.application;

import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;

import java.util.List;
import java.util.Scanner;

import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;


public class CommandConsole {

  private static final String USER_EXPLANATION_FOR_PLATEAU_COORDINATES = "Insert the upper right coordinates of the plateau (like: 5 5): ";
  private static final String PLATEAU_SEPARATOR_REGEX = " ";
  private static final String PLATEAU_REGEX = "[0-9]+" + PLATEAU_SEPARATOR_REGEX + "[0-9]+";
  private static final int PLATEAU_WIDTH_INDEX = 0;
  private static final int PLATEAU_HEIGHT_INDEX = 1;

  private final Scanner scanner;

  public CommandConsole(Scanner scanner) {
    this.scanner = scanner;
  }

  public Plateau readPlateau() {
    var plateauSize = parseArgument(USER_EXPLANATION_FOR_PLATEAU_COORDINATES, PLATEAU_REGEX);
    return processPlateauWith(plateauSize);
  }

  private Plateau processPlateauWith(String size) {
    return new Plateau(plateauWidthFrom(size), plateauHeightFrom(size));
  }

  private int plateauWidthFrom(String size) {
    return Integer.parseInt(size.split(PLATEAU_SEPARATOR_REGEX)[PLATEAU_WIDTH_INDEX]);
  }

  private int plateauHeightFrom(String size) {
    return Integer.parseInt(size.split(PLATEAU_SEPARATOR_REGEX)[PLATEAU_HEIGHT_INDEX]);
  }

  public Mower readMower() {
    return new Mower(new Coordinates(1, 1), new FaceTo(NORTH));
  }

  public List<Command> readMowerCommands() {
    return List.of(LEFT, RIGHT, MOVE);
  }

  public boolean readIsFinished() {
    return true;
  }

  public void printSituationOf(final Mower mower) {
    System.out.printf("Mower situation: %s%n", mower.situation());
  }

  private String parseArgument(String userExplanation, String validationRegex) {
    String argument;
    do {
      argument = askForArgumentWith(userExplanation);
    } while (!validateArgument(validationRegex, argument));
    return argument;
  }

  private String askForArgumentWith(String userExplanation) {
    System.out.print(userExplanation);
    return scanner.nextLine();
  }

  private boolean validateArgument(String validationRegex, String argument) {
    return argument.matches(validationRegex);
  }
}
