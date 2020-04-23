
package lepandthebugs.gamehandler;

import lepandthebugs.controller.GameStateManager;
import lepandthebugs.controller.UserControlableGameUnit;
import lepandthebugs.model.DoubleXY;
import lepandthebugs.model.GunConfigurations;
import lepandthebugs.physics.GameConstant;
import lepandthebugs.physics.GameUpdateManager;
import lepandthebugs.physics.PhysicsEngine;
import lepandthebugs.scene.gameunit.graphic.BubbleGunUnit;
import lepandthebugs.scene.gameunit.graphic.BubbleUnit;
import lepandthebugs.scene.layers.GameLayer;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class BubbleGunHandler extends GameUnitHandler<BubbleGunUnit> implements UserControlableGameUnit
{
    public BubbleGunHandler(BubbleGunUnit bubbleGunUnit)
    {
        super(bubbleGunUnit);
    }

    @Override
    public void onGameStep()
    {

    }

    @Override
    public void onKeyPressed(int keyCode)
    {
        GameUpdateManager gameUpdateManager = GameUpdateManager.getInstance();
        
        switch (keyCode)
        {
            case KeyEvent.VK_UP :
                if (getGameUnit().getYPosition() > 65)
                {
                    getGameUnit().updateYPosition(getGameUnit().getYPosition() - 5);
                    gameUpdateManager.getBubbleGunOSDUnit().setGunHeight((int)getGameUnit().getYPosition());
                }
                break;
                
            case KeyEvent.VK_DOWN :
                if (getGameUnit().getYPosition() < GameConstant.GAME_SCENE_HEIGHT - 110)
                {
                    getGameUnit().updateYPosition(getGameUnit().getYPosition() + 5);
                    gameUpdateManager.getBubbleGunOSDUnit().setGunHeight((int)getGameUnit().getYPosition());
                }
                break;
                
            case KeyEvent.VK_LEFT :
                if (getGameUnit().getAngle() < 90)
                {
                    getGameUnit().updateAngle(getGameUnit().getAngle() + 3);
                    gameUpdateManager.getBubbleGunOSDUnit().setGunAngle((int)getGameUnit().getAngle());
                }
                break;
                
            case KeyEvent.VK_RIGHT :
                if (getGameUnit().getAngle() > 0)
                {
                    getGameUnit().updateAngle(getGameUnit().getAngle() - 3);
                    gameUpdateManager.getBubbleGunOSDUnit().setGunAngle((int)getGameUnit().getAngle());
                }
                break;
                
            case KeyEvent.VK_A :
                if (getGameUnit().getForce() < 400)
                {
                    getGameUnit().setForce(getGameUnit().getForce() + 10);
                    gameUpdateManager.getBubbleGunOSDUnit().setGunForce(getGameUnit().getForce());
                }
                break;
                
            case KeyEvent.VK_Z :
                if (getGameUnit().getForce() > 200)
                {
                    getGameUnit().setForce(getGameUnit().getForce() - 10);
                    gameUpdateManager.getBubbleGunOSDUnit().setGunForce(getGameUnit().getForce());
                }
                break;
                
            case KeyEvent.VK_SPACE :
                if (GameUpdateManager.getInstance().getBubbleGunOSDUnit().hasMoreBubbles())
                {
                    //Generate Projectile Path
                    ArrayList<DoubleXY> projectileData = PhysicsEngine.generateProjectilePath(this.getGunConfigurations());

                    // Generate Bubble
                    BubbleHandler bubbleHandler = new BubbleHandler(new BubbleUnit());
                    bubbleHandler.setPojectilePath(projectileData);
                    
                    GameLayer bubbleLayer = GameStateManager.getInstance().getCurrentGameScenePanel().getGameLayer("Bubble Layer");
                    if ( bubbleLayer != null )
                    {
                        bubbleLayer.addGameObject(bubbleHandler.getGameUnit());
                        GameUpdateManager.getInstance().addGameHandlerInterface(bubbleHandler);

                        GameState.getInstance().addBubble(bubbleHandler);
                        gameUpdateManager.getBubbleGunOSDUnit().addFiredBubble();
                    }
                    
                }
        }
    }

    @Override
    public void onKeyReleased(int keyCode)
    {
    
    } 
    
    public GunConfigurations getGunConfigurations()
    {
        return new GunConfigurations(GameConstant.GAME_SCENE_HEIGHT - getGameUnit().getYPosition()
                            ,90 - getGameUnit().getAngle(), getGameUnit().getForce());
    }
}
