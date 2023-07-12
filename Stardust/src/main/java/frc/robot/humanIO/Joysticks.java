package frc.robot.humanIO;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.humanIO.PS5Controller.Button;

public class Joysticks {

    private PS5Controller _driveController, _operatorController;

    public Joysticks() {
        this._driveController = new PS5Controller(0);
        this._operatorController = new PS5Controller(1);
    }

    public JoystickButton getDriveButton(Button button) {
        return new JoystickButton(_driveController, button.value);
    }

    public JoystickButton getOperatorButton(Button button) {
        return new JoystickButton(_operatorController, button.value);
    }

    private static double calculateDeadband(double value, double other) {
        return Math.abs(value) > Constants.Joysticks.DEADBAND
                || Math.abs(other) > Constants.Joysticks.DEADBAND
                        ? value
                        : 0;
    }

    private double squareInputs(double input) {
        return Math.copySign(input * input, input);
    }

    public double getDriveX() {
        return squareInputs(calculateDeadband(-this._driveController.getLeftY(), -this._driveController.getLeftX()));
    }

    public double getDriveY() {
        return squareInputs(calculateDeadband(-this._driveController.getLeftX(), -this._driveController.getLeftY()));
    }

    public double getRawDriveLeftY() {
        return _driveController.getLeftY();
    }

    public double getRawDriveRightY() {
        return _driveController.getRightY();
    }
}