package lepandthebugs.physics;

import lepandthebugs.gamehandler.BubbleHandler;
import lepandthebugs.gamehandler.BugHandler;
import lepandthebugs.gamehandler.GameState;
import lepandthebugs.model.DoubleXY;
import lepandthebugs.model.GunConfigurations;
import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class PhysicsEngine
{
    private static final double G = 150;
    
    private PhysicsEngine(){}

    public static ArrayList<DoubleXY> generateProjectilePath(GunConfigurations gunConfigurations)
    {
        ArrayList<DoubleXY> projectilePath = new ArrayList<>();
        ArrayList<DoubleXY> velocityValues = new ArrayList<>();
        
        // Add initial velocity
        velocityValues.add (new DoubleXY(
            gunConfigurations.getVelocity() * Math.sin(Math.toRadians(gunConfigurations.getAngle())), 
            gunConfigurations.getVelocity() * Math.cos(Math.toRadians(gunConfigurations.getAngle()))));
        
        // Add initial position
        projectilePath.add(new DoubleXY(25, GameConstant.GAME_SCENE_HEIGHT - gunConfigurations.getHeight() + 16));
        
        double deltaT = 1.0 / GameConstant.FPS;
        int step = 0;
        double distanceY = 0;
        do
        {
            DoubleXY preVelocities = velocityValues.get(step);
            DoubleXY newVelocities = new DoubleXY(preVelocities.getX(),
                    preVelocities.getY() - (G * deltaT));
            double distanceX = projectilePath.get(step).getX() + newVelocities.getX() * deltaT;
            distanceY = projectilePath.get(step).getY() - (newVelocities.getY() * deltaT - (0.5 * G * deltaT * deltaT));
            
            velocityValues.add(newVelocities);
            projectilePath.add(new DoubleXY(distanceX, distanceY));
            step++;
            
        } while (distanceY < GameConstant.BUG_GROUND_LEVEL_2 + 20);
        
        return projectilePath;
    }
    
    public static ArrayList<BugHandler> getPositiveHitList(BubbleHandler bubbleHandler, GameState gameState)
    {
        ArrayList<BugHandler> bugList = new ArrayList<>();
        
        for (BugHandler bugHandler : gameState.getAllBugHandlers())
        {
            if (bugHandler.getBugStatus().equals(GameConstant.BugStatus.LIVE))
            {
                double bugXPosition = bugHandler.getGameUnit().getXPosition();
                double hitXPosition = bubbleHandler.getGameUnit().getXPosition();

                if ((hitXPosition > bugXPosition) && (hitXPosition < bugXPosition + GameConstant.BUG_WIDTH))
                {
                    bugList.add(bugHandler);
                }
            }
        }
        
        return bugList;
    }
}
