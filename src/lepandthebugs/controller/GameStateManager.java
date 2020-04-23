package lepandthebugs.controller;

import lepandthebugs.gamehandler.BubbleGunHandler;
import lepandthebugs.gamehandler.BugHandler;
import lepandthebugs.gamehandler.GameState;
import lepandthebugs.model.BugModel;
import lepandthebugs.model.GameLevel;
import lepandthebugs.model.GameLevelScore;
import lepandthebugs.model.GrassModel;
import lepandthebugs.physics.GameConstant;
import lepandthebugs.physics.GameConstant.GameSceneState;
import lepandthebugs.physics.GameUpdateManager;
import lepandthebugs.scene.gamescene.GameScenePanel;
import lepandthebugs.scene.gameunit.OSDUnit;
import lepandthebugs.scene.gameunit.baisc.ProjectilePathUnit;
import lepandthebugs.scene.gameunit.graphic.BackgroundUnit;
import lepandthebugs.scene.gameunit.graphic.BubbleGunUnit;
import lepandthebugs.scene.gameunit.graphic.BubbleUnit;
import lepandthebugs.scene.gameunit.graphic.BugUnit;
import lepandthebugs.scene.gameunit.graphic.GrassLineUnit;
import lepandthebugs.scene.gameunit.graphic.IntroUnit;
import lepandthebugs.scene.gameunit.graphic.MainMenuUnit;
import lepandthebugs.scene.gameunit.graphic.WallUnit;
import lepandthebugs.scene.gameunit.osd.BubbleGunOSDUnit;
import lepandthebugs.scene.gameunit.osd.GameCompleteOSDUnit;
import lepandthebugs.scene.gameunit.osd.GameOverOSDUnit;
import lepandthebugs.scene.gameunit.osd.LevelOSDUnit;
import lepandthebugs.scene.gameunit.osd.LevelUpOSDUnit;
import lepandthebugs.scene.gameunit.osd.ScoreOSDUnit;
import lepandthebugs.scene.layers.BackgroundlLayer;
import lepandthebugs.scene.layers.BubbleGunLayer;
import lepandthebugs.scene.layers.BubbleLayer;
import lepandthebugs.scene.layers.BugLayer;
import lepandthebugs.scene.layers.GameLayer;
import lepandthebugs.scene.layers.GrassLineLayer;
import lepandthebugs.scene.layers.GuideLayer;
import lepandthebugs.scene.layers.IntrolLayer;
import lepandthebugs.scene.layers.OSDLayer;
import lepandthebugs.scene.layers.WallLayer;
import lepandthebugs.utils.ResourceReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Timer;

/**
 *
 * @author Keshan De Silva
 */
public class GameStateManager
{
    private static GameStateManager instance;
    private final HashMap<String, GameScenePanel> gameScenePanelMap;
    private ArrayList<GameStateListener> gameStateListeners;
    private GameScenePanel currentGameScenePanel;
    private GameSceneState gameSceneState;
    private ArrayList<GameLevel> gameLevels;
    private int gameLevelId;
    
    private GameStateManager()
    {
        gameScenePanelMap = new HashMap<>();
        gameStateListeners = new ArrayList<>();
    }
    
    public static GameStateManager getInstance()
    {
        synchronized(GameStateManager.class)
        {
            if (instance == null)
            {
                instance = new GameStateManager();
            }
        }
        
        return instance;
    }
    
    public void initialize()
    {
        // Intro
        GameScenePanel introScenePanel = new GameScenePanel("Intro");
        IntrolLayer introlLayer = new IntrolLayer();
        introlLayer.addGameObject(new IntroUnit());
        introScenePanel.addGameSceneLayer(introlLayer);
                
        // Main Menu
        GameScenePanel mainMenuScenePanel = new GameScenePanel("Main Menu");
        BackgroundlLayer backgroundlLayer = new BackgroundlLayer();
        backgroundlLayer.addGameObject(new MainMenuUnit());
        mainMenuScenePanel.addGameSceneLayer(backgroundlLayer);
        
        gameScenePanelMap.put(introScenePanel.getSceneName(), introScenePanel); 
        gameScenePanelMap.put(mainMenuScenePanel.getSceneName(), mainMenuScenePanel); 
        
        gameSceneState = GameSceneState.MAIN_MENU;
    }
    
