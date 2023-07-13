package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ExtractorConstants;

public class Extractor extends SubsystemBase {
    
    private TalonSRX _motor;
    private ExtractorState _state;
    
    public Extractor() {
        this._motor = new TalonSRX(ExtractorConstants.TALON_CANID);
    }

    public enum ExtractorState {
        EXTRACT(ExtractorConstants.EXTRACT_PERCENT),
        OFF(0);

        private double _percent_output;

        private ExtractorState(double percent) {
            this._percent_output = percent;
        }

        public double getPercentOutput() {
            return _percent_output;
        }
    }

    public void setState(ExtractorState state) {
        this._motor.set(ControlMode.PercentOutput, state.getPercentOutput());
        this._state = state;
    }

    public InstantCommand getSetStateCommand(ExtractorState state) {
        return new InstantCommand(() -> this.setState(state));
    }

    public ExtractorState gExtractorState() {
        return _state;
    }
}
