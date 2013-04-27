package Interface;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Entidades.particle;
import Entidades.position;


@SuppressWarnings("serial")
public class DrawSwarm extends JPanel {

	@SuppressWarnings("unused")
	private int width; // largura
	@SuppressWarnings("unused")
	private int height; // altura
	private position p;

	public DrawSwarm(int width, int height) throws IOException {
		this.width = width;
		this.height = height;
		this.p = new position(width, height);
		this.p.rand();
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.setColor(Color.RED);

		BufferedImage beeNormal = null;
		BufferedImage home = null;
		try {
			beeNormal = ImageIO.read(new File("blackBall.png"));
			home = ImageIO.read(new File("redBall.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail");
			e.printStackTrace();
		}
		ArrayList<particle> array = this.p.getArray();

		for (particle part : array) {
			if (part.getType() == 1) {
				g.drawImage(home, part.getX(), part.getY(), null);
			} else {
				g.drawImage(beeNormal, part.getX(), part.getY(), null);
			}
		}
		this.p.moviment();

	}

}
