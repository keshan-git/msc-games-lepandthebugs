package lepandthebugs.scene.gameunit.osd;

import lepandthebugs.controller.GameStateManager;
import lepandthebugs.model.GameLevelScore;
import lepandthebugs.physics.GameUpdateManager;
import lepandthebugs.scene.gameunit.OSDUnit;
import lepandthebugs.utils.ImageUtils;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Keshan De Silva
 */
public class ScoreOSDUnit extends OSDUnit
{
    private int levelId = 0;
    private int liveBugCount = 0;
    private int deadBugCount = 0;
    private int missedBugCount = 0;
    private int timeLeft = 0;
    private int totalTime = 0;
    private static int totalScore = 0;
    private int score = 0;

    public ScoreOSDUnit(int xPosition, int yPosition)
    {
        updatePosition(xPosition, yPosition);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/score osd.png"));
        this.backImage = ImageUtils.generateBufferedImage(imageIcon);
    }

    public static void resetTotalScore()
    {
        totalScore = 0;
    }

    public void setLiveBugCount(int liveBugCount)
    {
        this.liveBugCount = liveBugCount;
    }

    public void setDeadBugCount(int deadBugCount)
    {
        this.deadBugCount = deadBugCount;
    }

    public void addDeadBug()
    {
        this.deadBugCount++;
        this.liveBugCount--;
        
        if (liveBugCount == 0)
        {
            int playTime = totalTime - timeLeft;
            totalScore += score;
            
            final GameLevelScore gameLevelScore = new GameLevelScore(levelId,
                                          deadBugCount, missedBugCount, playTime, totalScore);
            Timer levelCompleteTimer = new Timer(3080, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    GameStateManager.getInstance().onLevelComplete(gameLevelScore);
                }
            });
            levelCompleteTimer.setRepeats(false);
            levelCompleteTimer.start();
        }
    }
    
    public void addMissedBug()
    {
        this.missedBugCount++;
        this.liveBugCount--;
        
        // check for game over condition
        if (liveBugCount == 0)
        {
            Timer levelFailsTimer = new Timer(90, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    GameStateManager.getInstance().onGameOver();
                }
            });
            levelFailsTimer.setRepeats(false);
            levelFailsTimer.start();
        }
    }
   
    public boolean hasMoreBugs()
    {
        return liveBugCount != 0;
    }
    
    public void countDownTime()
    {
        this.timeLeft--;
    }

    public int getTimeLeft()
    {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft)
    {
        this.timeLeft = timeLeft;
        this.totalTime = timeLeft;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void setLevelId(int levelId)
    {
        this.levelId = levelId;
    }
        
    public void updateScore()
    {
        int currentScore = 3000;
        currentScore += deadBugCount * 2000;
        currentScore += timeLeft * 100;
        
        int bubblesLeft = GameUpdateManager.getInstance().getBubbleGunOSDUnit().getAvailableBubbles();
        currentScore += bubblesLeft * 500;
        
        this.score = currentScore;
    }
    
    @Override
    public String getGameObjectName()
    {
        return "Score OSD" ;
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

        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 18)); 
        graphics2D.setColor(Color.WHITE);
        
        String bugCountString = String.format("%02d", liveBugCount);
        graphics2D.drawString(bugCountString, (int)getXPosition() + 102, (int)getYPosition() + 30);
        
        String deadBugCountString = String.format("%02d", deadBugCount);
        graphics2D.drawString(deadBugCountString, (int)getXPosition() + 245, (int)getYPosition() + 30);
        
        String timeString = String.format("%02d : %02d", timeLeft / 60, timeLeft % 60);
        graphics2D.drawString(timeString, (int)getXPosition() + 87, (int)getYPosition() + 62);
        
        updateScore();
        String scoreString = String.format("%06d", score);
        graphics2D.drawString(scoreString, (int)getXPosition() + 227, (int)getYPosition() + 62); 
    }
}
