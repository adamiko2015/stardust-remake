// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
  public static class DrivetrainConstants {
    public static final int TALON_L_CANID = 6;
    public static final int TALON_R_CANID = 1;
    
    public static final int VICTOR_LF_CANID = 3;
    public static final int VICTOR_LB_CANID = 2;
    public static final int VICTOR_RF_CANID = 4;
    public static final int VICTOR_RB_CANID = 5;
  }

  public static class PizzaConstants {
    public static final int TALON_CANID = 10;

    public static final double CLOCKWISE_PERCENT = 0.4;
    public static final double COUNTER_CLOCKWISE_PERCENT = -1.0;
  }

  public static class ExtractorConstants {
    public static final int TALON_CANID = 8;

    public static final double EXTRACT_PERCENT = 0;
  }

  public static class ShooterConstants {
    public static final int LEFT_SPARKMAX_CANID = 14;
    public static final int RIGHT_SPARKMAX_CANID = 12;

    public static final double PEAK_CURRENT = 0;

    public static final double SHOOT_PERCENT = 0;

    public static final double kF = 0, kP = 0, kD = 0, kI = 0;
  }

  public static class Joysticks {
    public static final double DEADBAND = 0;
  }
}
