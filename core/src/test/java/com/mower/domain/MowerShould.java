package com.mower.domain;

import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;
import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class MowerShould {

  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 2;

  @Test
  void returnCoordinates() {
    assertEquals(coordinates(), mowerOrientedToNorth().coordinates());
  }

  @Test
  void executeMoveCommand() {
    var mower = mowerOrientedToNorth();
    mower.executeCommand(MOVE);
    assertEquals(mowerMovedToNorth().coordinates(), mower.coordinates());
  }

  @Test
  void executeRightCommand() {
    var mower = mowerOrientedToNorth();
    mower.executeCommand(RIGHT);
    assertSame(mowerOrientedToEast().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void executeLeftCommand() {
    var mower = mowerOrientedToNorth();
    mower.executeCommand(LEFT);
    assertSame(mowerMovedToWest().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void spinLeftFromNorth() {
    var mower = mowerOrientedToNorth();
    mower.spinLeft();
    assertSame(mowerOrientedToWest().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void spinLeftFromEast() {
    var mower = mowerOrientedToEast();
    mower.spinLeft();
    assertSame(mowerOrientedToNorth().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void spinLeftFromSouth() {
    var mower = mowerOrientedToSouth();
    mower.spinLeft();
    assertSame(mowerOrientedToEast().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void spinLeftFromWest() {
    var mower = mowerOrientedToWest();
    mower.spinLeft();
    assertSame(mowerOrientedToSouth().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void spinRightFromNorth() {
    var mower = mowerOrientedToNorth();
    mower.spinRight();
    assertSame(mowerOrientedToEast().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void spinRightFromEast() {
    var mower = mowerOrientedToEast();
    mower.spinRight();
    assertSame(mowerOrientedToSouth().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void spinRightFromSouth() {
    var mower = mowerOrientedToSouth();
    mower.spinRight();
    assertSame(mowerOrientedToWest().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void spinRightFromWest() {
    var mower = mowerOrientedToWest();
    mower.spinRight();
    assertSame(mowerOrientedToNorth().cardinalPoint(), mower.cardinalPoint());
  }

  @Test
  void moveForwardFromNorth() {
    var mower = mowerOrientedToNorth();
    mower.moveForward();
    assertEquals(mowerMovedToNorth().coordinates(), mower.coordinates());
  }

  @Test
  void moveForwardFromEast() {
    var mower = mowerOrientedToEast();
    mower.moveForward();
    assertEquals(mowerMovedToEast().coordinates(), mower.coordinates());
  }

  @Test
  void moveForwardFromSouth() {
    var mower = mowerOrientedToSouth();
    mower.moveForward();
    assertEquals(mowerMovedToSouth().coordinates(), mower.coordinates());
  }

  @Test
  void moveForwardFromWest() {
    var mower = mowerOrientedToWest();
    mower.moveForward();
    assertEquals(mowerMovedToWest().coordinates(), mower.coordinates());
  }

  private Mower mowerMovedToNorth() {
    return new Mower(coordinatesMovedToNorth(), NORTH);
  }

  private Mower mowerMovedToEast() {
    return new Mower(coordinatesMovedToEast(), EAST);
  }

  private Mower mowerMovedToSouth() {
    return new Mower(coordinatesMovedToSouth(), SOUTH);
  }

  private Mower mowerMovedToWest() {
    return new Mower(coordinatesMovedToWest(), WEST);
  }

  private Mower mowerOrientedToNorth() {
    return new Mower(coordinates(), NORTH);
  }

  private Mower mowerOrientedToEast() {
    return new Mower(coordinates(), EAST);
  }

  private Mower mowerOrientedToSouth() {
    return new Mower(coordinates(), SOUTH);
  }

  private Mower mowerOrientedToWest() {
    return new Mower(coordinates(), WEST);
  }

  private Coordinates coordinates() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private Coordinates coordinatesMovedToNorth() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y + 1);
  }

  private Coordinates coordinatesMovedToEast() {
    return new Coordinates(COORDINATE_X + 1, COORDINATE_Y);
  }

  private Coordinates coordinatesMovedToSouth() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y - 1);
  }

  private Coordinates coordinatesMovedToWest() {
    return new Coordinates(COORDINATE_X - 1, COORDINATE_Y);
  }

}
