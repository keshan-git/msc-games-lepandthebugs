/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs.scene.layers;

import lepandthebugs.scene.gameunit.graphic.BugUnit;

/**
 *
 * @author Keshan De Silva
 */
public class BugLayer extends GameLayer<BugUnit>
{
    private static int counter;

    public BugLayer()
    {
        counter++;
    }
    
    @Override
    public String getLayerName()
    {
        return "Bug Layer_" + counter;
    } 
  
}
