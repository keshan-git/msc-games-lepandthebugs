
package lepandthebugs.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Keshan De Silva
 */
public class ImageUtils
{
    public static BufferedImage generateBufferedImage(ImageIcon imageIcon)
    {
        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(),
                                            imageIcon.getIconHeight(),
                                            BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        imageIcon.paintIcon(null, graphics, 0,0);
        graphics.dispose();
        
        return bufferedImage;
    }
}
