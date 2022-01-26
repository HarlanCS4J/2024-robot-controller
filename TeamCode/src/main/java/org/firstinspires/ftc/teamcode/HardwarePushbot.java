

package org.firstinspires.ftc.teamcode;


import android.app.Activity;
import android.view.View;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.TouchSensor;



public class HardwarePushbot {
    /* Public OpMode members. */
    ///////////////// Motors

    public static DcMotor frontLeft = null;
    public static DcMotor backLeft = null;
    public static DcMotor frontRight = null;
    public static DcMotor backRight = null;


    public static DcMotor arm = null;

    public static DcMotor intake = null;





    ///////////////Servos

//public static Servo testServo = null;
    /////////////Sensors
    //public static DistanceSensor FrontDistance = null;
    //public static DigitalChannel CascadeTouch = null;  // Hardware Device Object
    public static TouchSensor armstop = null;
    public static TouchSensor elbowstop = null;
    public static CRServo turntable = null;

    /*

        public static ColorSensor colorSensor;
        public static DistanceSensor colorDistance;
        public static DistanceSensor backRangeSensor;
        */
    public static BNO055IMU imu;// Additional Gyro device
    /* local OpMode members. */
    static HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public HardwarePushbot() {

    }

    /* Initialize standard Hardware interfaces */
    public static void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backRight = hwMap.get(DcMotor.class, "backRight");
        arm = hwMap.get(DcMotor.class, "arm");
        intake = hwMap.get(DcMotor.class, "intake");






        // Define and initialize Servos
        turntable =  hwMap.get(CRServo.class, "turntable");


        //Define and Initialize Sensors///////////////////

        armstop = hwMap.get(TouchSensor.class,"armstop");
        elbowstop = hwMap.get(TouchSensor.class, "elbowstop");



        imu = hwMap.get(BNO055IMU.class, "imu");

        // Set
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);






        // Set all motors to zero power
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        arm.setPower(0);
        intake.setPower(0);




        // rollers.setPower(0);


        // Set zero power behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




        //rollers.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);





    }
        public static void autoinit (HardwareMap ahwMap){
            // Save reference to Hardware map
            hwMap = ahwMap;

            //imu stuff
            imu = hwMap.get(BNO055IMU.class, "imu");
           BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
            parameters.loggingEnabled = true;
            parameters.loggingTag = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            imu.initialize(parameters);


            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            frontRight.setDirection(DcMotor.Direction.FORWARD);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
            backRight.setDirection(DcMotor.Direction.FORWARD);

            //////close claw
            //
        }
    }