package frc.robot.subsystems.hood.hoodCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.hood.Hood;

public class HomeHood extends Command {
    Hood hood;
    public HomeHood(Hood hood) {
        this.hood = hood;
        addRequirements(hood);
        }
    }