    public void initializeGame()
    {    
        // Define Game Scene
        gameLevels = ResourceReader.getAllGameLevels();
        ScoreOSDUnit.resetTotalScore();
        GameScenePanel gameScenePanel = generateGamePlayScenePanel(gameLevels.get(0));

        // Level Up
        GameScenePanel levelUpPanel = new GameScenePanel("Level Up");
        levelUpPanel.addGameSceneLayer(gameScenePanel.getGameLayer("Background Layer"));
        levelUpPanel.addGameSceneLayer(gameScenePanel.getGameLayer("Wall Layer"));
        GameLayer<OSDUnit> osdLayer = new OSDLayer();
        osdLayer.addGameObject(new LevelUpOSDUnit(250, 10));
        levelUpPanel.addGameSceneLayer(osdLayer);
        
        // Game Over
        GameScenePanel gameOverPanel = new GameScenePanel("Game Over");
        gameOverPanel.addGameSceneLayer(gameScenePanel.getGameLayer("Background Layer"));
        gameOverPanel.addGameSceneLayer(gameScenePanel.getGameLayer("Wall Layer"));
        GameLayer<OSDUnit> osdOverLayer = new OSDLayer();
        osdOverLayer.addGameObject(new GameOverOSDUnit(250, 10));
        gameOverPanel.addGameSceneLayer(osdOverLayer);
        
        // Game Complete
        GameScenePanel gameCompletePanel = new GameScenePanel("Game Complete");
        gameCompletePanel.addGameSceneLayer(gameScenePanel.getGameLayer("Background Layer"));
        gameCompletePanel.addGameSceneLayer(gameScenePanel.getGameLayer("Wall Layer"));
        GameLayer<OSDUnit> osdCompleteLayer = new OSDLayer();
        osdCompleteLayer.addGameObject(new GameCompleteOSDUnit(250, 10));
        gameCompletePanel.addGameSceneLayer(osdCompleteLayer);
        
        gameScenePanelMap.put(gameScenePanel.getSceneName(), gameScenePanel);
        gameScenePanelMap.put(levelUpPanel.getSceneName(), levelUpPanel); 
        gameScenePanelMap.put(gameOverPanel.getSceneName(), gameOverPanel); 
        gameScenePanelMap.put(gameCompletePanel.getSceneName(), gameCompletePanel); 

        gameSceneState = GameSceneState.PLAY;
        gameLevelId = 0;
        this.currentGameScenePanel = gameScenePanel;
    }
    
    public void loadNextGameLevel()
    {
        gameLevelId++;
        GameScenePanel gameScenePanel = generateGamePlayScenePanel(gameLevels.get(gameLevelId));
        gameScenePanelMap.put(gameScenePanel.getSceneName(), gameScenePanel);
        this.currentGameScenePanel = gameScenePanel;
        gameSceneState = GameSceneState.PLAY;
    }
    
