package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;


public abstract class AutonomousBase extends OpModeBase {

    private HardwareK9bot   robot           = new HardwareK9bot();

    public MovementHelper helper;
    private boolean red;

    public AutonomousBase(boolean red) {
        this.helper = new MovementHelper(red, robot, this);
        this.red = red;
    }

    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        for(DcMotor motor : robot.motors){
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        waitForStart();

        //do inital things that are the same across all auto's

        runOpModeImpl();
    }

    public abstract void runOpModeImpl();


    public void dropTrophy() {
        //do something
    }

    enum Position {
        LEFT, MIDDLE, RIGHT;
    }

}
