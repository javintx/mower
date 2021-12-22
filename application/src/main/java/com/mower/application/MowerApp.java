package com.mower.application;

import com.mower.console.CommandConsole;
import com.mower.console.ConsoleApplication;
import com.mower.usecase.ExecuteMowerCommandsInPlateau;

import java.nio.charset.Charset;
import java.util.Scanner;

public class MowerApp {

  private static ConsoleApplication consoleApplication = new ConsoleApplication(systemInputCommandConsole());

  public static void main(String... args) {
    consoleApplication.start(new ExecuteMowerCommandsInPlateau());
  }

  static void initForTestPurposes(final ConsoleApplication testConsoleApplication) {
    consoleApplication = testConsoleApplication;
  }

  private static CommandConsole systemInputCommandConsole() {
    return new CommandConsole(new Scanner(System.in, Charset.defaultCharset()));
  }
}
