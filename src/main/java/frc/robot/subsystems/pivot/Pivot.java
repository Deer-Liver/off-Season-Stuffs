package frc.robot.subsystems.pivot;

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


public class Pivot {
    public Pivot() {
      configureMotors();
    }

    //Method to configure the motors
    public void configureMotors() {
      pivotMotor.getConfigurator().apply(talonFXConfiguration);
    }

    private double kP = 0;
    private double kI = 0;
    private double kD = 0;

    private double kS = 0;
    private double kG = 0;
    private double kV = 0;

    // ProfiledPIDController(double PivotConstants.kP, Double PivotConstants.kI, double PivotConstants.kD, TrapezoidProfile.Constraints constraints) {

    // }
    // Constraints(double maxVelocity, double maxAcceleration) {

    // }

    // public Angle CurrentAngle = Degrees.of(30);
    // public Angle targetAngle = Degrees.of(45);
    // public Angle CurrentAngle = Degrees.of(pivotController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));



    TalonFX pivotMotor = new TalonFX(PivotConstants.kPivotMotorID);
    
    final CurrentLimitsConfigs currentLimits = 
      new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(PivotConstants.kPivotMaxCurrentSupply)
        .withSupplyCurrentLimitEnable(PivotConstants.kPivotCurrentLimitSupplyEnabled)
        .withStatorCurrentLimit(PivotConstants.kPivotMaxCurrentStator)
        .withStatorCurrentLimitEnable(PivotConstants.kPivotCurrentLimitStatorEnabled);

    final MotorOutputConfigs motorOutput =
      new MotorOutputConfigs()
        .withNeutralMode(PivotConstants.kNuetralMode)
        .withInverted(PivotConstants.kInversion);

    final FeedbackConfigs feedbackConfigs =
      new FeedbackConfigs()
        .withSensorToMechanismRatio(PivotConstants.kPivotGearRatio);

    final TalonFXConfiguration talonFXConfiguration = 
      new TalonFXConfiguration()
        .withCurrentLimits(currentLimits)
        .withMotorOutput(motorOutput)
        .withFeedback(feedbackConfigs);

    Constraints PIDConstraints = new Constraints(PivotConstants.kMaxVelocity.in(DegreesPerSecond),PivotConstants.kMaxAcceleration.in(DegreesPerSecondPerSecond));

    ProfiledPIDController pivotController = new ProfiledPIDController(kP, kI, kD, PIDConstraints);
      ArmFeedforward pivotFeedforward = new ArmFeedforward(kS, kG, kV);


    public void goToAngle(Angle targetAngle) {
      Angle currentAngle = getAngle();

      Voltage calculatedPIDVoltage = Volts.of(pivotController.calculate(currentAngle.in(Degrees), targetAngle.in(Degrees)));
      Voltage calculatedFeedForwardVoltage = Volts.of(pivotFeedforward.calculate(targetAngle.in(Degrees), 0));

      Voltage desiredVoltage = Volts.of(calculatedFeedForwardVoltage.in(Volts) + calculatedPIDVoltage.in(Volts));

      pivotMotor.setVoltage(desiredVoltage.in(Volts));
    }

    

    @Logged(name = "PivotPitch")
    public Angle getAngle() {
      Angle angle = pivotMotor.getPosition().getValue();
      return angle;
    }
  
  @Logged(name = "pivotAtTargetAngle")
  public boolean atTargetAngle() {
    boolean atTargetAngle = Math.abs(getAngle().in(Degrees) - PivotConstants.kTargetAngle.in(Degrees)) <= PivotConstants.kPivotAngleTolerance.in(Degrees);
    return atTargetAngle;
  }

  @Logged(name = "PivotVoltage")
  public Voltage getVoltage() {
    return this.pivotMotor.getMotorVoltage().getValue();
  }

  @Logged(name = "PivotCurrent")
  public Current getCurrent() {
    return this.pivotMotor.getStatorCurrent().getValue();
  }


  // @Override
  public void periodic() {
    SmartDashboard.putNumber("Pivot Angle", pivotMotor.getPosition().getValue().in(Degrees));
  }
}
