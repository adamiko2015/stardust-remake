// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.humanIO.Joysticks;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  // Subsystem definitions
  private Drivetrain m_drivetrain = new Drivetrain();

  private Joysticks m_joysticks = new Joysticks();

  public RobotContainer() {
    configureButtonBindings();

    m_drivetrain.setDefaultCommand(new RunCommand(
      () -> m_drivetrain.setPercent(m_joysticks.getRawDriveLeftY(), m_joysticks.getRawDriveRightY())    
      , m_drivetrain));
  }

  private void configureButtonBindings() {
    
  }

  public Command getAutonomousCommand() {
    return null;
  }

}
