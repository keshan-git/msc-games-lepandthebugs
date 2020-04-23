/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs;

import lepandthebugs.controller.GameStateListener;
import lepandthebugs.controller.GameStateManager;
import lepandthebugs.controller.KeyBoardControllerManager;
import lepandthebugs.controller.UserControlableGameUnit;
import lepandthebugs.model.GameLevelScore;
import lepandthebugs.physics.GameConstant;
import lepandthebugs.physics.GameUpdateManager;
import lepandthebugs.scene.animation.FrameManager;
import lepandthebugs.scene.gamescene.GameScenePanel;
import lepandthebugs.scene.gameunit.osd.LevelUpOSDUnit;
import lepandthebugs.scene.layers.GameLayer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Keshan De Silva
 */
public class BubbleGunMainFrame extends javax.swing.JFrame
{    
    private GameScenePanel gameScenePanel;
    public BubbleGunMainFrame()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
                | UnsupportedLookAndFeelException ex){}
        
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/icon.png"));
        setIconImage(img.getImage());
        setResizable(false);
        setSize(925, 520);
        initIntro();
    }
    
    private void initIntro()
    {
        GameStateManager.getInstance().initialize();
        KeyBoardControllerManager.getInstance().setGameSceneFrame(this);
        
        gameScenePanel = GameStateManager.getInstance().getIntroPanel();
        FrameManager.getInstance().setGameScenePanel(gameScenePanel);
        add("Screen", gameScenePanel);
        revalidate();
        
        FrameManager.getInstance().setGameScenePanel(gameScenePanel);
        FrameManager.getInstance().startFrameManager();
        
        final Timer mainMenuTimer = new Timer(30000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                initMainMenu();
            }
        });
        
        mainMenuTimer.setRepeats(false);
        mainMenuTimer.start();
        
        KeyBoardControllerManager.getInstance().addUserControlableGameUnit(new UserControlableGameUnit()
        {
            @Override
            public void onKeyPressed(int keyCode)
            {
                if (keyCode == KeyEvent.VK_ESCAPE)
                {
                    Timer mainMenuEscTimer = new Timer(100, new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            mainMenuTimer.stop();
                            initMainMenu();
                        }
                    });
                    
                    mainMenuEscTimer.setRepeats(false);
                    mainMenuEscTimer.start();
                }                
            }

            @Override
            public void onKeyReleased(int keyCode) { }
        });
    }
    
    private void initMainMenu()
    {
        GameUpdateManager.getInstance().reset();
        KeyBoardControllerManager.getInstance().reset();
        
        // Display main menu 
        remove(gameScenePanel);
        gameScenePanel = GameStateManager.getInstance().getMainMenuPanel();
        FrameManager.getInstance().setGameScenePanel(gameScenePanel);
        add("Screen", gameScenePanel);
        revalidate();
        
        KeyBoardControllerManager.getInstance().addUserControlableGameUnit(new UserControlableGameUnit()
        {
            @Override
            public void onKeyPressed(int keyCode)
            {
                if (keyCode == KeyEvent.VK_ENTER)
                {
                    Timer startGameTimer = new Timer(100, new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            startGame();
                        }
                    });
                    
                    startGameTimer.setRepeats(false);
                    startGameTimer.start();
                }                
            }

            @Override
            public void onKeyReleased(int keyCode) { }
        });    
    }
        
    private void startGame()
    {
        GameStateManager.getInstance().initializeGame();

        remove(gameScenePanel);
        gameScenePanel = GameStateManager.getInstance().getCurrentGameScenePanel();
        add("Screen", gameScenePanel);
        revalidate();

        FrameManager.getInstance().setGameScenePanel(gameScenePanel);
        FrameManager.getInstance().startFrameManager();
        
        // Register State Listeners
        GameStateManager.getInstance().addGameStateListener(new GameStateListener()
        {
            @Override
            public void onGameStateUpdate(GameConstant.GameSceneState currentGameState,
                    GameLevelScore gameLevelScore)
            {
                if (currentGameState.equals(GameConstant.GameSceneState.LEVEL_PASS))
                {                    
                    remove(gameScenePanel);
                    gameScenePanel = GameStateManager.getInstance().getLevelUpPanel();
                    GameLayer osdLayer = gameScenePanel.getGameLayer("OSD Layer");
                    LevelUpOSDUnit levelUpOSDUnit = (LevelUpOSDUnit)osdLayer.getGameObjectList().get(0);
                    levelUpOSDUnit.setGameLevelScore(gameLevelScore);
                    FrameManager.getInstance().setGameScenePanel(gameScenePanel);
                    add("Screen", gameScenePanel);
                    revalidate();
                    
                    KeyBoardControllerManager.getInstance().addUserControlableGameUnit(new UserControlableGameUnit()
                    {
                        @Override
                        public void onKeyPressed(int keyCode)
                        {
                            if (keyCode == KeyEvent.VK_ENTER)
                            {
                                Timer moveToNextLevel = new Timer(500, new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent ae)
                                    {
                                        if (!GameStateManager.getInstance().isGameCompleted())
                                        {
                                            remove(gameScenePanel);
                                            GameStateManager.getInstance().loadNextGameLevel();
                                            gameScenePanel = GameStateManager.getInstance().getCurrentGameScenePanel();
                                            FrameManager.getInstance().setGameScenePanel(gameScenePanel);
                                            add("Screen", gameScenePanel);
                                            revalidate();
                                        }
                                        else
                                        {
                                            GameStateManager.getInstance().onGameComplete();
                                        }
                                    }
                                });
                                moveToNextLevel.setRepeats(false);
                                moveToNextLevel.start();    
                            }
                        }

                        @Override
                        public void onKeyReleased(int keyCode) { }
                    });
                }
                else if (currentGameState.equals(GameConstant.GameSceneState.GAME_OVER))
                {                    
                    remove(gameScenePanel);
                    gameScenePanel = GameStateManager.getInstance().getGameOverPanel();
                    FrameManager.getInstance().setGameScenePanel(gameScenePanel);
                    add("Screen", gameScenePanel);
                    revalidate();
                    
                    KeyBoardControllerManager.getInstance().addUserControlableGameUnit(new UserControlableGameUnit()
                    {
                        @Override
                        public void onKeyPressed(int keyCode)
                        {
                            if (keyCode == KeyEvent.VK_ENTER)
                            {
                                Timer moveToMainMenu = new Timer(500, new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent ae)
                                    {
                                        //Move to main menu
                                        initMainMenu();
                                    }
                                });
                                moveToMainMenu.setRepeats(false);
                                moveToMainMenu.start();    
                            }
                        }

                        @Override
                        public void onKeyReleased(int keyCode) { }
                    });
                }
                else if (currentGameState.equals(GameConstant.GameSceneState.GAME_COMPLETE))
                {                    
                    remove(gameScenePanel);
                    gameScenePanel = GameStateManager.getInstance().getGameCompletePanel();
                    FrameManager.getInstance().setGameScenePanel(gameScenePanel);
                    add("Screen", gameScenePanel);
                    revalidate();
                    
                    KeyBoardControllerManager.getInstance().addUserControlableGameUnit(new UserControlableGameUnit()
                    {
                        @Override
                        public void onKeyPressed(int keyCode)
                        {
                            if (keyCode == KeyEvent.VK_ENTER)
                            {
                                Timer moveToMainMenu = new Timer(500, new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent ae)
                                    {
                                        //Move to main menu
                                        initMainMenu();
                                    }
                                });
                                moveToMainMenu.setRepeats(false);
                                moveToMainMenu.start();    
                            }
                        }

                        @Override
                        public void onKeyReleased(int keyCode) { }
                    });
                }
            }
        });
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LEP and the Bugs");
        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new BubbleGunMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
