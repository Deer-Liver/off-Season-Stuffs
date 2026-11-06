/* (C) RoboLancers 2026 */
package frc.robot.subsystems.hood.hoodCommands;

import static edu.wpi.first.units.Units.Volts;

import java.util.function.Supplier;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.hood.Hood;

public class GoToAngle extends Command {

  Hood hood;
  Supplier<Angle> angleSupplier;

  public GoToAngle(Hood hood, Supplier<Angle> angleSupplier) {
    this.hood = hood;
    this.angleSupplier = angleSupplier;
    addRequirements(hood);
  }

  @Override
  public void initialize() {
    hood.setTargetAngle(angleSupplier.get());
  }

  @Override
  public void execute() {
    hood.goToAngle(angleSupplier.get());
  }

  @Override
  public boolean isFinished() {
    return hood.atTargetAngle();
  }

  @Override
  public void end(boolean interrupted) {

    hood.runVoltage(Volts.of(0.0));
  }
}
