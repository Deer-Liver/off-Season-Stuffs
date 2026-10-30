package frc.robot.subsystems.pivot;

/* Static Imports */
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.DegreesPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volt;
import static edu.wpi.first.units.Units.Amp;

/* Non-static imports */
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class PivotConstants {
    // Voltage and Amp Limits
    public static final Voltage kPivotMaxVoltage = Volt.of(12.0);
    public static final Current kPivotMaxCurrentSupply = Amp.of(40.0);
    public static final Current kPivotMaxCurrentStator = Amp.of(40.0);
    
    public static final Boolean kPivotCurrentLimitSupplyEnabled = true;
    public static final Boolean kPivotCurrentLimitStatorEnabled = true;

    // Starting Angle for the pivot
    public static final Angle kStartAngle = Degrees.of(0);

    public static final int kPivotMotorID = 11; // CAN ID for the pivot motor
    public static final double kPivotGearRatio = 142.9; // Gear ratio for the pivot motor
    public static final InvertedValue kInversion = InvertedValue.Clockwise_Positive; // Inversion for the pivot motor
    public static final NeutralModeValue kNuetralMode = NeutralModeValue.Brake; // Neutral mode for the pivot motor

    public static final AngularVelocity kMaxVelocity = DegreesPerSecond.of(0);
    public static final AngularAcceleration kMaxAcceleration = DegreesPerSecondPerSecond.of(0);

    public static final Angle kPivotAngleTolerance = Degrees.of(1.5); // Tolerance for the pivot angle
    public static final Angle kTargetAngle = Degrees.of(45.0); // Target angle for the pivot
}
