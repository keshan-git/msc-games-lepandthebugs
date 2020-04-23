/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs.scene.gameunit.baisc;

import lepandthebugs.gamehandler.BubbleGunHandler;
import lepandthebugs.model.DoubleXY;
import lepandthebugs.physics.PhysicsEngine;
import lepandthebugs.scene.gameunit.GameUnit;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class ProjectilePathUnit extends GameUnit
{
    private BubbleGunHandler bubbleGunHandler;

    public ProjectilePathUnit(BubbleGunHandler bubbleGunHandler)
    {
        this.bubbleGunHandler = bubbleGunHandler;
    }

    @Override
    public String getGameObjectName()
    {
        return "Projectile Guide";
    }

    @Override
    public void drawGameObject(Graphics graphics)
    {
        ArrayList<DoubleXY> projectileData = PhysicsEngine.generateProjectilePath(bubbleGunHandler.getGunConfigurations());
        
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.setStroke(new BasicStroke(2));
                
        for (int i = 0; i < projectileData.size() - 1; i+=2)
        {
            graphics2D.drawLine(
                    (int)projectileData.get(i).getX(),
                    (int)projectileData.get(i).getY(),
                    (int)projectileData.get(i + 1).getX(),
                    (int)projectileData.get(i + 1).getY());
        }
        
    }

    @Override
    public void flip() {}

}