    private GameScenePanel generateGamePlayScenePanel(GameLevel gameLevel)
    {
        GameUpdateManager.getInstance().reset();
        KeyBoardControllerManager.getInstance().reset();
        
        GameState gameState = GameState.getInstance();
        
        // Play scene panel
        GameScenePanel gameScenePanel = new GameScenePanel("Game Scene");
        
        // Bubble Layer
        GameLayer<BubbleUnit> bubbleLayer = new BubbleLayer();        
        
        // Bubble Gun Layer
        BubbleGunHandler bubbleGunHandler = new BubbleGunHandler(new BubbleGunUnit());
        GameLayer bubbleGunLayer = new BubbleGunLayer();
        bubbleGunLayer.addGameObject(bubbleGunHandler.getGameUnit());
        
        // Bug Layers
        GameLayer<BugUnit> bugLayerLine1 = new BugLayer();
        GameLayer<BugUnit> bugLayerLine2 = new BugLayer();
        
        for (BugModel bugModel : gameLevel.getBugModelList())
        {
            final BugHandler bugHandler;
            if (!bugModel.getBugDirection().equals(GameConstant.BugDirection.NONE))
            {
                bugHandler = new BugHandler(new BugUnit(), bugModel.getBugDirection(), bugModel.getBugLineId());
            }
            else
            {
                int location = (int)(Math.random() * 600) + 200;
                bugHandler = new BugHandler(new BugUnit(), location, bugModel.getBugLineId());
            }
            
            if (bugModel.getBugLineId().equals(GameConstant.BugLine.LINE_1))
            {
                bugLayerLine1.addGameObject(bugHandler.getGameUnit());
            }
            else
            {
                bugLayerLine2.addGameObject(bugHandler.getGameUnit());
            }
            
            Timer liveTimer = new Timer(bugModel.getStartTime(), new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    bugHandler.setBugStatus(GameConstant.BugStatus.LIVE);
                }
            });
            liveTimer.setRepeats(false);
            liveTimer.start();
            
            GameUpdateManager.getInstance().addGameHandlerInterface(bugHandler);
            
