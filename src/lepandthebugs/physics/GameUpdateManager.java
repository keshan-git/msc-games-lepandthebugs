/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs.physics;

import lepandthebugs.controller.GameStateManager;
import lepandthebugs.gamehandler.GameHandlerInterface;
import lepandthebugs.scene.gameunit.osd.BubbleGunOSDUnit;
import lepandthebugs.scene.gameunit.osd.ScoreOSDUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Keshan De Silva
 */
public class GameUpdateManager implements ActionListener
{
    private static GameUpdateManager instance = null;
    private final Timer gameUpdateTimer;
    private final Timer gameCountDownTimer;
    private final ArrayList<GameHandlerInterface> gameHandlerInterfaces;
    
    private BubbleGunOSDUnit bubbleGunOSDUnit;
    private ScoreOSDUnit scoreOSDUnit;
    
    private GameUpdateManager()
    {
        this.gameUpdateTimer = new Timer(1000 / GameConstant.FPS, this);
        this.gameCountDownTimer = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
               getScoreOSDUnit().countDownTime();
               if (getScoreOSDUnit().getTimeLeft() == 0)
               {
                   gameCountDownTimer.stop();
                   GameStateManager.getInstance().onGameOver();
               }
            }
        });
        
        this.gameHandlerInterfaces = new ArrayList<>();
    }
    
    public static GameUpdateManager getInstance()
    {
        synchronized(GameUpdateManager.class)
        {
            if(instance == null) 
            {
                instance = new GameUpdateManager();
            }  
        }
        
        return instance;
    }
    
    public void addGameHandlerInterface(GameHandlerInterface gameHandlerInterface)
    {
        gameHandlerInterfaces.add(gameHandlerInterface);
    }

    public void setBubbleGunOSDUnit(BubbleGunOSDUnit bubbleGunOSDUnit)
    {
        this.bubbleGunOSDUnit = bubbleGunOSDUnit;
    }

    public BubbleGunOSDUnit getBubbleGunOSDUnit()
    {
        return bubbleGunOSDUnit;
    }

    public ScoreOSDUnit getScoreOSDUnit()
    {
        return scoreOSDUnit;
    }

    public void setScoreOSDUnit(ScoreOSDUnit scoreOSDUnit)
    {
        this.scoreOSDUnit = scoreOSDUnit;
    }

    public void startGameUpdateManager()
    {
        gameUpdateTimer.start();
    }
    
    public void startCountDown()
    {
        gameCountDownTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (GameHandlerInterface gameHandlerInterface : gameHandlerInterfaces)
        {
            gameHandlerInterface.onGameStep();
        }
    }     

    public void reset()
    {
        gameUpdateTimer.stop();
        gameCountDownTimer.stop();
        gameHandlerInterfaces.clear();
        bubbleGunOSDUnit = null;
        scoreOSDUnit = null;
    }
}
