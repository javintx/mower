package com.mower.application;

import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;

import java.util.List;

import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;


public class CommandConsole {

  public Plateau readPlateau() {
    return new Plateau(5, 5);
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

}
