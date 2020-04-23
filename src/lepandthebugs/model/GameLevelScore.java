package lepandthebugs.model;

/**
 *
 * @author Keshan De Silva
 */
public class GameLevelScore
{
    private final int level;
    private final int deadBugCount;
    private final int missedBugCount;
    private final int playTime;
    private final int score;

    public GameLevelScore(int level, int deadBugCount, int missedBugCount, int playTime, int score)
    {
        this.level = level;
        this.deadBugCount = deadBugCount;
        this.missedBugCount = missedBugCount;
        this.playTime = playTime;
        this.score = score;
    }

    public int getLevel()
    {
        return level;
    }

    public int getDeadBugCount()
    {
        return deadBugCount;
    }

    public int getMissedBugCount()
    {
        return missedBugCount;
    }
    
    public int getPlayTime()
    {
        return playTime;
    }

    public int getScore()
    {
        return score;
    }
}
