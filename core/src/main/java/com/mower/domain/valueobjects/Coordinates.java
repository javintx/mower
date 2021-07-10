package com.mower.domain.valueobjects;

import com.mower.domain.CardinalPoint;
import com.mower.domain.exception.CoordinatesMustBePositiveNumbers;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;

public class Coordinates {
  private final static Map<CardinalPoint, Consumer<Coordinates>> MOVEMENTS = Map.of(
      NORTH, Coordinates::upward,
      EAST, Coordinates::forward,
      SOUTH, Coordinates::downward,
      WEST, Coordinates::backward
  );
  private final AtomicInteger coordinateX;
  private final AtomicInteger coordinateY;

  public Coordinates(int initialCoordinateX, int initialCoordinateY) {
    validatePositiveCoordinates(initialCoordinateX, initialCoordinateY);
    this.coordinateX = new AtomicInteger(initialCoordinateX);
    this.coordinateY = new AtomicInteger(initialCoordinateY);
  }

  private void validatePositiveCoordinates(int initialCoordinateX, int initialCoordinateY) {
    if (initialCoordinateX < 0 || initialCoordinateY < 0) {
      throw new CoordinatesMustBePositiveNumbers(initialCoordinateX, initialCoordinateY);
    }
  }

  public void moveTowards(CardinalPoint cardinalPoint) {
    MOVEMENTS.get(cardinalPoint).accept(this);
  }

  private void forward() {
    coordinateX.incrementAndGet();
  }

  private void backward() {
    coordinateX.decrementAndGet();
  }

  private void upward() {
    coordinateY.incrementAndGet();
  }

  private void downward() {
    coordinateY.decrementAndGet();
  }

  public int coordinateX() {
    return coordinateX.get();
  }

  public int coordinateY() {
    return coordinateY.get();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinates that = (Coordinates) o;
    return coordinateX.get() == that.coordinateX.get() && coordinateY.get() == that.coordinateY.get();
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinateX.get(), coordinateY.get());
  }
}
