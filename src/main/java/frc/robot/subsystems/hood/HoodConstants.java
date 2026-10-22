package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Amp;
/* Static Imports */
import static edu.wpi.first.units.Units.Degrees;
// import static edu.wpi.first.units.Units.Radian;
import static edu.wpi.first.units.Units.Volt;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

/* Non-static imports */
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;

public class HoodConstants {
    /*
    These are BS constants we had to make for an assignment, they have no purpose in this subsytem; ignore them.

    public static final String kRobotName = "Walter"; // Robot name
    public static final Angle kFeedAngle = Degrees.of(15.0);
    public static final double kFeedAngleDegrees = kFeedAngle.in(Degrees);
    public static final double kFeedAngleRadian = kFeedAngle.in(Radian);
    */

    /* Real Constants */   

    // Voltage and Amp Limits
    public static final Voltage kHoodMaxVoltage = Volt.of(12.0);
    public static final Current kHoodMaxCurrentSupply = Amp.of(40.0);
    public static final Current kHoodMaxCurrentStator = Amp.of(40.0);
    
    public static final Boolean kHoodCurrentLimitSupplyEnabled = true;
    public static final Boolean kHoodCurrentLimitStatorEnabled = true;

    // Starting Angle for the hood
    public static final Angle kStartAngle = Degrees.of(0);

    public static final int kHoodMotorID = 11; // CAN ID for the hood motor
    public static final double kHoodGearRatio = 142.9; // Gear ratio for the hood motor
    public static final InvertedValue kInversion = InvertedValue.Clockwise_Positive; // Inversion for the hood motor
    public static final NeutralModeValue kNuetralMode = NeutralModeValue.Brake; // Neutral mode for the hood motor

    public static final Angle kHoodAngleTolerance = Degrees.of(1.5); // Tolerance for the hood angle
    public static final Angle kTargetAngle = Degrees.of(45.0); // Target angle for the hood

    

}