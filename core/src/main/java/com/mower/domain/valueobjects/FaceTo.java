package com.mower.domain.valueobjects;

import com.mower.domain.CardinalPoint;

public class FaceTo {
  private static final int FIRST_CARDINAL_POINT_INDEX = 0;
  private CardinalPoint cardinalPointOrientation;

  public FaceTo(CardinalPoint initialOrientation) {
    this.cardinalPointOrientation = initialOrientation;
  }

  public CardinalPoint orientation() {
    return cardinalPointOrientation;
  }

  public void spinRight() {
    this.cardinalPointOrientation = rightCardinalPoint();
  }

  public void spinLeft() {
    this.cardinalPointOrientation = leftCardinalPoint();
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
}
