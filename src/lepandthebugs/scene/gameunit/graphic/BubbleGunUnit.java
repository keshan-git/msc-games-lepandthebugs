package lepandthebugs.scene.gameunit.graphic;

/**
 *
 * @author Keshan De Silva
 */
public class BubbleGunUnit extends BasicImageGameUnit
{
    private int force;
    public BubbleGunUnit()
    {
        updatePosition(0, 65);
        setForce(300);
    }

    public int getForce()
    {
        return force;
    }

    public final void setForce(int force)
    {
        this.force = force;
    }

    @Override
    public String getGameObjectName()
    {
        return "Bubble Gun";
    }

    @Override
    public String getImageName()
    {
        return "bubble_gun.png";
    }

}
