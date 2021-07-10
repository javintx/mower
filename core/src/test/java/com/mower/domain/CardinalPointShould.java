package com.mower.domain;

import com.mower.domain.exception.UnknownCardinalPointCode;
import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardinalPointShould {

  private static final String UNKNOWN_CARDINAL_POINT_CODE = "UNKNOWN CARDINAL POINT CODE";

  @Test
  void throwUnknownCardinalPointCodeIfCardinalPointCodeIsUnknown() {
    assertThrows(UnknownCardinalPointCode.class, () -> CardinalPoint.fromCode(UNKNOWN_CARDINAL_POINT_CODE));
  }

  @Test
  void returnNorthFromNCode() {
    assertSame(NORTH, CardinalPoint.fromCode(NORTH.code()));
  }

  @Test
  void returnEastFromECode() {
    assertSame(EAST, CardinalPoint.fromCode(EAST.code()));
  }

  @Test
  void returnSouthFromSCode() {
    assertSame(SOUTH, CardinalPoint.fromCode(SOUTH.code()));
  }

  @Test
  void returnWestFromWCode() {
    assertSame(WEST, CardinalPoint.fromCode(WEST.code()));
  }

}
