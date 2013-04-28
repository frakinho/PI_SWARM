package Interface;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import config.config;

import Entidades.particle;
import Entidades.position;


@SuppressWarnings("serial")
public class DrawSwarm extends JPanel {

	private position p;

	public DrawSwarm(config config) throws IOException {
		this.p = new position(config);
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
		BufferedImage flower = null;
		try {
			beeNormal = ImageIO.read(new File("beeBall.png"));
			home = ImageIO.read(new File("homeBall.png"));
			flower = ImageIO.read(new File("flowerBall2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail");
			e.printStackTrace();
		}
		
		ArrayList<particle> array = this.p.getArray();

		for (particle part : array) {
			if (part.getType() == 1) {
				g.drawImage(home, part.getX(), part.getY(), null);
			} else if(part.getType() == 2){
				g.drawImage(flower, part.getX(), part.getY(), null);
			} else {
				g.drawImage(beeNormal, part.getX(), part.getY(), null);
			}
			
			//g.setColor(Color.BLACK);
			//g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
			//g.drawString("X: " + part.getX() + " -Y: " + part.getY(), part.getX(), part.getY());
		}
		this.p.moviment();

	}

}
