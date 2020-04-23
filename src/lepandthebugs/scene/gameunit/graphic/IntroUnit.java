
package lepandthebugs.scene.gameunit.graphic;

/**
 *
 * @author Keshan De Silva
 */
public class IntroUnit extends BasicAnimationGameUnit
{
    private final int totalSteps = 5 * 10 * 6;
    private final int[] animationSequence = new int[totalSteps];
    
    public IntroUnit()
    {
        for (int i = 0; i < totalSteps; i++)
        {
            animationSequence[i] = i / 50;
        }
    }
    
    @Override
    public String getGameObjectName()
    {
        return "Intro";
    }

    @Override
    public String getBaseImageName()
    {
        return "intro";
    }
    
    @Override
    public int getImageCount()
    {
        return 6;
    }
    
    @Override
    public int getTotalSteps()
    {
        return totalSteps;
    }
    
    @Override
    public int[] getAnimationSequence()
    {
        return animationSequence;
    }
}
