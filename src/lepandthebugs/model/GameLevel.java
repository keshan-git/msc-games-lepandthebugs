package lepandthebugs.model;

import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public final class GameLevel
{
    private int gameLevel;
    private ArrayList<BugModel> bugModelList;
    private ArrayList<GrassModel> grassModelList;
    private int totalTime;
    
    public GameLevel(int gameLevel, ArrayList<BugModel> bugModelList, ArrayList<GrassModel> grassModelList, int totalTime)
    {
        this.gameLevel = gameLevel;
        this.bugModelList = bugModelList;
        this.grassModelList = grassModelList;
        this.totalTime = totalTime;
    }

    public int getGameLevel()
    {
        return gameLevel;
    }
    
    public ArrayList<BugModel> getBugModelList()
    {
        return bugModelList;
    }

    public ArrayList<GrassModel> getGrassModelList()
    {
        return grassModelList;
    }  

    public int getTotalTime()
    {
        return totalTime;
    }   
}


