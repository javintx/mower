package com.mower.domain;

import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;

public final class Mower {
  private Coordinates coordinates;
  private FaceTo faceTo;

  public Mower(final Coordinates initialCoordinates, final FaceTo initialFaceTo) {
    this.coordinates = initialCoordinates;
    this.faceTo = initialFaceTo;
  }

  public Coordinates coordinates() {
    return coordinates;
  }

  public void executeCommand(final Command command) {
    switch (command) {
      case MOVE:
        moveForward();
        break;
      case LEFT:
        spinLeft();
        break;
      case RIGHT:
        spinRight();
        break;
    }
  }

  private void moveForward() {
    this.coordinates = this.coordinates.moveTowards(this.faceTo.orientation());
  }

  private void spinRight() {
    this.faceTo = this.faceTo.spinRight();
  }

  private void spinLeft() {
    this.faceTo = this.faceTo.spinLeft();
  }

  public String situation() {
    return coordinates.situation() + " " + faceTo.situation();
  }

}
