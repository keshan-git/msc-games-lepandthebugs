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
public class DoubleXY
{
    private double x;
    private double y;

    public DoubleXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public DoubleXY()
    {
        this.x = 0;
        this.y = 0;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }
    
    
}
