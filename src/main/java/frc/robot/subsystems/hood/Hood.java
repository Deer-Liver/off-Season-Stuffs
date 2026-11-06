package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.DegreesPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Hood extends SubsystemBase {
    public Hood() {
      configureMotors();
    }

    //Method to configure the motors
    public void configureMotors() {
      hoodMotor.getConfigurator().apply(talonFXConfiguration);
    }

    private double kP = 0;
    private double kI = 0;
    private double kD = 0;

    private double kS = 0;
    private double kG = 0;
    private double kV = 0;

    // ProfiledPIDController(double HoodConstants.kP, Double HoodConstants.kI, double HoodConstants.kD, TrapezoidProfile.Constraints constraints) {

    // }
    // Constraints(double maxVelocity, double maxAcceleration) {

    // }

    // public Angle CurrentAngle = Degrees.of(30);
    // public Angle targetAngle = Degrees.of(45);
    // public Angle CurrentAngle = Degrees.of(hoodController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));



    TalonFX hoodMotor = new TalonFX(HoodConstants.kHoodMotorID);
    private Angle targetAngle = HoodConstants.kStartingAngle;
    Constraints PIDConstraints = new Constraints(HoodConstants.kMaxVelocity.in(DegreesPerSecond),HoodConstants.kMaxAcceleration.in(DegreesPerSecondPerSecond));
    ProfiledPIDController hoodController = new ProfiledPIDController(kP, kI, kD, PIDConstraints);
    ArmFeedforward hoodFeedforward = new ArmFeedforward(kS, kG, kV);
    public Object zerEncoder;

    final CurrentLimitsConfigs currentLimits = 
      new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(HoodConstants.kHoodMaxCurrentSupply)
        .withSupplyCurrentLimitEnable(HoodConstants.kHoodCurrentLimitSupplyEnabled)
        .withStatorCurrentLimit(HoodConstants.kHoodMaxCurrentStator)
        .withStatorCurrentLimitEnable(HoodConstants.kHoodCurrentLimitStatorEnabled);

    final MotorOutputConfigs motorOutput =
      new MotorOutputConfigs()
        .withNeutralMode(HoodConstants.kNuetralMode)
        .withInverted(HoodConstants.kInversion);

    final FeedbackConfigs feedbackConfigs =
      new FeedbackConfigs()
        .withSensorToMechanismRatio(HoodConstants.kHoodGearRatio);

    final TalonFXConfiguration talonFXConfiguration = 
      new TalonFXConfiguration()
        .withCurrentLimits(currentLimits)
        .withMotorOutput(motorOutput)
        .withFeedback(feedbackConfigs);

    public void goToAngle(Angle targetAngle) {
      Angle currentAngle = getAngle();

      Voltage calculatedPIDVoltage = Volts.of(hoodController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));
      Voltage calculatedFeedForwardVoltage = Volts.of(hoodFeedforward.calculate(targetAngle.in(Degrees), 0));

      Voltage desiredVoltage = Volts.of(calculatedFeedForwardVoltage.in(Volts) + calculatedPIDVoltage.in(Volts));

      hoodMotor.setVoltage(desiredVoltage.in(Volts));
    }

    public void runVoltage(Voltage desiredVoltage) {
      hoodMotor.setVoltage(desiredVoltage.in(Volts));
    }

    

    @Logged(name = "HoodPitch")
    public Angle getAngle() {
      Angle angle = hoodMotor.getPosition().getValue();
      return angle;
    }
  
  @Logged(name = "hoodAtTargetAngle")
  public boolean atTargetAngle() {
    boolean atTargetAngle = Math.abs(getAngle().in(Degrees) - HoodConstants.kTargetAngle.in(Degrees)) <= HoodConstants.kHoodAngleTolerance.in(Degrees);
    return atTargetAngle;
  }

  @Logged(name = "HoodVoltage")
  public Voltage getVoltage() {
    return this.hoodMotor.getMotorVoltage().getValue();
  }

  @Logged(name = "HoodCurrent")
  public Current getCurrent() {
    return this.hoodMotor.getStatorCurrent().getValue();
  }


  // @Override
  public void periodic() {
    SmartDashboard.putNumber("Hood Angle", hoodMotor.getPosition().getValue().in(Degrees));
  }

  public boolean atHomedPosition() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'atHomedPosition'");
  }

  public void zeroEncoder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'coder'");
  }

  public void setTargetAngle(Angle angle) {
    this.targetAngle = angle;
  }
}
