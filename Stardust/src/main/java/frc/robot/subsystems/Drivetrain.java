package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    
    // Motor definitions
    private Talon _talonL, _talonR;
    private VictorSPX _victorLF, _victorLB, _victorRF, _victorRB;

    public Drivetrain() {
        this._talonR = new Talon(Constants.DrivetrainConstants.TALON_R_PORT);
        this._talonL = new Talon(Constants.DrivetrainConstants.TALON_L_PORT);

        this._victorLF = new VictorSPX(Constants.DrivetrainConstants.VICTOR_LF_PORT);
        this._victorLB = new VictorSPX(Constants.DrivetrainConstants.VICTOR_LB_PORT);
        this._victorRF = new VictorSPX(Constants.DrivetrainConstants.VICTOR_RF_PORT);
        this._victorRB = new VictorSPX(Constants.DrivetrainConstants.VICTOR_RB_PORT);

        // Define followers
        this._victorLB.follow((IMotorController) _talonL);
        this._victorLF.follow((IMotorController) _talonL);

        this._victorRB.follow((IMotorController) _talonR);
        this._victorRF.follow((IMotorController) _talonR);

        // TODO: Config controllers (gains, inverted)
        this._talonR.setInverted(true);
    }

    public void setPercent(double percentLeft, double percentRight) {
        this._talonL.set(percentLeft);
        this._talonR.set(percentRight);
    }
}
