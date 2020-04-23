package lepandthebugs.scene.gameunit.osd;

import lepandthebugs.scene.gameunit.OSDUnit;
import lepandthebugs.utils.ImageUtils;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Keshan De Silva
 */
public class GameCompleteOSDUnit extends OSDUnit
{
    public GameCompleteOSDUnit(int xPosition, int yPosition)
    {
        updatePosition(xPosition, yPosition);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/game complete.png"));
        this.backImage = ImageUtils.generateBufferedImage(imageIcon);
    }

    @Override
    public String getGameObjectName()
    {
        return "Game Complete OSD" ;
    }

    @Override
    public void drawGameObject(Graphics graphics)
    {
        //bubble gun osd
        Graphics2D graphics2D = (Graphics2D)graphics;
        int rule = AlphaComposite.SRC_OVER;
        Composite comp = AlphaComposite.getInstance(rule , 0.8f);
        graphics2D.setComposite(comp);
        graphics2D.drawImage(backImage, null, (int)getXPosition(), (int)getYPosition());
    }
}
