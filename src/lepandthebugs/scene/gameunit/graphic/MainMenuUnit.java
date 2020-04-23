
package lepandthebugs.scene.gameunit.graphic;

/**
 *
 * @author Keshan De Silva
 */
public class MainMenuUnit extends BackgroundUnit
{
    public MainMenuUnit()
    {
        super(0);
        updatePosition(0, 0);
    }

    @Override
    public String getGameObjectName()
    {
        return "Main Menu";
    }

    @Override
    public String getImageName()
    {
        return "main menu.png";
    }

}
