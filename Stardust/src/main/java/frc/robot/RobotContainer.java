// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.humanIO.Joysticks;
import frc.robot.humanIO.PS5Controller.Button;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Extractor;
import frc.robot.subsystems.Pizza;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Extractor.ExtractorState;
import frc.robot.subsystems.Pizza.PizzaState;
import frc.robot.subsystems.Shooter.ShooterState;

public class RobotContainer {

  // Subsystem definitions
  private Drivetrain m_drivetrain = new Drivetrain();
  private Pizza m_pizza = new Pizza();
  private Extractor m_extractor = new Extractor();
  private Shooter m_shooter = new Shooter(); 

  private Joysticks m_joysticks = new Joysticks();

  public RobotContainer() {
    configureButtonBindings();

    // DR LEFT STICK - LEFT WHEELS, DR RIGHT STICK - RIGHT WHEELS
    m_drivetrain.setDefaultCommand(new RunCommand(
      () -> m_drivetrain.setPercent(m_joysticks.getRawDriveLeftY(), m_joysticks.getRawDriveRightY())    
      , m_drivetrain));
  }

  private void configureButtonBindings() {
    // OP CIRCLE - Rotate pizza clockwise
    m_joysticks.getOperatorButton(Button.kCircle).onTrue(m_pizza.getSetStateCommand(PizzaState.CLOCKWISE));
    
    // OP CROSS - Rotate pizza counterclockwise
    m_joysticks.getOperatorButton(Button.kCross).onTrue(m_pizza.getSetStateCommand(PizzaState.COUNTER_CLOCKWISE));

    // OP R1 - Shoot
    m_joysticks.getOperatorButton(Button.kR1).whileTrue(getShootCommand());
  }

  private StartEndCommand getShootCommand() {
    return new StartEndCommand(
      () -> {
        m_shooter.setState(ShooterState.SHOOT);
        m_pizza.setState(PizzaState.CLOCKWISE);
        m_extractor.setState(ExtractorState.EXTRACT);
      }, 
      () -> {
        m_shooter.setState(ShooterState.OFF);
        m_pizza.setState(PizzaState.OFF);
        m_extractor.setState(ExtractorState.OFF);
      }, 
      m_shooter, m_pizza, m_extractor);
  }

  public Command getAutonomousCommand() {
    return null;
  }

}
