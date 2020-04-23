
package lepandthebugs.gamehandler;

import lepandthebugs.controller.GameStateManager;
import lepandthebugs.physics.GameConstant;
import lepandthebugs.physics.GameConstant.BugDirection;
import lepandthebugs.physics.GameConstant.BugLine;
import lepandthebugs.physics.GameConstant.BugStatus;
import lepandthebugs.physics.GameUpdateManager;
import lepandthebugs.scene.gameunit.graphic.BugUnit;
import lepandthebugs.scene.gameunit.graphic.DieBugUnit;
import lepandthebugs.scene.layers.GameLayer;

/**
 *
 * @author Keshan De Silva
 */
public class BugHandler extends GameUnitHandler<BugUnit>
{
    private BugDirection bugDirection = BugDirection.LEFT;
    private BugStatus bugStatus = BugStatus.NOT_GEN;
    private BugLine bugLine = BugLine.LINE_1;
    private int initialPosition;
    
    public BugHandler(BugUnit bugUnit, BugDirection bugDirection, BugLine bugLine)
    {
        super(bugUnit);
        this.bugDirection = bugDirection;
        this.bugLine = bugLine;
        setBugStatus(BugStatus.NOT_GEN);
        if (bugDirection.equals(BugDirection.LEFT))
        {
            this.initialPosition = GameConstant.BUG_RIGHT_LIMIT;
        }
        else
        {
            this.initialPosition = GameConstant.BUG_LEFT_LIMIT;
            bugUnit.flip();
        }
    }
    
    public BugHandler(BugUnit bugUnit, int position, BugLine bugLine)
    {
        super(bugUnit);
        this.bugDirection = BugDirection.NONE;
        this.initialPosition = position;
        this.bugLine = bugLine;
        setBugStatus(BugStatus.NOT_GEN);
    }
 
    public BugStatus getBugStatus()
    {
        return bugStatus;
    }
    
    public final void setBugStatus(BugStatus bugStatus)
    {
        this.bugStatus = bugStatus;
        switch (bugStatus)
        {
            case LIVE : 
            {
                getGameUnit().setVisible(true);
                if (bugLine.equals(BugLine.LINE_1))
                {
                    getGameUnit().updatePosition(initialPosition, GameConstant.BUG_GROUND_LEVEL_1);
                }
                else
                {
                    getGameUnit().updatePosition(initialPosition, GameConstant.BUG_GROUND_LEVEL_2);
                }
                break;
            }
                
            case NOT_GEN :
            {
                getGameUnit().setVisible(false);
                break;
            }
            
            case DIE:
            {
                getGameUnit().setVisible(false);
                GameUpdateManager.getInstance().getScoreOSDUnit().addDeadBug();
                GameLayer gameLayer = GameStateManager.getInstance().getCurrentGameScenePanel().getGameLayerByUnit(getGameUnit());
                gameLayer.addGameObject(new DieBugUnit(getGameUnit()));
                break;
            }
            
            case MISSED:
            {
                GameUpdateManager.getInstance().getScoreOSDUnit().addMissedBug();
                break;
            }
        }
    }
    
    private void moveBug()
    {
        if (bugStatus.equals(BugStatus.LIVE))
        {
            if (bugDirection.equals(BugDirection.LEFT))
            {
                if (getGameUnit().getXPosition() > GameConstant.BUG_LEFT_LIMIT)
                {
                    getGameUnit().updateXPosition(getGameUnit().getXPosition() - 1);
                }
                else
                {
                    setBugStatus(BugStatus.MISSED);
                }
            }
            else
            {
                if (getGameUnit().getXPosition() < GameConstant.BUG_RIGHT_LIMIT)
                {
                    getGameUnit().updateXPosition(getGameUnit().getXPosition() + 1);
                }
                else
                {
                    setBugStatus(BugStatus.MISSED);
                }
            }
        }
    }
    
    @Override
    public void onGameStep()
    {
        if (!bugDirection.equals(BugDirection.NONE))
        {
            moveBug();
        }
    }
}
