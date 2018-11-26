package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class MotorTest extends OpModeBase  {

    HardwareK9bot testMotors = new HardwareK9bot();
    int stupid = 0;
    public void runMotors () {
        int i = 0;
        while (opModeIsActive()) {
            testMotors.leftBack.setPower(1);
            testMotors.rightBack.setPower(1);
            testMotors.leftDrive.setPower(1);
            testMotors.rightDrive.setPower(1);
            telemetry.addData("Left Back Motor: ", ""+ testMotors.leftBack.getCurrentPosition());
            telemetry.addData("Right Back Motor: ", ""+ testMotors.rightBack.getCurrentPosition());
            telemetry.addData("Left Front Motor: ", ""+ testMotors.leftDrive.getCurrentPosition());
            telemetry.addData("RIght Front Motor: ", ""+testMotors.rightDrive.getCurrentPosition());
            telemetry.update();
        }
    }


    }