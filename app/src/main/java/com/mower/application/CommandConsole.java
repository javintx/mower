package com.mower.application;

import com.mower.domain.CardinalPoint;
import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CommandConsole {

  private static final String USER_EXPLANATION_FOR_PLATEAU_COORDINATES = "Insert the upper right coordinates of the plateau (like: 5 5): ";
  private static final String USER_EXPLANATION_FOR_MOWER_DEFINITION = "Insert the mower definition (like: 1 2 N): ";
  private static final String USER_EXPLANATION_FOR_MOWER_COMMANDS = "Insert the mower commands (like: LRM): ";
  private static final String USER_EXPLANATION_FOR_IS_FINISHED = "Do you want to finish? (y/n): ";

  private static final String PLATEAU_SEPARATOR_REGEX = " ";
  private static final String MOWER_SEPARATOR_REGEX = " ";
  private static final String COMMANDS_SEPARATOR_REGEX = "";
  private static final String NUMBER_REGEX = "[0-9]+";
  private static final String PLATEAU_REGEX = NUMBER_REGEX + PLATEAU_SEPARATOR_REGEX + NUMBER_REGEX;
  private static final String MOWER_REGEX = NUMBER_REGEX + MOWER_SEPARATOR_REGEX + NUMBER_REGEX + MOWER_SEPARATOR_REGEX + "[NESW]";
  private static final String MOWER_COMMANDS_REGEX = "[LRM]+";
  private static final String IS_FINISHED_REGEX = "[ynYN]";

  private static final int PLATEAU_WIDTH_INDEX = 0;
  private static final int PLATEAU_HEIGHT_INDEX = 1;
  private static final int MOWER_COORDINATE_X_INDEX = 0;
  private static final int MOWER_COORDINATE_Y_INDEX = 1;
  private static final int MOWER_CARDINAL_POINT_INDEX = 2;
  private static final String FINISH_VALUE = "y";

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

  public Mower readMowerGiven(final Plateau plateau) {
    String mowerDefinition;
    do {
      mowerDefinition = parseArgument(USER_EXPLANATION_FOR_MOWER_DEFINITION, MOWER_REGEX);
    } while (!verifyMower(plateau, mowerDefinition));
    return processMowerWith(mowerDefinition);
  }

  private boolean verifyMower(final Plateau plateau, String mowerDefinition) {
    var verified = false;
    try {
      plateau.verifyCoordinates(processCoordinatesFrom(mowerDefinition));
      verified = true;
    } catch (Exception exception) {
      printErrorMessage(exception.getMessage());
    }
    return verified;
  }

  private Coordinates processCoordinatesFrom(String mowerDefinition) {
    return new Coordinates(
        coordinateXFrom(mowerDefinition),
        coordinateYFrom(mowerDefinition));
  }

  private int coordinateXFrom(String mowerDefinition) {
    return Integer.parseInt(mowerDefinition.split(MOWER_SEPARATOR_REGEX)[MOWER_COORDINATE_X_INDEX]);
  }

  private int coordinateYFrom(String mowerDefinition) {
    return Integer.parseInt(mowerDefinition.split(MOWER_SEPARATOR_REGEX)[MOWER_COORDINATE_Y_INDEX]);
  }

  private Mower processMowerWith(String mowerDefinition) {
    var mowerCoordinates = processCoordinatesFrom(mowerDefinition);
    return new Mower(
        mowerCoordinates,
        processFaceToFrom(mowerDefinition)
    );
  }

  private FaceTo processFaceToFrom(String mowerDefinition) {
    return new FaceTo(CardinalPoint.fromCode(mowerDefinition.split(MOWER_SEPARATOR_REGEX)[MOWER_CARDINAL_POINT_INDEX]));
  }

  public List<Command> readMowerCommands() {
    var mowerCommands = parseArgument(USER_EXPLANATION_FOR_MOWER_COMMANDS, MOWER_COMMANDS_REGEX);
    return processCommandsWith(mowerCommands);
  }

  private List<Command> processCommandsWith(String mowerCommands) {
    return Arrays
        .stream(mowerCommands.split(COMMANDS_SEPARATOR_REGEX))
        .map(Command::fromCode)
        .collect(Collectors.toList());
  }

  public boolean readIsFinished() {
    var isFinished = parseArgument(USER_EXPLANATION_FOR_IS_FINISHED, IS_FINISHED_REGEX);
    return processIsFinishedWith(isFinished);
  }

  private boolean processIsFinishedWith(String isFinished) {
    return FINISH_VALUE.equalsIgnoreCase(isFinished);
  }

  public void printSituationOf(final Mower mower) {
    System.out.printf("Mower situation: %s%n", mower.situation());
  }

  public void printErrorMessage(String error) {
    System.err.println(error);
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
