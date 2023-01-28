package frc.robot.command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drive.SwerveTrain;

public class SwerveZeroModulePositions extends CommandBase {
    private final SwerveTrain m_drivetrainSubsystem;
    Timer timer = new Timer();
    /** Creates a new SwerveZeroModulePositions. */
    public SwerveZeroModulePositions(SwerveTrain drivetrainSubsystem) {
      this.m_drivetrainSubsystem = drivetrainSubsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(drivetrainSubsystem);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      timer.reset();
      timer.start();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      timer.stop();
      if(!interrupted && DriverStation.isDisabled()){
        m_drivetrainSubsystem.setModuleZeros();
      }else if(interrupted){
        System.out.println("WARNING: RESET COMMAND INTERRUPTED - RESET FAILED");
        DriverStation.reportWarning("WARNING: RESET COMMAND INTERRUPTED - RESET FAILED",false);
      }else if(!DriverStation.isDisabled()){
        System.out.println("WARNING: ROBOT NOT DISABLED (DISABLE ROBOT!) - RESET FAILED");
        DriverStation.reportWarning("WARNING: ROBOT NOT DISABLED (DISABLE ROBOT!) - RESET FAILED",false);
      }
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return (timer.get() >= 10) || !DriverStation.isDisabled();
    }
  
    public boolean runsWhenDisabled(){
      return true;
    }
  }
  