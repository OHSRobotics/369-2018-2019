package org.firstinspires.ftc;
//import pixy package
public class PixyData {
    private double position; //value btwn 0 and 3.3
    public PixyData()
    {
        position = 0.0;
    }
    public void pixyUpdate(double pos)
    {
        position = pos; //not sure if this one is needed
    }
    public double getPosition()
    {
        return position; //probably won't be needed, but added here for convenience
    }
}
