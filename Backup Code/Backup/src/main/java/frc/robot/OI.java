/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
  public XboxController XboxController = new XboxController(RobotMap.XboxController.value);
  public boolean toggleOn = false;
  public boolean togglePressed = false;
  DigitalInput forwardLimitSwitch = new DigitalInput(RobotMap.forwardLimitSwitch.value);
  DigitalInput backwardLimitSwitch = new DigitalInput(RobotMap.backwardLimitSwitch.value);
}