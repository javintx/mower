package com.mower;

import com.mower.application.ConsoleApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class MowerAppShould {
  @Mock
  ConsoleApplication consoleApplicationMocked;

  @Test
  public void main() {
    doNothing().when(consoleApplicationMocked).start();
    MowerApp.initForTestPurposes(consoleApplicationMocked);
    MowerApp.main();
  }
}
