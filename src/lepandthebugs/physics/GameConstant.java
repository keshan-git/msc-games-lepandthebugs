/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs.physics;

/**
 *
 * @author Keshan De Silva
 */
public final class GameConstant
{
    public static final int FPS = 30;
    public static final int GAME_SCENE_HEIGHT = 450;
    public static final int GAME_SCENE_WIDTH = 925;
    
    public static final int BUG_GROUND_LEVEL_1 = GAME_SCENE_HEIGHT - 50; // 400
    public static final int BUG_GROUND_LEVEL_2 = GAME_SCENE_HEIGHT - 30; // 420
    public static final int BUG_LEFT_LIMIT = 0;
    public static final int BUG_RIGHT_LIMIT = GAME_SCENE_WIDTH;
    public static final int BUG_WIDTH = 60;
    
    public static final int GRASS_GROUND_LEVEL_1 = GAME_SCENE_HEIGHT - 70;
    public static final int GRASS_GROUND_LEVEL_2 = GAME_SCENE_HEIGHT - 50;
    public static final int GRASS_GROUND_LEVEL_3 = GAME_SCENE_HEIGHT - 30;
        
    public enum BugDirection {LEFT, RIGHT, NONE};
    public enum BugStatus {NOT_GEN, LIVE, DIE, MISSED};
    public enum BugLine {LINE_1, LINE_2};
    public enum GameSceneState {INTRO, MAIN_MENU, PLAY, LEVEL_PASS, GAME_OVER, GAME_COMPLETE};
}
