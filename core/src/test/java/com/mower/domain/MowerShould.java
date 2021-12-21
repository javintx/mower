package com.mower.domain;

import com.mower.domain.valueobjects.Coordinates;
import com.mower.domain.valueobjects.FaceTo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.Command.LEFT;
import static com.mower.domain.Command.MOVE;
import static com.mower.domain.Command.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MowerShould {

  public static final String EXPECTED_MOWER_SITUATION = "1 2 N";
  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 2;
  private static final CardinalPoint CARDINAL_POINT = NORTH;

  @Test
  void returnCoordinates() {
    assertEquals(anyCoordinates(), anyMower().coordinates());
  }

  @Test
  void spinLeft() {
    var mower = anyMower();
    mower.executeCommand(LEFT);
    assertTrue(mower.situation().contains(anyFaceTo().spinLeft().situation()));
  }

  @Test
  void spinRight() {
    var mower = anyMower();
    mower.executeCommand(RIGHT);
    assertTrue(mower.situation().contains(anyFaceTo().spinRight().situation()));
  }

  @Test
  void moveForward() {
    var mower = anyMower();
    mower.executeCommand(MOVE);
    assertEquals(mower.coordinates(), anyCoordinatesForwarded());
  }

  @Test
  void returnSituation() {
    assertThat(anyMower().situation()).isEqualTo(EXPECTED_MOWER_SITUATION);
  }

  private Mower anyMower() {
    return new Mower(anyCoordinates(), anyFaceTo());
  }

  private Coordinates anyCoordinates() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y);
  }

  private Coordinates anyCoordinatesForwarded() {
    return new Coordinates(COORDINATE_X, COORDINATE_Y + 1);
  }

  private FaceTo anyFaceTo() {
    return new FaceTo(CARDINAL_POINT);
  }
}
