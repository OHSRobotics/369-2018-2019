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
        int heading = 250;

        while (robot.gyro.getHeading() != heading && opModeIsActive()) {
            double scaledHeading = 0;
            if (heading >= 0 && heading <= 180)
                scaledHeading = heading / 180.0;
            else if (heading > 180 && heading < 360)
                scaledHeading = -(360 - heading) / 180.0;
            telemetry.addData("Heading", scaledHeading);
            telemetry.update();

            /*if (robot.gyro.getHeading() - heading > 180)
                telemetry.addData("Turn robot left ", "");
            else if (robot.gyro.getHeading() - heading < 180)
                telemetry.addData("Turn robot right", "");
            telemetry.update();
        }
        telemetry.addData("Robot is currently at inputted gyro heading", "");
        telemetry.update();*/

       /* while (opModeIsActive()) {
            telemetry.addData("Heading", scaledHeading);
            telemetry.update();
        }*/
        }


    /*private Gamepad getGamepad(int number) {
        if(useSingleController) return gamepad1;
        if(number == 1) return gamepad1;
        else return gamepad2;
    }*/
    }
}

