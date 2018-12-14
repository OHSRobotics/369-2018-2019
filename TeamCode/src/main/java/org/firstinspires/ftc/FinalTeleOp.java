package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Troy Neubauer on 10/28/2017.
 * Last year's code
 */

@TeleOp(name="Telop Test (if you use this in a tournament, you will lose)", group="K9bot")
public class FinalTeleOp extends OpModeBase {
    HardwareK9bot robot = new HardwareK9bot();
    private static boolean useSingleController = false;

    @Override
    public void runOpMode() {
        waitForStart();
        robot.init(hardwareMap);
        robot.gyro.calibrate();
        while (robot.gyro.isCalibrating() && opModeIsActive()) {
            telemetry.addData("gyro calibrating", "");
            telemetry.update();
        }
        telemetry.addData("Calibration finished ", "");
        telemetry.update();
        int heading = 180;

        while (robot.gyro.getHeading() != heading && opModeIsActive()) {
            double scaledHeading = 0;
            if (robot.gyro.getHeading() <= 180) {
                if (heading >= robot.gyro.getHeading() && heading <= 180 + robot.gyro.getHeading())
                    scaledHeading = 1 - Math.abs((180 + robot.gyro.getHeading() - heading) / 180.0);
                else if (heading > 180 + robot.gyro.getHeading() && heading < 360)
                    scaledHeading = -1 + (heading - 180 + robot.gyro.getHeading() / 180.0);
            } else if (robot.gyro.getHeading() > 180) {
                if (heading >= robot.gyro.getHeading() || heading <= robot.gyro.getHeading() - 180)
                    scaledHeading = ((360 + heading - robot.gyro.getHeading()) % 360) / 180.0;
                else if (heading > robot.gyro.getHeading() - 180 && heading < robot.gyro.getHeading())
                    scaledHeading = (heading - robot.gyro.getHeading()) / 180.0;
            }


            if (scaledHeading > 0) {
                telemetry.addData("Turn left", "");
                telemetry.addData("Gyro Heading", scaledHeading);
            } else {
                telemetry.addData("Turn right", "");
                telemetry.addData("Gyro Heading", scaledHeading);
            }

            telemetry.update();
        }
        telemetry.addData("Robot is currently at inputted gyro heading", "");
        telemetry.update();

    }


    /*private Gamepad getGamepad(int number) {
        if(useSingleController) return gamepad1;
        if(number == 1) return gamepad1;
        else return gamepad2;
    }*/


}


