package lepandthebugs.controller;

import lepandthebugs.model.GameLevelScore;
import lepandthebugs.physics.GameConstant;

/**
 *
 * @author Keshan De Silva
 */
public interface GameStateListener
{
    public abstract void onGameStateUpdate(GameConstant.GameSceneState currentGameState
                                                        , GameLevelScore gameLevelScore);
}
