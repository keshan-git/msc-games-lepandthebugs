package lepandthebugs.scene.gameunit.graphic;

/**
 *
 * @author Keshan De Silva
 */
public class BubbleUnit extends BasicImageGameUnit
{
    private static int counter = 0;
    
    public BubbleUnit()
    {
        counter++;
    }
        
    @Override
    public String getGameObjectName()
    {
        return "Bubble_" + counter;
    }

    @Override
    public String getImageName()
    {
        return "bubble.png";
    }

}
