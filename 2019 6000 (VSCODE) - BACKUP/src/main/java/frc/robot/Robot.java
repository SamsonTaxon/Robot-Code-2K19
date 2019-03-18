/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Puncher;
import frc.robot.OI;


public class Robot extends TimedRobot {
  public static Drivetrain drivetrain;
  public static Intake intake;
  public static Lifter lifter;
  public static Puncher puncher;
  public static OI oi;

  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();
    intake = new Intake();
    lifter = new Lifter();
    puncher = new Puncher();
    oi = new OI();
    
  }
 
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateToggle();

    // Lift Controls
    lifter.Lifter1.set(oi.XboxController.getTriggerAxis(Hand.kRight)-oi.XboxController.getTriggerAxis(Hand.kLeft));
    lifter.Lifter2.set(0.3 * oi.XboxController.getY(Hand.kLeft));

    // Pivot Control
    intake.intakePivot.set(oi.XboxController.getX(Hand.kLeft));
    
    // Arcade Drive
    drivetrain.drivetrain.arcadeDrive(RobotMap2.power.value * oi.XboxController.getY(Hand.kRight), RobotMap2.power.value * oi.XboxController.getX(Hand.kRight));
  
    // Punch Control
    if (oi.toggleOn) {
      // Punch
      puncher.punchBackward.set(false);
      puncher.punchForward.set(true);
    } else{
      // Retract punch
      puncher.punchForward.set(false);
      puncher.punchBackward.set(true);
    }
  }

	private void updateToggle() {
		 if(oi.XboxController.getRawButton(1)){
        if(!oi.togglePressed){
            oi.toggleOn = !oi.toggleOn;
            oi.togglePressed = true;
        }
        }else{
            oi.togglePressed = false;
        }
	}
}