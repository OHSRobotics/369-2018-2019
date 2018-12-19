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
        robot.gyro.calibrate();
        while(robot.gyro.isCalibrating() && opModeIsActive()){
            telemetry.addData("gyro calibrating","");
            telemetry.update();
        }
        //do inital things that are the same across all auto's

        runOpModeImpl();
    }

    public void runOpModeImpl(){
    }

    public void move(double distance, double angle, double power) {
        //set x,y coords
        angle += 90;
        angle *= Math.PI/180;
        double lateral = Math.cos(angle);
        double forward = Math.sin(angle);

        double leftDrive, leftBack, rightDrive, rightBack;

        //mecanum wheel function
        leftDrive = forward + lateral;
        leftBack = forward - lateral;
        rightDrive = forward - lateral;
        rightBack = forward + lateral;

        int ticks = maxEncoder();

        //track ticks of left wheel for distance
        while (opModeIsActive() && Math.abs(ticks) < distance) {
            ticks = robot.leftDrive.getCurrentPosition();
            robot.rightBack.setPower(rightBack);
            robot.leftBack.setPower(leftBack);
            robot.rightDrive.setPower(rightDrive);
            robot.leftDrive.setPower(leftDrive);
        }
        setAll(0);
    }

    public void gamePiece(){
        robot.rake.setPower(-.3);
        sleep(500);
        robot.rake.setPower(0);
    }

    public void crater(){
        robot.shaft2.setPower(.3);
        sleep(1500);
        robot.shaft2.setPower(0);
    }

    private void setAll(double power){
        for(DcMotor motor : robot.motors){
            motor.setPower(power);
        }
    }

    public void gyroRotate(int heading, double speed){
        while (Math.abs(robot.gyro.getHeading() - heading) > 5 && opModeIsActive()) {
            double scaledHeading = 0;

            if (robot.gyro.getHeading() <= 180) {
                if (heading >= robot.gyro.getHeading() && heading <= 180 + robot.gyro.getHeading())
                    scaledHeading = 1 - Math.abs((180 + robot.gyro.getHeading() - heading) / 180.0);
                else if (heading > 180 + robot.gyro.getHeading() && heading < 360)
                    scaledHeading = (heading - 180 + robot.gyro.getHeading()) / 180.0 - 1;
            }
            else if (robot.gyro.getHeading() > 180) {
                if (heading >= robot.gyro.getHeading() || heading <= robot.gyro.getHeading() - 180)
                    scaledHeading = ((360 + heading - robot.gyro.getHeading()) % 360) / 180.0;
                else if (heading > robot.gyro.getHeading() - 180 && heading < robot.gyro.getHeading())
                    scaledHeading = (heading - robot.gyro.getHeading()) / 180.0;
            }


            if (scaledHeading > 0) {
                robot.rightDrive.setPower(speed);
                robot.rightBack.setPower(speed);
                robot.leftDrive.setPower(-speed);
                robot.leftBack.setPower(-speed);
            } else if (scaledHeading < 0){
                robot.rightDrive.setPower(-speed);
                robot.rightBack.setPower(-speed);
                robot.leftDrive.setPower(speed);
                robot.leftBack.setPower(speed);
            }
            telemetry.update();
        }
        for(DcMotor motor: robot.motors)
            motor.setPower(0);
        telemetry.addData("Robot is currently at inputted gyro heading", "");
        telemetry.update();
    }

    private int maxEncoder(){
        return Math.max(Math.max(robot.leftBack.getCurrentPosition(), robot.rightBack.getCurrentPosition()),
                Math.max(robot.rightDrive.getCurrentPosition(), robot.leftDrive.getCurrentPosition()));
    }

    public void turnToPixyNew(){//works for the pixy camera on the side looking at two
        if(robot.pixy.getVoltage() < .5) {
            gyroRotate(28, .2);
            position = Position.LEFT;
        }else if (robot.pixy.getVoltage() > 1.5) {
            gyroRotate(332, .2);
            position = Position.RIGHT;
        } else
            position = Position.MIDDLE;
    }


    public void turnToPixy()//works if pixy cam is in the middle
    {
        double pixyHalf = 1.95;
        telemetry.addData("started turn to pixy", "");
        telemetry.update();
        if(robot.pixy.getVoltage()>0.05)
            position = Position.MIDDLE;
        while(robot.pixy.getVoltage() < 0.1 && opModeIsActive() && robot.leftBack.getCurrentPosition() < 600){
            telemetry.addData("started right turn", "");
            telemetry.update();
            position = Position.RIGHT;
            robot.leftBack.setPower(.1);
            robot.rightBack.setPower(-.1);
            robot.leftDrive.setPower(.1);
            robot.rightDrive.setPower(-.1);
        }
        while(robot.pixy.getVoltage() < 0.1 && opModeIsActive() && robot.rightBack.getCurrentPosition() < 2000)
        //reset encoders, might get rid of this
        for (DcMotor motor : robot.motors)
            motor.setPower(0);
        while(opModeIsActive() && Math.abs(robot.pixy.getVoltage() - pixyHalf) > .05){
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
