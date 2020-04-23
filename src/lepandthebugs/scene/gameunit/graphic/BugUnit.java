
package lepandthebugs.scene.gameunit.graphic;

import lepandthebugs.scene.gameunit.graphic.BasicAnimationGameUnit;

/**
 *
 * @author Keshan De Silva
 */
public class BugUnit extends BasicAnimationGameUnit
{
    private static int counter = 0;
    private final int totalSteps = 10 + (int)(Math.random() * 137 ) % 10;
    private final int[] animationSequence = new int[totalSteps];
    
    public BugUnit()
    {
        counter++;
        animationSequence[(int)(totalSteps * 2 / 30)] = 1;
    }
    
    @Override
    public String getGameObjectName()
    {
        return "Bug_" + counter;
    }

    @Override
    public String getBaseImageName()
    {
        return "bug";
    }
    
    @Override
    public int getImageCount()
    {
        return 2;
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
