package com.mower.domain;

import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;

public class Mower {
  private final Coordinates coordinates;
  private final FaceTo faceTo;

  public Mower(final Coordinates initialCoordinates, final FaceTo initialFaceTo) {
    this.coordinates = initialCoordinates;
    this.faceTo = initialFaceTo;
  }

  public Coordinates coordinates() {
    return coordinates;
  }

  public void executeCommand(Command command) {
    command.execute(this);
  }

  public void moveForward() {
    this.coordinates.moveTowards(this.faceTo.orientation());
  }

  public void spinRight() {
    this.faceTo.spinRight();
  }

  public void spinLeft() {
    this.faceTo.spinLeft();
  }

  public String situation() {
    return coordinates.situation() + " " + faceTo.situation();
  }
}
