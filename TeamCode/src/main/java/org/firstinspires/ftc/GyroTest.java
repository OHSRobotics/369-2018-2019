package org.firstinspires.ftc;

public class GyroTest{/*


    //public class Gyro extends OpModeBase {/*

        public void gyroTurnTo(double heading) {
            HardwareK9bot robot = new HardwareK9bot();
            robot.gyro.calibrate();

            while (robot.gyro.getHeading() != heading) {
                if (robot.gyro.getHeading() - heading > 180)
                    telemetry.addData("Turn robot right ");
                else if (robot.gyro.getHeading() - heading < 180
                telemetry.addData("Turn robot left"));
            }
            telemetry.addData("Robot is currently at inputted gyro heading");
        }
    }

    */
}
