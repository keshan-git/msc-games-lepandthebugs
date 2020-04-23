package lepandthebugs.model;

import lepandthebugs.physics.GameConstant.BugDirection;
import lepandthebugs.physics.GameConstant.BugLine;

/**
 *
 * @author Keshan De Silva
 */
public class BugModel
{
    private final BugDirection bugDirection;
    private final BugLine bugLineId;
    private int startTime;
    
    public BugModel(BugDirection bugDirection, BugLine bugLineId)
    {
        this.bugDirection = bugDirection;
        this.bugLineId = bugLineId;
        this.startTime = 0;
    }

    public BugDirection getBugDirection()
    {
        return bugDirection;
    }

    public BugLine getBugLineId()
    {
        return bugLineId;
    }     

    public int getStartTime()
    {
        return startTime;
    }

    public void setStartTime(int startTime)
    {
        this.startTime = startTime;
    }
}
