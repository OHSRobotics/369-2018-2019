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
    HardwareK9bot   robot           = new HardwareK9bot();
    private static boolean useSingleController = false;

    @Override
    public void runOpMode() {
        waitForStart();
        robot.init(hardwareMap);
        while(opModeIsActive()){
            //robot.pixy.getVoltage();
            telemetry.addData("heading", robot.gyro.getHeading());
            telemetry.update();
        }
    }

    private Gamepad getGamepad(int number) {
        if(useSingleController) return gamepad1;
        if(number == 1) return gamepad1;
        else return gamepad2;
    }
}
