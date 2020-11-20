import java.util.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.Timer;
/*
	*La classe Maps est là ou on crée les Scenarios du Jeu
	*Chaque Scenario sera placé dans un matrice 2 dimensions nommée MAP 
	*Cette matrice sera le Scenario Complet de notre Jeu et contient tous les Scenarios
	* De même, dans cette classe on reálise le changement de Scenario de Scenario si Besoin
*/
public class Maps{
	//Taille de la Fenetre Initial en Pixel
	final int xInitial=820;
	final int yInitial=400;
	
	//On garde les adresses des autres classes qu´ on a besoin
	private Scene scene;
	private DetecteurCollision detecteur;
	private Personnage personnage;
	private Impression impression;
	
	private Scenario [][] map = new Scenario[10][10]; //Un tableau qui contient le Map du Jeu
	//Coord ou se trouve le personnage dans Map
	private final int xDebut=0;
	private final int yDebut=1;
	private int xMap;
	private int yMap;
	
	//Scenario qui doit etre affiche si Changement de Scenario
	private Scenario scenarioProchain;
	
	//Ensemble de Scenarios
	private Scenario scenarioTest;
	private Scenario scenario1;
	private Scenario scenario2;
	private Scenario scenario3;
	private Scenario scenario4;
	private Scenario scenario5;
	private Scenario scenario6;
	private Scenario scenario7;
	private Scenario scenario8;
	private Scenario scenario9;
	private Scenario scenario10;
		
