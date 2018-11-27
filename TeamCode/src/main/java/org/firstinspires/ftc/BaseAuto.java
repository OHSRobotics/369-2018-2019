package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.Constants.*;

@Autonomous(name = "Test Autonomous", group = "K9Bot")
public class BaseAuto extends AutonomousBase{

    public BaseAuto(){super(true);}

    @Override
    public void runOpModeImpl()
    {
        double fastness = 0.2;
        Position place = Position.LEFT;
        deployBot();
        switch(place){
            case LEFT:
                helper.drive(fastness, FOOT_SQRT_2);
                helper.rotate(45, fastness, false);
                helper.drive(fastness, 24);
                helper.goSideways(fastness, -36);
                dropTrophy();
                helper.rotate(90,fastness, false);
                helper.drive(fastness, 96);
                break;
            case MIDDLE:
                helper.drive(fastness, 4*FOOT_SQRT_2);
                dropTrophy();
                helper.rotate(-45, fastness, false);
                break;
            case RIGHT:
                helper.drive(fastness, FOOT_SQRT_2);
                helper.rotate(45,fastness, false);
                helper.drive(fastness, 24);
                helper.goSideways(fastness, 36);
                dropTrophy();
                helper.rotate(90,fastness, false);
                helper.drive(fastness, 96);
                break;
        }
    }
}
