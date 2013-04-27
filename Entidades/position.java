package Entidades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import DataBase.Connect;
import Sensor.Acelerometro;
import Server.Server;

public class position {
	//private ArrayList<particle> array;

	//Estrutura de Dados duplicado com os mesmos valores dos dois lados mas em X e em Y
	private HashMap<Integer, particle> valoresX;
	private HashMap<Integer, particle> valoresY;

	private int width; // Largura
	private int heigth;// Altura
	private int tolerancia = 10;
	private int iX = 0;
	private Acelerometro acel;
	private Connect bd = new Connect();

	public position(int width, int heigth) throws IOException {

		acel = new Acelerometro(0, 0, 0);
		Thread t = new Thread(new Server(acel));
		t.start();
		this.width = width;
		this.heigth = heigth;
		//this.array = new ArrayList<particle>();
		
	}

	// Coloca as particulas RANDOM
	public void rand() {
		int i = 0;
		Random r = new Random();
		particle casa = new particle(this.width / 2, this.heigth / 2, 1);
		this.addPoint(casa);
		while (i < 50) {
			int particleX = r.nextInt(width);
			int particleY = r.nextInt(heigth);
			particle particula = new particle(particleX, particleY, 0, "blackBall", 0);
			//particle particula1 = new particle(particleX, this.heigth / 2, 0, "blackBall", 0);

			if (this.addPoint(particula)) {
				this.bd.addParticle(particula);
				i++;
			}/*
			if (this.addPoint(particula1)) {
				this.bd.addParticle(particula1);
				i++;
			}*/
		}
		
		
		System.out.println("Tau: " + iX);
	}

	public void moviment() {

		if (this.array.size() == 0) {

		} else {

			for (particle par : this.array) {
				if (par.getType() != 1) { // So faz o movimento para as outras
											// noa para o casa

					// Cria uma particula com a posi‹o futura para calcular
					// sobreposi›es
					/**particle e = new particle(
							(par.getX() + (acel.getY() * (-1))), par.getY()
									+ (acel.getX()), 1);
					// Vai dar que est‡ sempre sobre ela propria
					if (!subposition(e, par)) { // Devolve True se estiver
												// sobre alguma particula
						par.setX((par.getX() + (acel.getY() * (-1))));
						par.setY(par.getY() + acel.getX());
					}*/
					
					 /**
					 
					particle e = new particle(
							(par.getX() + (acel.getY() * (-1))), par.getY()
									+ (acel.getX() * (-1)), 1);
					// Vai dar que est‡ sempre sobre ela propria
					if (!subposition(e, par)) { // Devolve True se estiver
												// sobre alguma particula
						par.setX(par.getX() + (acel.getY() * (-1)));
						par.setY(par.getY() + (acel.getX() * (-1)));
						caso caso = new caso(par, new problema(1, 0, 0, 0, 0), new movimento((acel.getY() * (-1)), (acel.getX() * (-1)) ),1);
						this.bd.addCaso(caso);
						par.setX((par.getX() + (acel.getY() * (-1))));
						par.setY((par.getY() + (acel.getX() * (-1))));
					}*/
					bd.updateMovimento(par);
				}

			}
		}
	}

	public boolean addPoint(particle e) {
		if (verifyCollision(e)) { // Retorna true caso colida
			System.out.println("Fora do cenario");
			return false;
		} else {
			this.array.add(e);
			iX++;
			return true;
		}
	}

	public boolean verifyCollision(particle e) {
		if (array.size() == 0) {
			return false; // nao colide
		}
		// Colis›es com a parede
		if (e.getX() + tolerancia > width || e.getX() - tolerancia < 0) {
			return true; // Pork colide ou sai do lado 0 ou do maior

		} else if (e.getY() + tolerancia > heigth || e.getY() - tolerancia < 0) {
			return true; // Colide um dos Y pelo mesmo motivo do X
		}

		// Colis›es com outras particulas
		return subposition(e, null); // Devolve true se estiver sobre outra
										// particula
	}

	// MŽtodo para saber se se vai subrepor
	public boolean subposition(particle e, particle original) {
		for (particle par : this.array) {
			if (original != null && original.equals(par)) {
				// N‹o faz nada, n‹o compara com ele proprio
			} else if (e.getX() < par.getX() + tolerancia
					&& e.getX() > par.getX() - tolerancia) {

				if (e.getY() < par.getY() + tolerancia
						&& e.getY() > par.getY() - tolerancia) {
					return true;
				}
			}
		}
		return false;
	}

	public ArrayList<particle> getArray() {
		return array;
	}

	public void setArray(ArrayList<particle> array) {
		this.array = array;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public int getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(int tolerancia) {
		this.tolerancia = tolerancia;
	}

}
