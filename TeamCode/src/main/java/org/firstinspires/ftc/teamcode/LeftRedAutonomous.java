package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

@Autonomous(name="Left Red Autonomous", group="K9bot")
public class LeftRedAutonomous extends AutonomousBase {

    public LeftRedAutonomous() {
        super(true);
    }

    @Override
    public void runOpModeImpl() {
        newJewel();
        helper.rotate(87, 0.15, false);
        helper.drive(0.2, 27);
        helper.rotate(45, -0.15, false);
        if (vuMark == RelicRecoveryVuMark.LEFT) {
            helper.diaganolDrive(0.2, 5, 'l');
        }
        else if (vuMark == RelicRecoveryVuMark.RIGHT) {
            helper.diaganolDrive(-.2, -8, 'l');
        }
        else if((vuMark ==  RelicRecoveryVuMark.CENTER) || (vuMark == RelicRecoveryVuMark.UNKNOWN)) {
            helper.diaganolDrive(-.2, -1, 'l');
        }
        helper.diaganolDrive(0.4, 10, 'r');
    }
}
