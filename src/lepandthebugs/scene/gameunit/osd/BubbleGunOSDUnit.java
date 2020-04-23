package lepandthebugs.scene.gameunit.osd;

import lepandthebugs.scene.gameunit.OSDUnit;
import lepandthebugs.utils.ImageUtils;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Keshan De Silva
 */
public class BubbleGunOSDUnit extends OSDUnit
{
    private int gunHeight = 65;
    private int gunAngle;
    private int gunForce = 300;
    private int totalBubbleCount = 12;
    private int firedBubbleCount = 0;

    private final BufferedImage bubbleImage;
    private final BufferedImage bubbleOffImage;
    
    public BubbleGunOSDUnit(int xPosition, int yPosition)
    {
        updatePosition(xPosition, yPosition);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/bubble gun osd.png"));
        this.backImage = ImageUtils.generateBufferedImage(imageIcon);
        
        ImageIcon bubbleImageIcon = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/bubble.png"));
        this.bubbleImage = ImageUtils.generateBufferedImage(bubbleImageIcon);
        
        ImageIcon bubbleOffImageIcon = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/bubble_off.png"));
        this.bubbleOffImage = ImageUtils.generateBufferedImage(bubbleOffImageIcon);
    }

    public void setGunAngle(int gunAngle)
    {
        this.gunAngle = gunAngle;
    }
    
    public void setGunForce(int gunForce)
    {
        this.gunForce = gunForce;
    }

    public void setTotalBubbleCount(int totalBubbleCount)
    {
        this.totalBubbleCount = totalBubbleCount;
    }

    public void addFiredBubble()
    {
        this.firedBubbleCount++;
    }

    public void setGunHeight(int gunHeight)
    {
        this.gunHeight = gunHeight;
    }

    public boolean hasMoreBubbles()
    {
        return totalBubbleCount != firedBubbleCount;
    }
     
    public int getAvailableBubbles()
    {
        return totalBubbleCount - firedBubbleCount;
    }
        
    @Override
    public String getGameObjectName()
    {
        return "Bubble Gun OSD" ;
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
        
        for (int i = 0; i < totalBubbleCount; i++)
        {
            if (i < totalBubbleCount - firedBubbleCount)
            {
                graphics2D.drawImage(bubbleImage, null, (int)getXPosition() + i * 30 + 70,
                    (int)getYPosition() + 44);
            }
            else
            {
                graphics2D.drawImage(bubbleOffImage, null, (int)getXPosition() + i * 30 + 70,
                    (int)getYPosition() + 44);
            }
        }
        
        graphics2D.setColor(Color.GREEN);
        int forceWidth = (int)(((gunForce - 200) / 200.0) * 82);
        graphics.fillRoundRect((int)getXPosition() + 70, (int)getYPosition() + 10, forceWidth, 27, 30 , 30);
        
        int angleWidth = (int)(((gunAngle) / 90.0) * 82);
        graphics.fillRoundRect((int)getXPosition() + 210, (int)getYPosition() + 10, angleWidth, 27, 30 , 30);
        
        int heightWidth = (int)(((gunHeight - 65) / 275.0) * 82);
        graphics.fillRoundRect((int)getXPosition() + 350, (int)getYPosition() + 11, heightWidth, 27, 30 , 30);
        
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 18)); 
        graphics2D.setColor(Color.RED);
        
        String gunForceString = String.format("%3d %s", Math.round(((gunForce - 200) / 200.0) * 100), " %");
        graphics2D.drawString(gunForceString, (int)getXPosition() + 90, (int)getYPosition() + 30);
        
        String gunAngleString = gunAngle + " °";
        graphics2D.drawString(gunAngleString, (int)getXPosition() + 240, (int)getYPosition() + 30);
        
        String gunHeightString = String.format("%3d %s", Math.round(((gunHeight - 65) / 275.0) * 100), " %");
        graphics2D.drawString(gunHeightString, (int)getXPosition() + 365, (int)getYPosition() + 30); 
    }
  
}