	//Contructeur
	public Maps(Scene scene){
		this.scene = scene;//Avec scene on pourra trouver les adresses des autres classes
		this.xMap = xDebut;//Position initial du Personnage en Map
		this.yMap = yDebut;
		
		scenarioTest = createurScenarioTest(1,1);
		scenario1 = createurScenario1(0,1);
		scenario2 = createurScenario2(2,1);
		scenario3 = createurScenario3(3,1);
		scenario4 = createurScenario4(4,1);
		scenario5 = createurScenario5(5,1);
		scenario7 = createurScenario7(5,3);
		scenario6 = createurScenario6(5,2);		
		scenario8 = createurScenario8(4,3);
		scenario9 = createurScenario9(3,3);
		scenario10 = createurScenario10(3,2);
		
		
		
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				System.out.print("" +map[i][j]);
					//System.out.print(""+scenario[i][j]);
			}
			System.out.print("\n");
		}
		
		
	}
	
	//Creation du premier scenario
	public Scenario createurScenario1(int xPosition, int yPosition){
		
		Objet o1 = new Objet(0,70,6,160,1);
		
		
		LinkedList<Objet> liste1 = new LinkedList<Objet>();
		
		liste1.add(o1);
		
		
		Scenario scenario = new Scenario(liste1);
		map[yPosition][xPosition] = scenario;
		return scenario;
	}
	
	public Scenario createurScenarioTest(int xPosition, int yPosition){
		Objet o1 = new Objet(0,70,6,163,1);
		Objet o2 = new Objet(50,58,12,12,1);
		Objet o3 = new Objet(4,5,10,20,1);
		Objet o4 = new Objet(50,0,35,10,1);
		Objet o5 = new Objet(75,35,20,5,1);
		Objet o6 = new Objet(90,55,15,40,1);
		Objet o7 = new Objet(90,10,25,30,1);
		Objet o8 = new Objet(130,10,70,34,2);
		
		LinkedList<Objet> liste = new LinkedList<Objet>();
		liste.add(o1);
		liste.add(o2);
		liste.add(o3);
		liste.add(o4);
		liste.add(o5);
		liste.add(o6);
		liste.add(o7);
		liste.add(o8);
	
		Scenario scenario = new Scenario(liste);
		map[yPosition][xPosition] = scenario; 
		return scenario;
		
	}
	
	public Scenario createurScenario2(int xPosition, int yPosition){
			
		Objet o1 = new Objet(0,70,6,163,1);
		Objet o2 = new Objet(30,50,18,18,1);
		Objet o3 = new Objet(62,0,18,18,1);
		Objet o4 = new Objet(150,50,6,6,3);
		
		
		LinkedList<Objet> liste2 = new LinkedList<Objet>();
		liste2.add(o1);
		liste2.add(o2);
		liste2.add(o3);
		liste2.add(o4);
			
		Scenario SN2 = new Scenario(liste2);
		map[yPosition][xPosition] = SN2;
		return SN2;
	}
	
	public Scenario createurScenario3(int xPosition, int yPosition){
		
		Objet o1 = new Objet(0,70,6,163,1);
		Objet o2 = new Objet(30,50,18,18,1);
		Objet o3 = new Objet(60,0,18,18,1);
		Objet o4 = new Objet(100,55,12,12,2);
		
		
		LinkedList<Objet> liste3 = new LinkedList<Objet>();
		liste3.add(o1);
		liste3.add(o2);
		liste3.add(o3);
		liste3.add(o4);
			
		Scenario SN3 = new Scenario(liste3);
		map[yPosition][xPosition] = SN3;
		return SN3;
	}
	
	public Scenario createurScenario4(int xPosition, int yPosition){
		
		Objet o1 = new Objet(0,70,6,163,1);
		Objet o2 = new Objet(0,0,12,12,2);
		Objet o3 = new Objet(40,20,48,18,1);
		Objet o4 = new Objet(90,0,48,18,1);
		Objet o5 = new Objet(148,50,18,12,2);
		Objet o6 = new Objet(148,0,18,12,2);
		
		LinkedList<Objet> liste4 = new LinkedList<Objet>();
		liste4.add(o1);
		liste4.add(o2);
		liste4.add(o3);
		liste4.add(o4);
		liste4.add(o5);
		liste4.add(o6);
			
		Scenario SN4 = new Scenario(liste4);
		map[yPosition][xPosition] = SN4;
		return SN4;
	}
	
	public Scenario createurScenario5(int xPosition, int yPosition){
		
		Objet o1 = new Objet(0,60,18,138,1);
		Objet o2 = new Objet(0,0,18,163,1);
		Objet o3 = new Objet(35,45,12,12,2);
		Objet o4 = new Objet(70,20,12,12,2);
		Objet o5 = new Objet(105,45,12,12,2);
		Objet o6 = new Objet(140,20,12,12,2);
		Objet o7 = new Objet(155,20,60,6,1);
		
		LinkedList<Objet> liste5 = new LinkedList<Objet>();
		liste5.add(o1);
		liste5.add(o2);
		liste5.add(o3);
		liste5.add(o4);
		liste5.add(o5);
		liste5.add(o6);
		liste5.add(o7);
			
		Scenario SN5 = new Scenario(liste5);
		map[yPosition][xPosition] = SN5;
		return SN5;
	}
	

	public Scenario createurScenario6(int xPosition, int yPosition){
		
		Objet o1 = new Objet(0,0,70,138,1);
		Objet o2 = new Objet(138,30,6,6,1);
		
		
		LinkedList<Objet> liste6 = new LinkedList<Objet>();
		liste6.add(o1);
		liste6.add(o2);
		
			
		Scenario SN6 = new Scenario(liste6);
		map[yPosition][xPosition] = SN6;
		return SN6;
	}
	
	public Scenario createurScenario7(int xPosition, int yPosition){
		
		Objet o1 = new Objet(0,0,12,138,1);
		Objet o2 = new Objet(0,12,12,12,2);
		Objet o3 = new Objet(0,56,24,72,2);
		Objet o4 = new Objet(100,40,6,24,1);
		Objet o5 = new Objet(136,30,50,27,2);
		Objet o6 = new Objet(72,68,12,58,2);
		
		LinkedList<Objet> liste7 = new LinkedList<Objet>();
		liste7.add(o1);
		liste7.add(o2);
		liste7.add(o3);
		liste7.add(o4);
		liste7.add(o5);
		liste7.add(o6);
			
		Scenario SN7 = new Scenario(liste7);
		map[yPosition][xPosition] = SN7;
		return SN7;
	}
	
	public Scenario createurScenario8(int xPosition, int yPosition){
		Objet o1 = new Objet(0,70,10,163,1);
		Objet o2 = new Objet(0,0,20,35,2);
		Objet o3 = new Objet(50,15,25,40,2);
		Objet o4 = new Objet(40,55,12,114,1);
		
		
		LinkedList<Objet> liste8 = new LinkedList<Objet>();
		liste8.add(o1);
		liste8.add(o2);
		liste8.add(o3);
		liste8.add(o4);
		
			
		Scenario SN8 = new Scenario(liste8);
		map[yPosition][xPosition] = SN8;
		return SN8;
	}
	
	public Scenario createurScenario9(int xPosition, int yPosition){
		
		Objet o1 = new Objet(0,0,80,30,2);
		Objet o2 = new Objet(30,70,10,133,1);
		Objet o3 = new Objet(120,55,15,20,2);
		Objet o4 = new Objet(30,0,20,12,2);
		Objet o5 = new Objet(30,50,20,12,2);
		Objet o6 = new Objet(70,0,10,90,1);
		Objet o7 = new Objet(30,20,6,12,1);
		Objet o8 = new Objet(30,44,6,12,1);
		Objet o9 = new Objet(55,15,24,35,2);
		
		LinkedList<Objet> liste9 = new LinkedList<Objet>();
		liste9.add(o1);
		liste9.add(o2);
		liste9.add(o3);
		liste9.add(o4);
		liste9.add(o5);
		liste9.add(o6);
		liste9.add(o7);
		liste9.add(o8);
		liste9.add(o9);
			
		Scenario SN9 = new Scenario(liste9);
		map[yPosition][xPosition] = SN9;
		return SN9;
	}		
	
	
	public Scenario createurScenario10(int xPosition, int yPosition){
		
			
		Objet o1 = new Objet(70,50,30,60,2);
		Objet o2 = new Objet(0,0,24,48,2);
		Objet o3 = new Objet(48,0,12,84,2);
		Objet o4 = new Objet(48,12,6,66,1);		
		Objet o5 = new Objet(132,0,24,30,2);
		Objet o6 = new Objet(60,30,12,24,2);
		Objet o7 = new Objet(152,50,30,12,1);
		Objet o8 = new Objet(140,74,6,6,3);
		
		LinkedList<Objet> liste10 = new LinkedList<Objet>();
		liste10.add(o1);
		liste10.add(o2);
		liste10.add(o3);
		liste10.add(o4);
		liste10.add(o5);
		liste10.add(o6);
		liste10.add(o7);
		liste10.add(o8);
			
		Scenario SN10 = new Scenario(liste10);
		map[yPosition][xPosition] = SN10;
		return SN10;
	}
	
	public void positionInitial(){
		this.xMap = xDebut;//Position initial du Personnage en Map
		this.yMap = yDebut;
		
	}
	
	//La methode nous dit si on doit changer de Scenario
	// Et quel Scenario est le suivant si il y a changement
	public void changementScenario(){
		detecteur = scene.getDetecteurCollision();
		personnage = scene.getPersonnage();
		impression = scene.getImpression();
		scenarioProchain = scene.getScenario();
		//Si changement de Scenario en Haut
		if(detecteur.getPositionLimiteHaut()){
			if(yMap-1>=0 && map[yMap-1][xMap]!=null){
				yMap = yMap - 1;
				scenarioProchain = map[yMap][xMap];
				personnage.setYmario(yInitial - personnage.getHauteur() - 10);//10 Pour eviter la detection de la limite du Scenario
			}
		}
		
		//Si changement de Scenario en Bas
		if(detecteur.getPositionLimiteBas()){
			if(yMap+1<map.length && map[yMap+1][xMap]!=null){
				yMap = yMap + 1;
				scenarioProchain = map[yMap][xMap];
				personnage.setYmario(10);
			}
		}
		
		//Si changement de Scenario a Gauche
		if(detecteur.getPositionLimiteGauche()){
			if(xMap-1>=0 && map[yMap][xMap-1]!=null){
				xMap = xMap - 1;
				scenarioProchain = map[yMap][xMap];
				personnage.setXmario(xInitial-personnage.getLargeur()-10);//10 Pour eviter detecter la limite du Scenario
			}
		}
		
		//Si changement de Scenario a Droite
		if(detecteur.getPositionLimiteDroite()){
			if(xMap+1<map[0].length && map[yMap][xMap+1]!=null){
				xMap = xMap + 1;
				scenarioProchain = map[yMap][xMap];
				personnage.setXmario(10);
			}
		}
		 
		 impression.setListeObjets(scenarioProchain.getListeObject());//On donne la nouvelle liste d´objets a Imprimer
		 detecteur.remplirMatriceEspace(scenarioProchain.getMatriceObjects());// On donne la matrice Espace pour detecteur Collison
																			//afin de que notre detecteur detecte les nouvelles Objets
		 
	}
	
	//Getters
	public Scenario getScenarioProchain(){return scenarioProchain;}
	public Scenario getScenarioTest(){return scenarioTest;}
	public Scenario getScenario1(){return scenario1;}
	public Scenario getScenario2(){return scenario2;}
	public Scenario getScenario3(){return scenario3;}
	public Scenario getScenario4(){return scenario4;}
	public Scenario getScenario5(){return scenario5;}
	public Scenario getScenario6(){return scenario6;}
	public Scenario getScenario7(){return scenario7;}
	public Scenario getScenario8(){return scenario8;}
	
	
	
	
	
}
