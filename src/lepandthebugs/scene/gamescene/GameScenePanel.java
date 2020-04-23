package lepandthebugs.scene.gamescene;

import lepandthebugs.scene.gameunit.GameUnit;
import lepandthebugs.scene.layers.GameLayer;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Keshan De Silva
 */
public class GameScenePanel extends JPanel
{
    private final ArrayList<GameLayer> gameSceneLayerList;
    private String sceneName;
    
    public GameScenePanel(String sceneName)
    {
        this.gameSceneLayerList = new ArrayList<>();
        this.sceneName = sceneName;
    }
    
    public boolean addGameSceneLayer(GameLayer gameSceneLayer)
    {
        if (!gameSceneLayerList.contains(gameSceneLayer))
        {
            return gameSceneLayerList.add(gameSceneLayer);
        }
        return false;
    }    
    
    public GameLayer getGameLayer(String layerName)
    {
        for (GameLayer gameSceneLayer : gameSceneLayerList)
        {
            if (gameSceneLayer.getLayerName().equals(layerName))
            {
                return gameSceneLayer;
            }
        }
        
        return null;
    }
    
    public GameLayer getGameLayerByUnit(GameUnit gameUnit)
    {
        for (GameLayer gameSceneLayer : gameSceneLayerList)
        {
            if (gameSceneLayer.getGameObjectList().contains(gameUnit))
            {
                return gameSceneLayer;    
            }
        }
        
        return null;
    }

    public String getSceneName()
    {
        return sceneName;
    }
    
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponents(graphics);
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        for (GameLayer gameSceneLayer : gameSceneLayerList)
        {
            gameSceneLayer.drawGameLayer(graphics);
        }
    }  

    public void refresh()
    {
        repaint();
    }
}
