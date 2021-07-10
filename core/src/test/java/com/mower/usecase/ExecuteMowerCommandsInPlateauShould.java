package com.mower.usecase;

import com.mower.domain.CardinalPoint;
import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;
import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExecuteMowerCommandsInPlateauShould {

  public static final int FINAL_MOWER_COORDINATE_X = 1;
  public static final int FINAL_MOWER_COORDINATE_Y = 3;
  public static final int INITIAL_MOWER_COORDINATE_X = 1;
  public static final int INITIAL_MOWER_COORDINATE_Y = 2;
  public static final int PLATEAU_WIDTH = 5;
  public static final int PLATEAU_HEIGHT = 5;
  private static final CardinalPoint CARDINAL_POINT = NORTH;
  private ExecuteMowerCommandsInPlateau executeMowerCommandsInPlateau;

  @BeforeEach
  void setUp() {
    this.executeMowerCommandsInPlateau = new ExecuteMowerCommandsInPlateau();
  }

  @Test
  void executeWithPlateauMowerAndCommands() {
    var mower = mower();
    this.executeMowerCommandsInPlateau.executeWith(plateau(), mower, commands());
    assertEquals(finalMowerCoordinates(), mower.coordinates());
  }

  @Test
  void executeWithPlateauMowerAndWithoutCommands() {
    var mower = mower();
    this.executeMowerCommandsInPlateau.executeWith(plateau(), mower, emptyCommands());
    assertEquals(mower().coordinates(), mower.coordinates());
  }

  private Plateau plateau() {
    return new Plateau(PLATEAU_WIDTH, PLATEAU_HEIGHT);
  }

  private Mower mower() {
    return new Mower(coordinates(), faceTo());
  }

  private Coordinates coordinates() {
    return new Coordinates(INITIAL_MOWER_COORDINATE_X, INITIAL_MOWER_COORDINATE_Y);
  }

  private FaceTo faceTo() {
    return new FaceTo(CARDINAL_POINT);
  }

  private List<Command> commands() {
    return List.of(LEFT, RIGHT, MOVE);
  }

  private List<Command> emptyCommands() {
    return List.of();
  }

  private Coordinates finalMowerCoordinates() {
    return new Coordinates(FINAL_MOWER_COORDINATE_X, FINAL_MOWER_COORDINATE_Y);
  }
}
