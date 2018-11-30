package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;


public class AutonomousBase extends OpModeBase {

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
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        waitForStart();

        //do inital things that are the same across all auto's

        runOpModeImpl();
    }

    public void runOpModeImpl(){
    }

    /*
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
                robot.leftDrive.setPower(leftDrive);
                robot.leftBack.setPower(leftBack);
                robot.rightDrive.setPower(rightDrive);
                robot.rightBack.setPower(rightBack);
            }
        }
        else //track ticks of right wheel for distance
        {
            robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            while (opModeIsActive() && ticks < 537.6/Math.PI * distance * Math.sqrt(2)) {
                ticks = robot.rightDrive.getCurrentPosition();
                robot.leftDrive.setPower(leftDrive);
                robot.leftBack.setPower(leftBack);
                robot.rightDrive.setPower(rightDrive);
                robot.rightBack.setPower(rightBack);
            }
        }

    }
    */
    public void turnToPixy()
    {
        double pixyhalf = 1.7;
        telemetry.addData("started turn to pixy", "");
        telemetry.update();
        boolean detected;
        while(robot.pixy.getVoltage() < 0.05 && opModeIsActive() && robot.leftBack.getCurrentPosition() < 600){
            telemetry.addData("started right turn", "");
            telemetry.update();
            robot.leftBack.setPower(.1);
            robot.rightBack.setPower(-.1);
            robot.leftDrive.setPower(.1);
            robot.rightDrive.setPower(-.1);
        }
        while(robot.pixy.getVoltage() < 0.05 && opModeIsActive() && robot.rightBack.getCurrentPosition() < 2000){
            telemetry.addData("started left turn", "");
            telemetry.update();
            robot.leftBack.setPower(-.1);
            robot.rightBack.setPower(.1);
            robot.leftDrive.setPower(-.1);
            robot.rightDrive.setPower(.1);
        }
        for (DcMotor motor : robot.motors)
            motor.setPower(0);
        while(opModeIsActive() && Math.abs(robot.pixy.getVoltage() - pixyhalf) > .1){
            telemetry.addData("votlage", robot.pixy.getVoltage());
            telemetry.update();
            double error = (robot.pixy.getVoltage() - pixyhalf)/(pixyhalf);
            error *= -.3;
            if(error < 0)
                error -= .1;
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
    public int readPixy()
    {
        return 0;
    }
    enum Position {
        LEFT, MIDDLE, RIGHT;
    }

}
