package com.mower.application;

import com.mower.console.CommandConsole;
import com.mower.console.ConsoleApplication;
import com.mower.usecase.ExecuteMowerCommandsInPlateau;

import java.nio.charset.Charset;
import java.util.Scanner;

public class MowerApp {

  public static void main(String... args) {
    consoleApplication().start(new ExecuteMowerCommandsInPlateau());
  }

  private static ConsoleApplication consoleApplication() {
    return new ConsoleApplication(systemInputCommandConsole());
  }

  private static CommandConsole systemInputCommandConsole() {
    return new CommandConsole(new Scanner(System.in, Charset.defaultCharset()));
  }
}
