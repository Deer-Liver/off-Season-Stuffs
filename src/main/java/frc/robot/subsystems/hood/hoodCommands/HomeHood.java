package frc.robot.subsystems.hood.hoodCommands;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.hood.Hood;
import frc.robot.subsystems.hood.HoodConstants;

public class HomeHood extends Command {
    Hood hood;
    public HomeHood(Hood hood) {
        this.hood = hood;
        addRequirements(hood);
        }

    @Override
    public void initialize() {}
    
    @Override
    public void execute() {
        hood.runVoltage(HoodConstants.kHoodHomingVoltage);
    }

    @Override
    public boolean isFinished() {
         return hood.atHomedPosition();
    }

    @Override
    public void end(boolean interrupted) {
        hood.zeroEncoder();
        hood.runVoltage(Voltage.of(0)); //TODO FIX
    }
}