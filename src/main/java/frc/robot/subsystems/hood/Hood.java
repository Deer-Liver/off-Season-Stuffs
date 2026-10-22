package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Degree;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.units.measure.Angle;
import static edu.wpi.first.units.Units.Degrees;


public class Hood {
    public Hood() {
      configureMotors();
    }

    //Method to configure the motors
    public void configureMotors() {
      hoodMotor.getConfigurator().apply(talonFXConfiguration);
    }

    TalonFX hoodMotor = new TalonFX(HoodConstants.kHoodMotorID);
    
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
}
