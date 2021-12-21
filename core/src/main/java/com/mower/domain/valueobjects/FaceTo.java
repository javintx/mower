package com.mower.domain.valueobjects;

import com.mower.domain.CardinalPoint;

import java.util.Objects;

public final class FaceTo {
  private static final int FIRST_CARDINAL_POINT_INDEX = 0;
  private final CardinalPoint cardinalPointOrientation;

  public FaceTo(CardinalPoint initialOrientation) {
    this.cardinalPointOrientation = initialOrientation;
  }

  public CardinalPoint orientation() {
    return cardinalPointOrientation;
  }

  public FaceTo spinRight() {
    return new FaceTo(rightCardinalPoint());
  }

  public FaceTo spinLeft() {
    return new FaceTo(leftCardinalPoint());
  }

  public String situation() {
    return cardinalPointOrientation.code();
  }

  private CardinalPoint rightCardinalPoint() {
    if (isTheLastCardinalPoint()) {
      return getFirstCardinalPoint();
    }
    int nextCardinalPositionIndex = cardinalPointOrientation.ordinal() + 1;
    return CardinalPoint.values()[nextCardinalPositionIndex];
  }

  private boolean isTheLastCardinalPoint() {
    return cardinalPointOrientation.ordinal() == CardinalPoint.values().length - 1;
  }

  private CardinalPoint getFirstCardinalPoint() {
    return CardinalPoint.values()[FIRST_CARDINAL_POINT_INDEX];
  }

  private CardinalPoint leftCardinalPoint() {
    if (isTheFirstCardinalPoint()) {
      return getLastCardinalPoint();
    }
    int previousCardinalPositionIndex = cardinalPointOrientation.ordinal() - 1;
    return CardinalPoint.values()[previousCardinalPositionIndex];
  }

  private boolean isTheFirstCardinalPoint() {
    return cardinalPointOrientation.ordinal() == FIRST_CARDINAL_POINT_INDEX;
  }

  private CardinalPoint getLastCardinalPoint() {
    int previousCardinalPointIndex = CardinalPoint.values().length - 1;
    return CardinalPoint.values()[previousCardinalPointIndex];
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FaceTo faceTo = (FaceTo) o;
    return cardinalPointOrientation == faceTo.cardinalPointOrientation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardinalPointOrientation);
  }
}
