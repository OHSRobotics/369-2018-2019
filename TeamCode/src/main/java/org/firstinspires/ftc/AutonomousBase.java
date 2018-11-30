package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;


public class AutonomousBase extends OpModeBase {

    public HardwareK9bot robot = new HardwareK9bot();
    public MovementHelper helper;
    public Position position;
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
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        waitForStart();

        //do inital things that are the same across all auto's

        runOpModeImpl();
    }

    public void runOpModeImpl(){
    }


    public void move(double distance, double angle)
    {
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
                robot.leftDrive.setPower(leftDrive);
                robot.leftBack.setPower(leftBack);
                robot.rightDrive.setPower(rightDrive);
                robot.rightBack.setPower(rightBack);
            }
        }
    }

    public void turnToPixy()
    {
        double pixyHalf = 1.7;
        telemetry.addData("started turn to pixy", "");
        telemetry.update();
        if(robot.pixy.getVoltage()>0.05)
            position = Position.MIDDLE;
        while(robot.pixy.getVoltage() < 0.05 && opModeIsActive() && robot.leftBack.getCurrentPosition() < 1000){
            telemetry.addData("started right turn", "");
            telemetry.update();
            position = Position.RIGHT;
            robot.leftBack.setPower(.2);
            robot.rightBack.setPower(-.2);
            robot.leftDrive.setPower(.2);
            robot.rightDrive.setPower(-.2);
        }
        while(robot.pixy.getVoltage() < 0.05 && opModeIsActive() && robot.rightBack.getCurrentPosition() < 2000){
            telemetry.addData("started left turn", "");
            telemetry.update();
            position = Position.LEFT;
            robot.leftBack.setPower(-.2);
            robot.rightBack.setPower(.2);
            robot.leftDrive.setPower(-.2);
            robot.rightDrive.setPower(.2);
        }
        //reset encoders, might get rid of this
        for (DcMotor motor : robot.motors) {
            motor.setPower(0);
        while(opModeIsActive() && Math.abs(robot.pixy.getVoltage() - pixyHalf) > .1){
            telemetry.addData("votlage", robot.pixy.getVoltage());
            telemetry.update();
            double error = (robot.pixy.getVoltage() - pixyHalf)/(pixyHalf);
            error *= -.3;
            if(error < 0)
                error -=.1;
            else
                error += .1;
            robot.leftBack.setPower(-error);
            robot.leftDrive.setPower(-error);
            robot.rightBack.setPower(error);
            robot.rightDrive.setPower(error);
        }
        for (DcMotor motor : robot.motors)
            motor.setPower(0);
    }

    public void deployBot()
    {

    }
    public void dropTrophy() {
        //do something
    }
    enum Position {
        LEFT, MIDDLE, RIGHT;
    }

}
