package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.Constants.*;

@Autonomous(name="Left Red Autonomous", group="K9bot")
public class CraterAuto extends AutonomousBase {

    public CraterAuto() {
        super(true);
    }

    @Override
    public void runOpModeImpl() {
        turnToPixy();
    }
}
