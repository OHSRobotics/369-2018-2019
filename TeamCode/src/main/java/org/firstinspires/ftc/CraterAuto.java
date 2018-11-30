package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.Constants.*;

import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name="Left Red Autonomous", group="K9bot")
public class CraterAuto extends AutonomousBase {

    public CraterAuto() {
        super(true);
    }

    @Override
    public void runOpModeImpl() {
        //move forward for a short bit
        while(opModeIsActive() && robot.leftBack.getCurrentPosition() < 200)
            for(DcMotor motor: robot.motors)
                motor.setPower(.4);
        for(DcMotor motor: robot.motors) {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        //turn to yellow block, move forward
        turnToPixy();

        //calculate the average encoder readings of the 3 wheels, need to replace later
        double distanceDrivenToBlock = (robot.leftBack.getCurrentPosition()+robot.rightBack.getCurrentPosition()+robot.rightDrive.getCurrentPosition())/3;
        //drive back to position
        while(opModeIsActive() && robot.leftBack.getCurrentPosition() < distanceDrivenToBlock)
            for(DcMotor motor : robot.motors)
                motor.setPower(-1);
        for(DcMotor motor : robot.motors)
            motor.setPower(0);
        double fastness = 0.2;
        switch(position) {
            case LEFT:


                break;
            case MIDDLE:

                break;
            case RIGHT:

                break;
        }
        dropTrophy();
        helper.drive(-fastness, 8);

    }
}
