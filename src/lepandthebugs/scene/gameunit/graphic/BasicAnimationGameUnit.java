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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Keshan De Silva
 */
public abstract class BasicAnimationGameUnit extends GameUnit
{
    private final ArrayList<BufferedImage> images = new ArrayList<>();
    private AffineTransform affineTransform;
    private AffineTransformOp affineTransformOp;
    private double lastAngle = 0;
    private int currentImageIndex = 0;
    private int stepCounter = 0;
    
    public BasicAnimationGameUnit()
    {
        for (int i = 0; i < getImageCount(); i++)
        {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(
                    "/lepandthebugs/scene/resources/" + getBaseImageName() + "_" + i + ".png"));
            this.images.add(ImageUtils.generateBufferedImage(imageIcon));
        }
        
        affineTransform = new AffineTransform();
        
        Timer animationTimer = new Timer(100, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (stepCounter == getTotalSteps()) stepCounter = 0;
                currentImageIndex = getAnimationSequence()[stepCounter++];
                
            }
        });
        animationTimer.start();
    }
    
    public abstract String getBaseImageName();
    public abstract int getImageCount();
    public abstract int getTotalSteps();
    public abstract int[] getAnimationSequence();
    
    @Override
    public void drawGameObject(Graphics graphics)
    {
        BufferedImage currentImage = images.get(currentImageIndex);
        
        Graphics2D graphics2D = (Graphics2D)graphics;
        double angle = ((Math.abs(lastAngle - getAngle())) > 0.1) ? lastAngle - getAngle() : 0;
        lastAngle = getAngle();
        affineTransform.rotate(Math.toRadians(angle), currentImage.getWidth() / 2,
                                                currentImage.getHeight() / 2);
        affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        
        graphics2D.drawImage(currentImage, affineTransformOp, (int)getXPosition(), (int)getYPosition());
    }

    @Override
    public void flip()
    {
        affineTransform = AffineTransform.getScaleInstance(-1, 1);
        affineTransform.translate(-60, 0);
    }
}
