package lepandthebugs.model;

/**
 *
 * @author Keshan De Silva
 */
public class GrassModel
{
    private final int blockSize;
    private final int xPosition;
    private final int level;

    public GrassModel(int blockSize, int xPosition, int level)
    {
        this.blockSize = blockSize;
        this.xPosition = xPosition;
        this.level = level;
    }

    public int getBlockSize()
    {
        return blockSize;
    }

    public int getxPosition()
    {
        return xPosition;
    }

    public int getLevel()
    {
        return level;
    }
}