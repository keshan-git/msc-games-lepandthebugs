
package lepandthebugs.scene.layers;

import lepandthebugs.scene.gameunit.GameUnit;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Keshan De Silva
 */
public abstract class GameLayer<T extends GameUnit>
{
    private String layerName;
    private ArrayList<T> gameObjectList;
    private boolean visible;
    
    public GameLayer()
    {
        gameObjectList = new ArrayList<>();
        this.layerName = getLayerName();
        this.visible = true;
    }
    
    public abstract String getLayerName();

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public boolean addGameObject(T gameObject)
    {
        if (!gameObjectList.contains(gameObject))
        {
            return gameObjectList.add(gameObject);
        }
        return false;
    }
    
    public ArrayList<T> getGameObjectList()
    {
        return gameObjectList;
    }
    
    public void drawGameLayer(Graphics graphics)
    {
        if (visible)
        {
            for (int i = 0; i < gameObjectList.size(); i++)
            {
                if (gameObjectList.get(i).isVisible())
                {
                    gameObjectList.get(i).drawGameObject(graphics);
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if ((obj == null) || (getClass() != obj.getClass()))
        {
            return false;
        }
        
        final GameLayer other = (GameLayer) obj;
        return Objects.equals(this.layerName, other.layerName);
    }
  
}
