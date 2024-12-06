
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware {
    
    public LinearOpMode myOpMode = null;
    
    // Declare OpMode members
    public ElapsedTime runtime = new ElapsedTime();
    
    public DcMotor leftFrontWheel = null; //Motors to control all wheels
    public DcMotor leftBackWheel = null;
    public DcMotor rightFrontWheel = null;
    public DcMotor rightBackWheel = null;

    public DcMotor leftLeg = null;
    public DcMotor rightLeg = null;
    
    public DcMotor spiralLift = null;
    public DcMotor spiralBrush = null;
    
    // Define a constructor that allows the OpMode to pass a reference to itself.


    /**
     * Initialize all the robot's hardware.
     * This method must be called ONCE when the OpMode is initialized.
     * <p>
     * All of the hardware devices are accessed via the hardware map, and initialized.
     */
     
    public RobotHardware (LinearOpMode opmode) {
        myOpMode = opmode;
    }
     
    public void init()    {
        
        leftFrontWheel  = myOpMode.hardwareMap.get(DcMotor.class, "leftFront");
        leftBackWheel   = myOpMode.hardwareMap.get(DcMotor.class, "leftBack");
        rightFrontWheel = myOpMode.hardwareMap.get(DcMotor.class, "rightFront");
        rightBackWheel  = myOpMode.hardwareMap.get(DcMotor.class, "rightBack");
       
        leftFrontWheel.setDirection(DcMotor.Direction.REVERSE);
        leftBackWheel.setDirection(DcMotor.Direction.REVERSE);
        rightFrontWheel.setDirection(DcMotor.Direction.FORWARD);
        rightBackWheel.setDirection(DcMotor.Direction.FORWARD);

        leftFoot        = myOpMode.hardwareMap.get(DcMotor.class, "myLeftFoot");
        rightFoot       = myOpMode.hardwareMap.get(DcMotor.class, "myRightFoot");

        spiralLift      = myOpMode.hardwareMap.get(DcMotor.class, "archimedes");
        spiralBrush     = myOpMode.hardwareMap.get(DcMotor.class, "brush");
        
        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

    /**
     * Calculates the left/right motor powers required to achieve the requested
     * robot motions: Drive (Axial motion) and Turn (Yaw motion).
     * Then sends these power levels to the motors.
     *
     * @param Drive     Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     * @param Turn      Right/Left turning power (-1.0 to 1.0) +ve is CW
    */
     
    double drive;
    double strafe;
    double turn;
     
    public void driveRobot(double drive, double strafe, double turn) {
    // Combine drive and turn and strafe for blended motion.
        double leftFrontPower  = - drive - strafe - turn;
        double rightFrontPower = - drive + strafe + turn;
        double leftBackPower   =   drive + strafe - turn;
        double rightBackPower  =   drive - strafe + turn;
        
    // Scale the values so neither exceed +/- 1.0          
        double max;
        double min;
        
    // Normalize the values so no wheel power exceeds 100%
    // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

    // Send calculated power to wheels
        leftFrontWheel.setPower(leftFrontPower);
        rightFrontWheel.setPower(rightFrontPower);
        leftBackWheel.setPower(leftBackPower);
        rightBackWheel.setPower(rightBackPower);

        myOpMode.telemetry.addData("Status", "Run Time: " + runtime.toString());
        myOpMode.telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        myOpMode.telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
        myOpMode.telemetry.update();
    }

    // public void setDrivePower(double leftWheel, double rightWheel) {
    //     // Output the values to the motor drives.
    //     leftDrive.setPower(leftWheel);
    //     rightDrive.setPower(rightWheel);
    // }

    // public void setArmPower(double power) {
    //     armMotor.setPower(power);
    // }

    public void setSweperPositions(double offset) { 
    //This code came from samples
        offset = Range.clip(offset, -0.5, 0.5);
        leftHand.setPosition(MID_SERVO + offset);
        rightHand.setPosition(MID_SERVO - offset);
    }

    public void standUp(double inches) {

    }
    
    public void liftScrew(double inches) {

    }
    
    public void toggleSweeper() {
      
    }
    
    public void setScrewPower(double spin) {

    }
    
    public void toggleDepositDoor() {

    }
}
