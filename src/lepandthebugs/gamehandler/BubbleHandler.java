
package lepandthebugs.gamehandler;

import lepandthebugs.controller.GameStateManager;
import lepandthebugs.model.DoubleXY;
import lepandthebugs.physics.GameConstant;
import lepandthebugs.physics.GameUpdateManager;
import lepandthebugs.physics.PhysicsEngine;
import lepandthebugs.scene.gameunit.graphic.BubbleUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Keshan De Silva
 */
public class BubbleHandler extends GameUnitHandler<BubbleUnit>
{
    private ArrayList<DoubleXY> projectilePath = new ArrayList<>();
    private int projectileStep = 0;
    
    public BubbleHandler(BubbleUnit bubbleUnit)
    {
       super(bubbleUnit);
    }

    public void setPojectilePath(ArrayList<DoubleXY> projectilePath)
    {
        projectileStep = 0;
        this.projectilePath = projectilePath;
    }
    
    @Override
    public void onGameStep()
    {
        if (projectilePath.size() > projectileStep + 1)
        {
            DoubleXY nextPoint = projectilePath.get(projectileStep++);
            getGameUnit().updatePosition(nextPoint.getX() - 11, nextPoint.getY() - 11);
        }
        
        if (this.getGameUnit().isVisible())
        {
            if (GameConstant.BUG_GROUND_LEVEL_1 + 15 - getGameUnit().getYPosition() < 5)
            {
                GameState gameState = GameState.getInstance();
                ArrayList<BugHandler> bugList = PhysicsEngine.getPositiveHitList(this, gameState);

                for (BugHandler bugHandler : bugList)
                {
                    bugHandler.setBugStatus(GameConstant.BugStatus.DIE);
                }

                this.getGameUnit().setVisible(false);
                GameState.getInstance().removeBubble(this);
                
                if ((GameState.getInstance().getAllBubbleHandlers().isEmpty()) &&
                        (!GameUpdateManager.getInstance().getBubbleGunOSDUnit().hasMoreBubbles()) &&
                        (GameUpdateManager.getInstance().getScoreOSDUnit().hasMoreBugs()))
                {
                    Timer levelFailsTimer = new Timer(100, new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            GameStateManager.getInstance().onGameOver();
                        }
                    });
                    levelFailsTimer.setRepeats(false);
                    levelFailsTimer.start();
                }
            } 
        }
    }
}