package lepandthebugs.scene.layers;

import lepandthebugs.scene.gameunit.graphic.GrassLineUnit;

/**
 *
 * @author Keshan De Silva
 */
public class GrassLineLayer extends GameLayer<GrassLineUnit>
{
    private static int counter;

    public GrassLineLayer()
    {
        counter++;
    }
    
    @Override
    public String getLayerName()
    {
        return "Grass Line Layer_" + counter;
    }    
}
