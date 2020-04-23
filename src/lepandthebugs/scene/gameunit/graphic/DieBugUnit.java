
package lepandthebugs.scene.gameunit.graphic;

import lepandthebugs.scene.gameunit.graphic.BugUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Keshan De Silva
 */
public class DieBugUnit extends BugUnit
{
    private static int counter = 0;
    private final int totalSteps = 10 + (int)(Math.random() * 137 ) % 10;
    private final int[] animationSequence = new int[totalSteps];
    
    public DieBugUnit(BugUnit bugUnit)
    {
        counter++;
        for (int i = 0; i < totalSteps; i+=3)
        {
            animationSequence[i] = 1;
        }
        updatePosition(bugUnit.getXPosition(), bugUnit.getYPosition());
        Timer autoHideTimer = new Timer(3000, new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                setVisible(false);
            }
        });
        autoHideTimer.setRepeats(false);
        autoHideTimer.start();
    }
    
    @Override
    public String getGameObjectName()
    {
        return "Die Bug_" + counter;
    }

    @Override
    public String getBaseImageName()
    {
        return "die_bug";
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
