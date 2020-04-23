
package lepandthebugs.scene.gameunit.graphic;

/**
 *
 * @author Keshan De Silva
 */
public class WallUnit extends BasicImageGameUnit
{
    public WallUnit()
    {
        updatePosition(0, 65);
    }

    @Override
    public String getGameObjectName()
    {
        return "Wall";
    }

    @Override
    public String getImageName()
    {
        return "brick.png";
    }

}