            gameState.addBug(bugHandler);
        }

        // Grass Layer
        GameLayer<GrassLineUnit> grassLineLayer1 = new GrassLineLayer();
        GameLayer<GrassLineUnit> grassLineLayer2 = new GrassLineLayer();
        GameLayer<GrassLineUnit> grassLineLayer3 = new GrassLineLayer();
        
        for (GrassModel grassModel : gameLevel.getGrassModelList())
        {
            if (grassModel.getLevel() == GameConstant.GRASS_GROUND_LEVEL_1)
            {
                grassLineLayer2.addGameObject(new GrassLineUnit(grassModel.getBlockSize(), 
                        grassModel.getxPosition(), GameConstant.GRASS_GROUND_LEVEL_1));
            }
            else if (grassModel.getLevel() == GameConstant.GRASS_GROUND_LEVEL_2)
            {
                grassLineLayer1.addGameObject(new GrassLineUnit(grassModel.getBlockSize(), 
                        grassModel.getxPosition(), GameConstant.GRASS_GROUND_LEVEL_2));
            }
            else if (grassModel.getLevel() == GameConstant.GRASS_GROUND_LEVEL_3)
            {
                grassLineLayer3.addGameObject(new GrassLineUnit(grassModel.getBlockSize(), 
                        grassModel.getxPosition(), GameConstant.GRASS_GROUND_LEVEL_3));
            }
        }

        // Wall Layer
        GameLayer<WallUnit> wallLayer = new WallLayer();
        wallLayer.addGameObject(new WallUnit());

        // Background Layer
        GameLayer<BackgroundUnit> backgroundLayer = new BackgroundlLayer();
        backgroundLayer.addGameObject(new BackgroundUnit(gameLevel.getGameLevel()));

        // OSD Layer
        GameLayer<OSDUnit> osdLayer = new OSDLayer();
        BubbleGunOSDUnit bubbleGunOSDUnit = new BubbleGunOSDUnit(50, 5);
        ScoreOSDUnit scoreOSDUnit = new ScoreOSDUnit(585, 5);
        scoreOSDUnit.setLiveBugCount(gameLevel.getBugModelList().size());
        scoreOSDUnit.setTimeLeft(gameLevel.getTotalTime());
        scoreOSDUnit.setLevelId(gameLevelId + 1);
        LevelOSDUnit levelOSDUnit = new LevelOSDUnit(gameLevelId + 1);
        osdLayer.addGameObject(bubbleGunOSDUnit);
        osdLayer.addGameObject(scoreOSDUnit);
        osdLayer.addGameObject(levelOSDUnit);
        
        // Guid Layer
        GameLayer<ProjectilePathUnit> guideLayer = new GuideLayer();
        ProjectilePathUnit projectilePathUnit = new ProjectilePathUnit(bubbleGunHandler);
        guideLayer.addGameObject(projectilePathUnit);
        
        gameScenePanel.addGameSceneLayer(backgroundLayer);
        gameScenePanel.addGameSceneLayer(grassLineLayer2);
        gameScenePanel.addGameSceneLayer(bugLayerLine1);
        gameScenePanel.addGameSceneLayer(grassLineLayer1);
        gameScenePanel.addGameSceneLayer(bugLayerLine2);
        gameScenePanel.addGameSceneLayer(grassLineLayer3);
        gameScenePanel.addGameSceneLayer(wallLayer);
        if (gameLevelId < 4 ) gameScenePanel.addGameSceneLayer(guideLayer);
        gameScenePanel.addGameSceneLayer(bubbleGunLayer);
        gameScenePanel.addGameSceneLayer(bubbleLayer);
        gameScenePanel.addGameSceneLayer(osdLayer);
        
        GameUpdateManager.getInstance().addGameHandlerInterface(bubbleGunHandler);
        
        GameUpdateManager.getInstance().setBubbleGunOSDUnit(bubbleGunOSDUnit);
        GameUpdateManager.getInstance().setScoreOSDUnit(scoreOSDUnit);
        GameUpdateManager.getInstance().startGameUpdateManager();
        GameUpdateManager.getInstance().startCountDown();

        KeyBoardControllerManager.getInstance().addUserControlableGameUnit(bubbleGunHandler);
        
        return gameScenePanel;
    }
    
    public void addGameStateListener(GameStateListener gameStateListener)
    {
        gameStateListeners.add(gameStateListener);
    }

    public GameScenePanel getCurrentGameScenePanel()
    {
        return currentGameScenePanel;
    }

    public GameSceneState getGameSceneState()
    {
        return gameSceneState;
    }

    public GameScenePanel getLevelUpPanel()
    {
        return gameScenePanelMap.get("Level Up");
    }
    
    public GameScenePanel getGameOverPanel()
    {
        return gameScenePanelMap.get("Game Over");
    }
    
    public GameScenePanel getGameCompletePanel()
    {
        return gameScenePanelMap.get("Game Complete");
    }
     
    public GameScenePanel getMainMenuPanel()
    {
        return gameScenePanelMap.get("Main Menu");
    }
    
    public GameScenePanel getIntroPanel()
    {
        return gameScenePanelMap.get("Intro");
    }
        
    public void onGameOver()
    {
        gameLevelId = 0;
        if (!gameSceneState.equals(GameSceneState.LEVEL_PASS))
        {
            gameSceneState = GameSceneState.GAME_OVER;
            for (GameStateListener gameStateListener : gameStateListeners)
            {
                gameStateListener.onGameStateUpdate(GameSceneState.GAME_OVER, null);
            }
        }
    }

    public void onLevelComplete(GameLevelScore gameLevelScore)
    {
        gameSceneState = GameSceneState.LEVEL_PASS;
        for (GameStateListener gameStateListener : gameStateListeners)
        {
            gameStateListener.onGameStateUpdate(GameSceneState.LEVEL_PASS, gameLevelScore);
        }
    }
    
    public void onGameComplete()
    {
        gameSceneState = GameSceneState.GAME_COMPLETE;
        gameLevelId = 0;
        for (GameStateListener gameStateListener : gameStateListeners)
        {
            gameStateListener.onGameStateUpdate(GameSceneState.GAME_COMPLETE, null);
        }
    }

    public boolean isGameCompleted()
    {
        int totalLevels = gameLevels.size();
        return (totalLevels - 1 <= gameLevelId);
    }
}
