package com.mower.usecase;

import com.mower.domain.Command;
import com.mower.domain.Mower;
import com.mower.domain.Plateau;

import java.util.List;

public interface ExecuteMowerCommandsInPlateauUseCase {
  void executeWith(final Plateau plateau, final Mower mower, final List<Command> commands);
}
