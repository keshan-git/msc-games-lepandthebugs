package lepandthebugs.gamehandler;

import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class GameState
{
    private static GameState instance;
    
    private final ArrayList<BubbleHandler> activeBubbleList = new ArrayList<>();
    private final ArrayList<BugHandler> liveBugList = new ArrayList<>();
    
    private GameState(){}
    
    public static GameState getInstance()
    {
        synchronized(GameState.class)
        {
            if (instance == null)
            {
                instance = new GameState();                
            }
            
            return instance;
        }
    }
    
    public void addBubble(BubbleHandler bubbleHandler)
    {
        activeBubbleList.add(bubbleHandler);
    }
    
    public void removeBubble(BubbleHandler bubbleHandler)
    {
        activeBubbleList.remove(bubbleHandler);
    }
    
    public ArrayList<BubbleHandler> getAllBubbleHandlers()
    {
        return activeBubbleList;
    }
    
    public void addBug(BugHandler bugHandler)
    {
        liveBugList.add(bugHandler);
    }
    
    public void removeBug(BugHandler bugHandler)
    {
        liveBugList.remove(bugHandler);
    }
    
    public ArrayList<BugHandler> getAllBugHandlers()
    {
        return liveBugList;
    }
}
