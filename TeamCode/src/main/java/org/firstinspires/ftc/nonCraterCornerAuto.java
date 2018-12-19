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

        if(position == Position.LEFT) {
            helper.drive(.3, 45);
            gyroRotate(330, .1);

        }else if(position == Position.RIGHT) {
            helper.drive(.3, 45);
            gyroRotate(30, .1);
        }else
            helper.drive(.3, 35);
        crater();
        gamePiece();
    }
}
