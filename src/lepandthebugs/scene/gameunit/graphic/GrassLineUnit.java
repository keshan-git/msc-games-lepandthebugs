/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs.scene.gameunit.graphic;

import lepandthebugs.model.DoubleXY;
import lepandthebugs.scene.gameunit.GameUnit;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class GrassLineUnit extends GameUnit
{
    private ArrayList<GrassUnit> grassList;
    private int blockSize;
    private int xPosition;
    private int yPosition;
    private static int counter = 0;
        
    public GrassLineUnit(int blockSize, int xPosition, int yPosition)
    {   
        this.blockSize = blockSize;
        this.grassList = new ArrayList<>(blockSize);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        counter++;
        
        generateGrassLines();
    }
    
    private void generateGrassLines()
    {
        for (int i = 0; i < blockSize; i++)
        {
            grassList.add(new GrassUnit(xPosition + (i * 35), yPosition));
        }
    }
    
    @Override
    public String getGameObjectName()
    {
        return "Grass Line_" + counter;
    }

    @Override
    public void drawGameObject(Graphics graphics)
    {
        for (GrassUnit grassUnit : grassList)
        {
            grassUnit.drawGameObject(graphics);
        }
    }

    @Override
    public void flip()
    {
        
    }
    
}
