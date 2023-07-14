package frc.robot.subsystems;


import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.motors.DBugSparkMax;

public class Shooter extends SubsystemBase {
    private DBugSparkMax _left_motor, _right_motor;
    private ShooterState _state;
    
    public Shooter() {
        this._left_motor = DBugSparkMax.create(
            ShooterConstants.LEFT_SPARKMAX_CANID,
            ShooterConstants.State.Shoot.GAINS_LEFT,
            0, 0, 0);

        this._right_motor = DBugSparkMax.create(
            ShooterConstants.RIGHT_SPARKMAX_CANID,
            ShooterConstants.State.Shoot.GAINS_RIGHT,
            0, 0, 0);

        this._right_motor.follow(_left_motor);
        this._right_motor.setInverted(true);

        this._left_motor.setSmartCurrentLimit(ShooterConstants.PEAK_CURRENT);
        this._right_motor.setSmartCurrentLimit(ShooterConstants.PEAK_CURRENT);
    }

    public enum ShooterState {
        SHOOT(ShooterConstants.State.Shoot.SHOOT_VELOCITY),
        OFF(0);

        private double _velocity;

        private ShooterState(double velocity) {
            this._velocity = velocity;
        }

        public double getVelocity() {
            return _velocity;
        }
    }

    public void setState(ShooterState state) {
        this._left_motor.setReference(0, ControlType.kVelocity);
        this._state = state;
    }

    public InstantCommand getSetStateCommand(ShooterState state) {
        return new InstantCommand(() -> this.setState(state));
    }

    public ShooterState getState() {
        return _state;
    }
}
