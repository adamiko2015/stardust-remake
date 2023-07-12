package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    
    // Motor definitions
    private TalonSRX _talonL, _talonR;
    private VictorSPX _victorLF, _victorLB, _victorRF, _victorRB;

    public Drivetrain() {
        this._talonR = new TalonSRX(Constants.DrivetrainConstants.TALON_R_CANID);
        this._talonL = new TalonSRX(Constants.DrivetrainConstants.TALON_L_CANID);

        this._victorLF = new VictorSPX(Constants.DrivetrainConstants.VICTOR_LF_CANID);
        this._victorLB = new VictorSPX(Constants.DrivetrainConstants.VICTOR_LB_CANID);
        this._victorRF = new VictorSPX(Constants.DrivetrainConstants.VICTOR_RF_CANID);
        this._victorRB = new VictorSPX(Constants.DrivetrainConstants.VICTOR_RB_CANID);

        // Define followers
        this._victorLB.follow((IMotorController) _talonL);
        this._victorLF.follow((IMotorController) _talonL);

        this._victorRB.follow((IMotorController) _talonR);
        this._victorRF.follow((IMotorController) _talonR);

        this._talonR.setInverted(true);
    }

    public void setPercent(double percentLeft, double percentRight) {
        this._talonL.set(TalonSRXControlMode.PercentOutput, percentLeft);
        this._talonR.set(TalonSRXControlMode.PercentOutput, percentRight);
    }
}
