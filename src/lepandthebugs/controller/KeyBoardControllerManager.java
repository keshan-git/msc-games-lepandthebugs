/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs.controller;

import lepandthebugs.BubbleGunMainFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class KeyBoardControllerManager
{
    private static KeyBoardControllerManager instance;
    private final ArrayList<UserControlableGameUnit> userControlableGameUnitList;
    
    private KeyBoardControllerManager()
    {
        this.userControlableGameUnitList = new ArrayList<>();
    }
    
    public static KeyBoardControllerManager getInstance()
    {
        if (instance == null)
        {
            instance = new KeyBoardControllerManager();
        }
        
        return instance;
    }
    
    public void setGameSceneFrame(BubbleGunMainFrame bubbleGunMainFrame)
    {
        bubbleGunMainFrame.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                for (UserControlableGameUnit userControlableGameUnit : userControlableGameUnitList)
                {
                    userControlableGameUnit.onKeyPressed(evt.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent evt)
            {
                for (UserControlableGameUnit userControlableGameUnit : userControlableGameUnitList)
                {
                    userControlableGameUnit.onKeyReleased(evt.getKeyCode());
                }
            } 
        });
    }
    
    public void addUserControlableGameUnit(UserControlableGameUnit userControlableGameUnit)
    {
        userControlableGameUnitList.clear();
        userControlableGameUnitList.add(userControlableGameUnit);
    }

    public void reset()
    {
        userControlableGameUnitList.clear();
    }
    
    
}
