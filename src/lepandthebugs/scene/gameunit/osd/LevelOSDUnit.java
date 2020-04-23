package lepandthebugs.scene.gameunit.osd;

import lepandthebugs.physics.GameConstant;
import lepandthebugs.scene.gameunit.OSDUnit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Keshan De Silva
 */
public class LevelOSDUnit extends OSDUnit
{
    private int levelId;

    public LevelOSDUnit(int levelId)
    {
        this.levelId = levelId;
    }

    @Override
    public String getGameObjectName()
    {
        return "Level OSD" ;
    }

    @Override
    public void drawGameObject(Graphics graphics)
    {
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.WHITE);
        
        graphics2D.drawString(String.format("%s %2d", "Level" , levelId),
                GameConstant.GAME_SCENE_WIDTH - 100, GameConstant.BUG_GROUND_LEVEL_2 + 70);
    }
}
