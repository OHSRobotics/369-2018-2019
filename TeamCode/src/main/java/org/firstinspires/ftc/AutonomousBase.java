package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;


public abstract class AutonomousBase extends OpModeBase {

    private HardwareK9bot robot = new HardwareK9bot();
    public MovementHelper helper;

    private boolean red;

    public AutonomousBase(boolean red) {
        this.helper = new MovementHelper(red, robot, this);
        this.red = red;
    }

    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        for(DcMotor motor : robot.motors){
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        waitForStart();

        //do inital things that are the same across all auto's

        runOpModeImpl();
    }

    public abstract void runOpModeImpl();

    public void move(double distance, double angle, double power)
    {
        power = Math.abs(power);
        //set x,y coords
        double lateral = Math.cos(angle);
        double forward = -Math.sin(angle);

        double leftDrive, leftBack, rightDrive, rightBack;

        //mecanum wheel function
        leftDrive = forward + lateral;
        leftBack = forward - lateral;
        rightDrive = forward - lateral;
        rightBack = forward + lateral;

        //track ticks of left wheel for distance
        double ticks = 0;
        if(leftDrive != 0) {
            robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            while (opModeIsActive() && ticks < 537.6/Math.PI * distance * Math.sqrt(2)) {
                ticks = robot.leftDrive.getCurrentPosition();
                robot.leftDrive.setPower(leftDrive * power);
                robot.leftBack.setPower(leftBack * power);
                robot.rightDrive.setPower(rightDrive * power);
                robot.rightBack.setPower(rightBack * power);
            }
        }
        else //track ticks of right wheel for distance
        {
            robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            while (opModeIsActive() && ticks < 537.6/Math.PI * distance * Math.sqrt(2)) {
                ticks = robot.rightDrive.getCurrentPosition();
                robot.leftDrive.setPower(leftDrive * power);
                robot.leftBack.setPower(leftBack * power);
                robot.rightDrive.setPower(rightDrive * power);
                robot.rightBack.setPower(rightBack * power);
            }
        }

    }

    public void deployBot()
    {

    }
    public void dropTrophy() {
        //do something
    }
    public int readPixy()
    {
        return 0;
    }
    enum Position {
        LEFT, MIDDLE, RIGHT;
    }

}
