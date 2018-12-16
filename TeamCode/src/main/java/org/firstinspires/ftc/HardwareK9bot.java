package org.firstinspires.ftc;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a K9 robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 *
 * Motor channel:  Raising the claw:         "shoulder"
 * Motor channel:  Raising the joint:        "elbow"
 * Motor channel:  Pull mechanism:           "shaft"
 *
 *
 * Servo channel:  Servo to raise/lower arm: "arm"
 * Servo channel:  Servo to open/close claw: "claw"
 *
 * Note: the configuration of the servos is such that:
 *   As the arm servo approaches 0, the arm position moves up (away from the floor).
 *   As the claw servo approaches 0, the claw opens up (drops the game element).
 */
public class HardwareK9bot
{
    /* Public OpMode members. */
    public DcMotor leftBack = null;
    public DcMotor rightBack = null;
    public DcMotor  leftDrive   = null;
    public DcMotor  rightDrive  = null;
    public DcMotor shaft2 = null;
    public DcMotor rake = null;
    public DcMotor shaft1 = null;
    //public DcMotor dustbin = null;
    public DcMotor[] motors = new DcMotor[4];
    //public Servo binBlock = null;
    public AnalogInput pixy = null;
    //public GyroUnshafter gyro;
    public ModernRoboticsI2cGyro gyro = null;

    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareK9bot() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        //Color_Sensor = hwMap.get(ColorSensor.class, "sensor_color");
        leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        leftBack = hwMap.get(DcMotor.class, "left_back");
        rightBack = hwMap.get(DcMotor.class, "right_back");
        shaft1 = hwMap.get(DcMotor.class, "shaft1");
        shaft2 = hwMap.get(DcMotor.class, "shaft2");
        rake = hwMap.get(DcMotor.class, "rake");
        pixy = hwMap.get(AnalogInput.class, "pixy");

        //grabberL = hwMap.get(Servo.class, "gripL");
        //grabberR = hwMap.get(Servo.class, "gripR");
        //tail = hwMap.get(Servo.class, "tail");
        //gyro = new GyroUnshafter(hwMap.get(ModernRoboticsI2cGyro.class, "gyro"));
        gyro = hwMap.get(ModernRoboticsI2cGyro.class, "gyro");

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        motors[0] = rightDrive;
        motors[1] = leftDrive;
        motors[2] = leftBack;
        motors[3] = rightBack;

        shaft1.setPower(0);
        shaft1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shaft2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}