
package lepandthebugs.scene.gameunit;

import java.awt.Graphics;
import java.util.Objects;

/**
 *
 * @author Keshan De Silva
 */
public abstract class GameUnit
{
    private String gameObjectName;
    private double xPosition;
    private double yPosition;
    private double angle;
    private boolean visible;
        
    public GameUnit()
    {
        this(0, 0);
    }
    
    public GameUnit(double xPosition, double yPosition)
    {
        this.gameObjectName = getGameObjectName();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.angle = 0;
        this.visible = true;
    }

    public abstract String getGameObjectName();
    public abstract void drawGameObject(Graphics graphics);

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
    
    public void updatePosition(double xPosition, double yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;            
    }
    
    public void updateXPosition(double xPosition)
    {
        this.xPosition = xPosition;         
    }
    
    public void updateYPosition(double yPosition)
    {
        this.yPosition = yPosition;         
    }
    
    public void updateAngle(double angle)
    {
        this.angle = angle;
    }
    
    public abstract void flip();
    
    public double getXPosition()
    {
        return xPosition;
    }

    public double getYPosition()
    {
        return yPosition;
    }

    public double getAngle()
    {
        return angle;
    }

    @Override
    public boolean equals(Object obj)
    {
        if ((obj == null) || (getClass() != obj.getClass()))
        {
            return false;
        }
        
        final GameUnit other = (GameUnit) obj;
        return Objects.equals(this.gameObjectName, other.gameObjectName);
    }
    
    
}
