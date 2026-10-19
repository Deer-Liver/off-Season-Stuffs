package frc.robot.subsystems.pivot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class Pivot {
    public Pivot() {
      configureMotors();
    }

    //Method to configure the motors
    public void configureMotors() {
          final TalonFXConfiguration talonFXConfiguration = 
      new TalonFXConfiguration()
        .withCurrentLimits(currentLimits)
        .withMotorOutput(motorOutput)
        .withFeedback(feedbackConfigs);
    }
    TalonFX pivotMotor = new TalonFX(PivotConstants.kPivotMotorID);
    
    final CurrentLimitsConfigs currentLimits = 
      new CurrentLimitsConfigs
        ().withSupplyCurrentLimit(PivotConstants.kPivotMaxCurrentSupply)
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
}
