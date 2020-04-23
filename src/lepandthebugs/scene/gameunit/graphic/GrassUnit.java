
package lepandthebugs.scene.gameunit.graphic;

/**
 *
 * @author Keshan De Silva
 */
public class GrassUnit extends BasicImageGameUnit
{
    private static int counter = 0;
    
    public GrassUnit(int xPosition, int yPosition)
    {
        counter++;
        this.updatePosition(xPosition, yPosition + Math.random() * 5);
    }
        
    @Override
    public String getGameObjectName()
    {
        return "Grass_" + counter;
    }

    @Override
    public String getImageName()
    {
        int grassType = (int)((Math.random() * 100 ) % 7);
        if (grassType < 3)
        {
            return "grass_1.png";
        }
        else if (grassType < 5)
        {
            return "grass_2.png";
        }
        else
        {
            return "grass_3.png";
        }
    }
}
