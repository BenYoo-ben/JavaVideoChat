import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class DrawingBoard extends JPanel {

    BufferedImage img;

    public DrawingBoard () {

    }           

    public void getImage(byte[] imageData){
    	
    	ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
    	try {
            this.img = ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = getSize();
        g.drawImage(img, 0,0, this);

    }
}
