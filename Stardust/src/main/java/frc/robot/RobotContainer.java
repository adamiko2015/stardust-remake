// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.humanIO.Joysticks;
import frc.robot.humanIO.PS5Controller.Button;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pizza;
import frc.robot.subsystems.Pizza.PizzaState;

public class RobotContainer {

  // Subsystem definitions
  private Drivetrain m_drivetrain = new Drivetrain();
  private Pizza m_pizza = new Pizza();

  private Joysticks m_joysticks = new Joysticks();

  public RobotContainer() {
    configureButtonBindings();

    m_drivetrain.setDefaultCommand(new RunCommand(
      () -> m_drivetrain.setPercent(m_joysticks.getRawDriveLeftY(), m_joysticks.getRawDriveRightY())    
      , m_drivetrain));

    // CIRCLE - Rotate pizza clockwise
    m_joysticks.getOperatorButton(Button.kCircle).onTrue(new InstantCommand(
      () -> m_pizza.setState(PizzaState.CLOCKWISE), 
      m_pizza));

    // CROSS - Rotate pizza counterclockwise
    m_joysticks.getOperatorButton(Button.kCross).onTrue(new InstantCommand(
      () -> m_pizza.setState(PizzaState.COUNTER_CLOCKWISE),
      m_pizza
    ));
  }

  private void configureButtonBindings() {
    
  }

  public Command getAutonomousCommand() {
    return null;
  }

}
