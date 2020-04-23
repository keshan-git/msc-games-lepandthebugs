
package lepandthebugs.scene.gameunit.graphic;

/**
 *
 * @author Keshan De Silva
 */
public class BackgroundUnit extends BasicImageGameUnit
{
    private int backgroundLevel = 0;
    
    public BackgroundUnit(int backgroundLevel)
    {
        this.backgroundLevel = backgroundLevel / 3;
        updatePosition(0, 0);
        reloadImages();
    }

    @Override
    public String getGameObjectName()
    {
        return "Background";
    }

    @Override
    public String getImageName()
    {
        return "background_" + backgroundLevel + ".png";
    }

}
