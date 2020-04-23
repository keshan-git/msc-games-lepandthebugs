
package lepandthebugs.gamehandler;

import lepandthebugs.scene.gameunit.GameUnit;

/**
 *
 * @author Keshan De Silva
 * @param <T>
 */
public abstract class GameUnitHandler<T extends GameUnit> implements GameHandlerInterface
{
    private final T gameUnit;

    public GameUnitHandler(T gameUnit)
    {
        this.gameUnit = gameUnit;
    }

    public T getGameUnit()
    {
        return gameUnit;
    }

}
