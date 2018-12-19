package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



@Autonomous(name="Crater Side", group="K9bot")
public class CraterAuto extends AutonomousBase {

    public CraterAuto() {
        super(true);
    }

    @Override
    public void runOpModeImpl() {
       //gyroRotate (28, .2);
        turnToPixyNew();
        helper.drive(.3, 35);
        if(position == Position.LEFT)
            gyroRotate(330, .1);
        else if(position == Position.RIGHT)
            gyroRotate(30, .1);
        crater();
    }
}
