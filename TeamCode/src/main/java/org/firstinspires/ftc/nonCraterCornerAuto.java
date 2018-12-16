package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



@Autonomous(name="Game Piece Side", group="K9bot")
public class nonCraterCornerAuto extends AutonomousBase {

    public nonCraterCornerAuto() {
        super(true);
    }

    @Override
    public void runOpModeImpl() {
        turnToPixyNew();
        helper.drive(.3, 35);
        if(position == Position.LEFT)
            gyroRotate(325, .1);
        else if(position == Position.RIGHT)
            gyroRotate(35, .1);
        gamePiece();
    }
}
