/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6000.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Command;



import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.DifferentialDrive;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private XboxController xboxController;
	//	private Joystick xboxController; 
	private DifferentialDrive m_myRobot;
//	private DifferentialDrive Lifter;
	private Spark Lifter;
	private Spark LifterS2;
//	private Spark Liftup;
//	private Spark Liftdown;
	private Spark IntakePivot;
	private Solenoid punchForward;
	private Solenoid punchBackward;
	DigitalInput forwardLimitSwitch, reverseLimitSwitch;

	
	//private Joystick m_rightStick;
    //private Joystick m_leftStick;
	
	@Override
	public void robotInit() {
	//	punch.set(DoubleSolenoid.Value.kForward);
		
		m_myRobot = new DifferentialDrive(new Spark(1), new Spark(0));
	//	m_leftStick = new Joystick(0);
	//	m_rightStick = new Joystick(0);
		xboxController = new XboxController(0);
		//Lifter = new DifferentialDrive(new Spark(4), new Spark(6));
		Lifter = new Spark (5);
		LifterS2 = new Spark (6);
	//	Liftup = new Spark(5);
	//	Liftdown = new Spark(6);
		punchForward = new Solenoid(2);
		punchBackward = new Solenoid(1);

		IntakePivot = new Spark (7);

		DigitalInput forwardLimitSwitch = new DigitalInput(1);
        DigitalInput reverseLimitSwitch = new DigitalInput(2);
		
	}

	 	  boolean toggleOn = false;
	      boolean togglePressed = false;
	@Override
	public void teleopPeriodic() {
		updateToggle();
		
		
		/* 	THESE ARE THE LIFT CONTROLS */

		Lifter.set(xboxController.getTriggerAxis(Hand.kRight)-xboxController.getTriggerAxis(Hand.kLeft));
		LifterS2.set(0.3 * xboxController.getY(Hand.kLeft));
		
		/* THIS IS THE PIVOT CONTROL */ 

		IntakePivot.set(xboxController.getX(Hand.kLeft));

		
	/* THIS IS TANK DRIVE FOR XBOX CONTROLLER */	
		
//	m_myRobot.tankDrive(0.8 * xboxController.getY(Hand.kRight), 0.8 * xboxController.getY(Hand.kLeft));


//	Liftup.set(xboxController.getTriggerAxis(Hand.kRight));
	


	/* THIS IS ARCADE DRIVE FOR XBOX CONTROLLER */	
		
//	m_myRobot.arcadeDrive(xboxController.getY(Hand.kLeft), xboxController.getX(Hand.kRight));	
		
	/* THIS IS SINGLE STICK ARCADE DRIVE FOR XBOX CONTROLLER */		
		
	m_myRobot.arcadeDrive(0.8 * xboxController.getY(Hand.kRight), 0.8 * xboxController.getX(Hand.kRight));
	



//
//        IntakePivot.set(xboxController.getRawButton(14));


	/* PUNCHER TOGGLE */	
	
	if(toggleOn){
        // Punch
		punchBackward.set(false);
		punchForward.set(true);
    }else{
        // Retract punch
    	punchForward.set(false);
    	punchBackward.set(true);
    }
}
	
	
	private void updateToggle() {
		// TODO Auto-generated method stub
		 if(xboxController.getRawButton(1)){
	            if(!togglePressed){
	                toggleOn = !toggleOn;
	                togglePressed = true;
	            }
	        }else{
	            togglePressed = false;
	        }
		
	}



}