package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
@TeleOp(name="Telop Final", group="K9bot")
public class TeleOPNotFinalEdition extends OpModeBase{
    HardwareK9bot   robot           = new HardwareK9bot();
    @Override
    public void runOpMode()
    {
        robot.init(hardwareMap);
        telemetry.addData("Say", "haha justin #2 is an idi0t");    //
        telemetry.update();
        waitForStart();
        while(opModeIsActive())
        {
            double turn = getGamepad(1).right_stick_x;
            double lateral = getGamepad(1).left_stick_x;
            double forward = -getGamepad(1).left_stick_y;

            double leftDrive, leftBack, rightDrive, rightBack;

            leftDrive = forward + lateral + turn;
            leftBack = forward - lateral +  turn;
            rightDrive = forward - lateral - turn;
            rightBack = forward + lateral - turn;

            robot.shaftController.setPower(getGamepad(2).right_trigger- getGamepad(2).left_trigger);
            robot.leftDrive.setPower(leftDrive);
            robot.leftBack.setPower(leftBack);
            robot.rightDrive.setPower(rightDrive);
            robot.rightBack.setPower(rightBack);

            if(getGamepad(2).b)
                robot.shoulder.setPower(.1);
            else if(getGamepad(2).a)
                robot.shoulder.setPower(-.1);

            if(getGamepad(2).x)
                robot.dustbin.setPower(.1);

            if(getGamepad(2).dpad_up)
                robot.elbow.setPower(.1);
            else if(getGamepad(2).dpad_down)
                robot.elbow.setPower(.1);

            telemetry.update();
            sleep(20);
        }

    }
    private Gamepad getGamepad(int number) {
        if(number == 1) return gamepad1;
        else return gamepad2;
    }
}
