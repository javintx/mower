package com.mower.domain;

import java.util.Objects;

public class Coordinates {
  private final int coordinateX;
  private final int coordinateY;

  public Coordinates(int coordinateX, int coordinateY) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  public int coordinateX() {
    return coordinateX;
  }

  public int coordinateY() {
    return coordinateY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinates that = (Coordinates) o;
    return coordinateX == that.coordinateX && coordinateY == that.coordinateY;
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinateX, coordinateY);
  }
}
