package Entidades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import config.config;

import DataBase.Connect;
import Sensor.Acelerometro;
import Server.Server;

public class position implements Observer{
	private ArrayList<particle> array;

	private int tolerancia = 8;
	private Acelerometro acel;
	private Connect bd = new Connect();
	private config config;

	// Particle position on Board, this information it is on two hash map
	public position(config config) throws IOException {
		this.config = config;
		acel = new Acelerometro(0, 0, 0);
		Thread t = new Thread(new Server(acel));
		t.start();

		this.array = new ArrayList<particle>();
	}

	// Coloca as particulas RANDOM
	public void rand() {
		int i = 0;
		Random r = new Random();
		//particle casa = new particle(config.getWidth() / 2,config.getHeigth() / 2, 1);
		particle flor1 = new particle(config.getWidth() - 50,config.getHeigth() - 50,2);
		particle flor2 = new particle(50,config.getHeigth() - 50,2);
		particle flor3 = new particle(config.getWidth() - 50,50,2);
		particle flor4 = new particle(50,50,2);
		this.addPoint(flor1);
		this.addPoint(flor2);
		this.addPoint(flor3);
		this.addPoint(flor4);
		//this.addPoint(casa);
		while (i < config.getNrParticle()) {
			int particleX = r.nextInt(config.getWidth());
			int particleY = r.nextInt(config.getHeigth());
			particle particula = new particle(particleX, particleY, 0,
					"blackBall", 0);
			// particle particula1 = new particle(particleX, this.heigth / 2,
			// 0,"blackBall", 0);

			if (this.addPoint(particula)) {
				this.bd.addParticle(particula);
				i++;
			}/*
			 * if (this.addPoint(particula1)) { this.bd.addParticle(particula1);
			 * i++; }
			 */
		}

	}

	public void moviment() {

		if (this.array.size() == 0) {

		} else {
			for (particle par : this.array) {
				if (par.getType() == 0) { // So faz o movimento para as outras
											// noa para o casa

					// Cria uma particula com a posi‹o futura para calcular
					// sobreposi›es
					/**
					 * particle e = new particle( (par.getX() + (acel.getY() *
					 * (-1))), par.getY() + (acel.getX()), 1); // Vai dar que
					 * est‡ sempre sobre ela propria if (!subposition(e, par)) {
					 * // Devolve True se estiver // sobre alguma particula
					 * par.setX((par.getX() + (acel.getY() * (-1))));
					 * par.setY(par.getY() + acel.getX()); }
					 */
					/**
					 * particle e = new particle( (par.getX() + (acel.getY() *
					 * (-1))), par.getY() + (acel.getX() * (-1)), 1); // Vai
					 * daque // est‡ sempre // sobre ela // propria if
					 * (!subposition(e, par)) { // Devolve True se estiver // //
					 * sobre algumaparticula par.setX(par.getX() + (acel.getY()
					 * * (-1))); par.setY(par.getY() + (acel.getX() * (-1)));
					 * caso caso = new caso(par, new problema(1, 0, 0, 0, 0),
					 * new movimento((acel.getY() * (-1)), (acel.getX() *
					 * (-1))), 1); this.bd.addCaso(caso); par.setX((par.getX() +
					 * (acel.getY() * (-1)))); par.setY((par.getY() +
					 * (acel.getX() * (-1)))); }
					 */

					particle nova = bd.updateMovimento(par,config);
					if(nova == null) {
						nova = new particle(par.getX(), par.getY(), par.getType());
					}
					// O passo nunca pode ser maior que a tolerancia a que pode
					// estar do vizinho
						//nova.setX(nova.getX() % tolerancia);
						//nova.setY(nova.getY() % tolerancia);
						problema problema = subposition(nova, par);
						
						if (problema.collition()) { //Caso existam colis›es Verifica para onde pode andar
					
							par = problema.moveProblem(par);
							
							/*System.out.println("Collision " + problema);
							if(problema.getVizinho_CIMA() || problema.getVizinho_BAIXO()) {
								if(!problema.getVizinho_DIREITA() && !problema.getVizinho_ESQUERDA()) {
									if(nova.getX() > 0){
										par.setX(nova.getX() + 3);
									} else {
										par.setX(nova.getX() - 3);
									}
									System.out.println("Problemas com vizinhaa 1");

								}
									
							} 
							if(problema.getVizinho_DIREITA() || problema.getVizinho_ESQUERDA() ) {
								if(!problema.getVizinho_CIMA() && !problema.getVizinho_BAIXO()) {
									if(nova.getY() > 0){
										par.setY(nova.getY() + 3);
									} else {
										par.setY(nova.getY() - 3);
									}
									System.out.println("Problemas com vizinhaa 2");

								}
							}*/							
						} else { //Caso nao existam colis›es faz movimento normal
							par.setX(nova.getX());
							par.setY(nova.getY());
						}/*
						if(!problema.collition()) {
							par.setX(nova.getX());
							par.setY(nova.getY());
						}*/
						
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

			// this.array.add(e);
			return true;
		}
	}

	public boolean verifyCollision(particle e) {
		// the same size on two hashmap
		if (this.array.size() == 0) {
			return false; // nao colide
		}
		// Colis›es com a parede
		if (e.getX() + tolerancia > config.getWidth() || e.getX() - tolerancia < 0) {
			return true; // Pork colide ou sai do lado 0 ou do maior

		} else if (e.getY() + tolerancia > config.getHeigth() || e.getY() - tolerancia < 0) {
			return true; // Colide um dos Y pelo mesmo motivo do X
		}

		// Colis›es com outras particulas
		return subposition(e, null).collition(); // Devolve true se estiver sobre outra
										// particula
	}

	// MŽtodo para saber se se vai subrepor
	public problema subposition(particle e, particle original) {
		problema problema = new problema();

		for (particle par : this.array) {
			if (original != null && original.equals(par)) {
				// NÜo faz nada, nÜo compara com ele proprio
			} else if (e.getX() < par.getX() + tolerancia
					&& e.getX() > par.getX() - tolerancia) {

				if (e.getY() < par.getY() + tolerancia
						&& e.getY() > par.getY() - tolerancia) {
					if (par.getX() > e.getX()) {
						problema.setVizinho_DIREITA(true);
					}
					if (par.getX() < e.getX()) {
						problema.setVizinho_ESQUERDA(true);
					}
					if (par.getY() > e.getY()) {
						problema.setVizinho_CIMA(true);
					}
					if (par.getY() < e.getY()) {
						problema.setVizinho_BAIXO(true);
					}

				}
			}
		}
		return problema;
	}

	// Get and Set Methods
	public int getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(int tolerancia) {
		this.tolerancia = tolerancia;
	}

	public ArrayList<particle> getArray() {
		return array;
	}

	public void setArray(ArrayList<particle> array) {
		this.array = array;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
