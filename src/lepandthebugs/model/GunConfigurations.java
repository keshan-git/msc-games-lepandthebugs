/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs.model;

/**
 *
 * @author Keshan De Silva
 */
public class GunConfigurations
{
    private double height;
    private double angle;
    private double velocity;

    public GunConfigurations(double height, double angle, double velocity)
    {
        this.height = height;
        this.angle = angle;
        this.velocity = velocity;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }
}
