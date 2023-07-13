package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PizzaConstants;

public class Pizza extends SubsystemBase {

    private TalonSRX _motor;
    private PizzaState _state;

    public Pizza() {
        this._motor = new TalonSRX(PizzaConstants.TALON_CANID);
    }

    public enum PizzaState {
        CLOCKWISE(PizzaConstants.CLOCKWISE_PERCENT), 
        COUNTER_CLOCKWISE(PizzaConstants.COUNTER_CLOCKWISE_PERCENT),
        OFF(0);

        private double _percent_output;

        private PizzaState(double percent) {
            this._percent_output = percent;
        }

        public double getPercentOutput() {
            return this._percent_output;
        }
    }

    public void setState(PizzaState state) {
        this._motor.set(ControlMode.PercentOutput, state.getPercentOutput());
        this._state = state;
    }

    public CommandBase getSetStateCommand(PizzaState state) {
        return new InstantCommand(() -> this.setState(state), this);
    }

    public PizzaState getState() {
        return this._state;
    }
}