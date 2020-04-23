
package lepandthebugs.scene.animation;

import lepandthebugs.physics.GameConstant;
import lepandthebugs.scene.gamescene.GameScenePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Keshan De Silva
 */
public class FrameManager implements ActionListener
{
    private static FrameManager instance = null;
    
    private GameScenePanel gameScenePanel;
    private Timer gameFrameTimer;
    
    private FrameManager()
    {
        this.gameFrameTimer = new Timer(1000 / GameConstant.FPS, this);
    }
    
    public static FrameManager getInstance()
    {
        synchronized(FrameManager.class)
        {
            if(instance == null) 
            {
                instance = new FrameManager();
            }  
        }
        
        return instance;
    }

    public void setGameScenePanel(GameScenePanel gameScenePanel)
    {
        this.gameScenePanel = gameScenePanel;
    }
    
    public boolean startFrameManager()
    {
        if (gameScenePanel != null)
        {
            gameFrameTimer.start();
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (gameScenePanel != null)
        {
            gameScenePanel.refresh();
        }
    }  
}
