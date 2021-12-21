package com.mower.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static com.mower.domain.CardinalPoint.EAST;
import static com.mower.domain.CardinalPoint.NORTH;
import static com.mower.domain.CardinalPoint.SOUTH;
import static com.mower.domain.CardinalPoint.WEST;
import static org.assertj.core.api.Assertions.assertThat;

class FaceToShould {
  @Test
  void returnNorthOrientation() {
    assertThat(faceToNorth().orientation()).isEqualTo(NORTH);
  }

  @Test
  void returnEastOrientation() {
    assertThat(faceToEast().orientation()).isEqualTo(EAST);
  }

  @Test
  void returnSouthOrientation() {
    assertThat(faceToSouth().orientation()).isEqualTo(SOUTH);
  }

  @Test
  void returnWestOrientation() {
    assertThat(faceToWest().orientation()).isEqualTo(WEST);
  }

  @Test
  void spinRightFromNorthToEast() {
    assertThat(faceToNorth().spinRight()).isEqualTo(faceToEast());
  }

  @Test
  void spinRightFromEastToSouth() {
    assertThat(faceToEast().spinRight()).isEqualTo(faceToSouth());
  }

  @Test
  void spinRightFromSouthToWest() {
    assertThat(faceToSouth().spinRight()).isEqualTo(faceToWest());
  }

  @Test
  void spinRightFromWestToNorth() {
    assertThat(faceToWest().spinRight()).isEqualTo(faceToNorth());
  }

  @Test
  void spinLeftFromNorthToWest() {
    assertThat(faceToNorth().spinLeft()).isEqualTo(faceToWest());
  }

  @Test
  void spinLeftFromEastToNorth() {
    assertThat(faceToEast().spinLeft()).isEqualTo(faceToNorth());
  }

  @Test
  void spinLeftFromSouthToEast() {
    assertThat(faceToSouth().spinLeft()).isEqualTo(faceToEast());
  }

  @Test
  void spinLeftFromWestToSouth() {
    assertThat(faceToWest().spinLeft()).isEqualTo(faceToSouth());
  }

  @Test
  void printFaceToNorthSituation() {
    assertThat(faceToNorth().situation()).isEqualTo(NORTH.code());
  }

  @Test
  void printFaceToEastSituation() {
    assertThat(faceToEast().situation()).isEqualTo(EAST.code());
  }

  @Test
  void printFaceToSouthSituation() {
    assertThat(faceToSouth().situation()).isEqualTo(SOUTH.code());
  }

  @Test
  void printFaceToWestSituation() {
    assertThat(faceToWest().situation()).isEqualTo(WEST.code());
  }

  private FaceTo faceToNorth() {
    return new FaceTo(NORTH);
  }

  private FaceTo faceToEast() {
    return new FaceTo(EAST);
  }

  private FaceTo faceToSouth() {
    return new FaceTo(SOUTH);
  }

  private FaceTo faceToWest() {
    return new FaceTo(WEST);
  }
}
