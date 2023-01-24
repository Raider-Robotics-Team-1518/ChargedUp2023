// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  CANCoder leftFront = new CANCoder(11);
  CANCoder leftRear = new CANCoder(12);
  CANCoder rightRear = new CANCoder(13);
  CANCoder rightFront = new CANCoder(14);
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
    
    SmartDashboard.putNumber("L Front ABS", leftFront.getAbsolutePosition());
    SmartDashboard.putNumber("L Front REL", leftFront.getPosition());

    SmartDashboard.putNumber("L Rear ABS", leftRear.getAbsolutePosition());
    SmartDashboard.putNumber("L Rear REL", leftRear.getPosition());

    SmartDashboard.putNumber("R Rear ABS", rightRear.getAbsolutePosition());
    SmartDashboard.putNumber("R Rear REL", rightRear.getPosition());

    SmartDashboard.putNumber("R Front ABS", rightFront.getAbsolutePosition());
    SmartDashboard.putNumber("R Front REL", rightFront.getPosition());
  }

  @Override
  public void testExit() {}
}
