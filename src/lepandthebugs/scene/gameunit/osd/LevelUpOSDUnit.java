package lepandthebugs.scene.gameunit.osd;

import lepandthebugs.model.GameLevelScore;
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
public class LevelUpOSDUnit extends OSDUnit
{
    private GameLevelScore gameLevelScore;

    public LevelUpOSDUnit(int xPosition, int yPosition)
    {
        updatePosition(xPosition, yPosition);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/level up.png"));
        this.backImage = ImageUtils.generateBufferedImage(imageIcon);
    }

    public void setGameLevelScore(GameLevelScore gameLevelScore)
    {
        this.gameLevelScore = gameLevelScore;
    }

    @Override
    public String getGameObjectName()
    {
        return "Level Up OSD" ;
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

        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
        graphics2D.setColor(Color.WHITE);
        
        String bugCountString = String.format("%2d", gameLevelScore.getDeadBugCount());
        graphics2D.drawString(bugCountString, (int)getXPosition() + 230, (int)getYPosition() + 385);
               
        String timeString = String.format("%02d : %02d", gameLevelScore.getPlayTime()/ 60, gameLevelScore.getPlayTime()% 60);
        graphics2D.drawString(timeString, (int)getXPosition() + 220, (int)getYPosition() + 435);
        
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 28)); 
        String scoreString = String.format("%6d", gameLevelScore.getScore());
        graphics2D.drawString(scoreString, (int)getXPosition() + 200, (int)getYPosition() + 325); 
        
        graphics2D.setFont(new Font("Tekton Pro", Font.BOLD, 85)); 
        String levelString = String.format("%02d", gameLevelScore.getLevel());
        graphics2D.drawString(levelString, (int)getXPosition() + 295, (int)getYPosition() + 97); 
    }


}
