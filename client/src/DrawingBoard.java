import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class DrawingBoard extends JPanel {

	BufferedImage img;

	public DrawingBoard() {

	}

	public void getImage(byte[] imageData) {

		ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
		try {
			this.img = ImageIO.read(bais);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// repaint img
		super.paintComponent(g);
		Dimension size = getSize();
		g.drawImage(this.img, 0, 0, size.width, size.height, 0, 0, this.img.getWidth(), this.img.getHeight(), null);

	}
}
