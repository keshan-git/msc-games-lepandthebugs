
package lepandthebugs.controller;

/**
 *
 * @author Keshan De Silva
 */
public interface UserControlableGameUnit
{
    public abstract void onKeyPressed(int keyCode);
    public abstract void onKeyReleased(int keyCode);
}
