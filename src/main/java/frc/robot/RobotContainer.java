// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.hood.Hood;
import frc.robot.subsystems.hood.hoodCommands.*;

public class RobotContainer {
  private final CommandXboxController driver = new CommandXboxController(0);
  Hood hood;
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    hood.setDefaultCommand(new RunVolts(hood, () -> Volts.of(0)));
    
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
