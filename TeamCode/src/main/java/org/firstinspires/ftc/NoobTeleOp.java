package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Telop Practice", group="K9bot")
public class NoobTeleOp extends LinearOpMode
{
    HardwareK9bot robot = new HardwareK9bot();

    //abstract public void runOpMode() throws InterruptedException;

    @Override
    public void runOpMode() /*throws InturruptedException*/
    {
        robot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive())
        {
            double backward = gamepad1.left_stick_y;
            robot.rightBack.setPower(backward);
            robot.rightDrive.setPower(backward);
            robot.leftBack.setPower(backward);
            robot.leftDrive.setPower(backward);

            double left = gamepad1.left_stick_x;
            robot.rightBack.setPower(left);
            robot.rightDrive.setPower(left);
            robot.leftBack.setPower(left);
            robot.leftDrive.setPower(left);

        if (backward == gamepad1.left_stick_y) {
            robot.rightBack.setPower(backward);
            robot.rightDrive.setPower(backward);
            robot.leftBack.setPower(backward);
            robot.leftDrive.setPower(backward);
        }
        if (left == gamepad1.left_stick_x) {
                robot.rightBack.setPower(left);
                robot.rightDrive.setPower(left);
                robot.leftBack.setPower(left);
                robot.leftDrive.setPower(left);
            }




            }


            }
        }
