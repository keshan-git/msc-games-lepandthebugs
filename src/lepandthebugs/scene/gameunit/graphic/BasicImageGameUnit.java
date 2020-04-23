/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lepandthebugs.scene.gameunit.graphic;

import lepandthebugs.scene.gameunit.GameUnit;
import lepandthebugs.utils.ImageUtils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Keshan De Silva
 */
public abstract class BasicImageGameUnit extends GameUnit
{
    private BufferedImage image;
    private AffineTransform affineTransform;
    private AffineTransformOp affineTransformOp;
    private double lastAngle = 0;
    
    public BasicImageGameUnit()
    {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/" + getImageName()));
        this.image = ImageUtils.generateBufferedImage(imageIcon);
        affineTransform = new AffineTransform();
    }
    
    public void reloadImages()
    {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/lepandthebugs/scene/resources/" + getImageName()));
        this.image = ImageUtils.generateBufferedImage(imageIcon);
        affineTransform = new AffineTransform();
    }
    
    public abstract String getImageName();
    
    @Override
    public void drawGameObject(Graphics graphics)
    {
        Graphics2D graphics2D = (Graphics2D)graphics;
        double angle = ((Math.abs(lastAngle - getAngle())) > 0.1) ? lastAngle - getAngle() : 0;
        lastAngle = getAngle();
        affineTransform.rotate(Math.toRadians(angle), image.getWidth() / 2,
                                                image.getHeight() / 2);
        affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        
        graphics2D.drawImage(image, affineTransformOp, (int)getXPosition(), (int)getYPosition());
    }
    
    @Override
    public void flip()
    {
        affineTransform = AffineTransform.getScaleInstance(-1, 1);
        affineTransform.translate(-60, 0);
    }
}
