package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Troy Neubauer on 10/28/2017.
 * Last year's code
 */

@TeleOp(name="Telop No longer Final", group="K9bot")
public class FinalTeleOp extends OpModeBase {
    HardwareK9bot   robot           = new HardwareK9bot();
    private static boolean useSingleController = false;

    @Override
    public void runOpMode() {
        waitForStart();
        robot.init(hardwareMap);
        while(opModeIsActive()){
            //robot.pixy.getVoltage();
            telemetry.addData("votlage", robot.pixy.getVoltage());
            telemetry.update();
            double error = (robot.pixy.getVoltage() - 3.5/2)/(3.5/2);
            error *= -.3;
            if(robot.pixy.getVoltage() > .1){
                robot.leftBack.setPower(-error);
                robot.leftDrive.setPower(-error);
                robot.rightBack.setPower(error);
                robot.rightDrive.setPower(error);
            } else {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
                robot.leftBack.setPower(0);
                robot.rightBack.setPower(0);
            }
        }
    }

    private Gamepad getGamepad(int number) {
        if(useSingleController) return gamepad1;
        if(number == 1) return gamepad1;
        else return gamepad2;
    }
}
