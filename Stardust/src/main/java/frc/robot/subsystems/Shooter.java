package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.motors.DBugSparkMax;
import frc.robot.motors.PIDFGains;

public class Shooter extends SubsystemBase {
    
    private DBugSparkMax _left_motor, _right_motor;
    private ShooterState _state;
    
    public Shooter() {
        this._left_motor = DBugSparkMax.create(
            ShooterConstants.LEFT_SPARKMAX_CANID,
            new PIDFGains(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD, ShooterConstants.kF),
            0, 0, 0);

        this._right_motor = DBugSparkMax.create(
            ShooterConstants.RIGHT_SPARKMAX_CANID,
            new PIDFGains(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD, ShooterConstants.kF),
            0, 0, 0);

        this._right_motor.follow(_left_motor);
        this._right_motor.setInverted(true);
    }

    public enum ShooterState {
        SHOOT(ShooterConstants.SHOOT_PERCENT),
        OFF(0);

        private double _percent_output;

        private ShooterState(double percent) {
            this._percent_output = percent;
        }

        public double getPercentOutput() {
            return _percent_output;
        }
    }

    public void setState(ShooterState state) {
        this._left_motor.set(state.getPercentOutput());
        this._state = state;
    }

    public InstantCommand getSetStateCommand(ShooterState state) {
        return new InstantCommand(() -> this.setState(state));
    }

    public ShooterState getState() {
        return _state;
    }
}
