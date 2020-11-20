import java.util.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
/*
	*Cette classe est chargé de deroulé le jeu.
	* Il declare tous les classes d´on aura besoin pour la suite et elle garde leur adresse
	* Cette adresse pourra être disponible par les autres classes en utilisant le getter correspondant
	* 
	* À l´aide du Timer on excute certains actions qui vont nous mettre de contrôler le jeu
	* Plus précisement, on indique les etapes à suivre du jeu
	* De meme ici on controle l affichage dans la fenetre
*/
public class Scene extends JPanel implements ActionListener{
	private Graphics buffer; // Pour la fluidité des animations
	private BufferedImage arrierePlan; // L'arrière plan sur lequel on va dessiner
	//Fenetre
	private FenetreJeu fenetre;
	private Maps map= new Maps(this);
	private Scenario scenarioJeu;
	private Impression impression;
	
	//Images du Fond Scenario
	private ImageIcon iconFond1;
	private ImageIcon iconFond2;
	private Image imgFond1;
	private Image imgFond2;
	
	private int xFond1;
	
	//Timer
	private Timer timer;
	private int temps;
	
	//Personnage
	private Personnage personnage= new Personnage(this);
	private DetecteurCollision detecteur;
	private EcouterClavier ecouteur=new EcouterClavier(this,personnage);
	private Scenario scenarioDebut=map.getScenario1();
	private boolean[] numeroReturn;
	private boolean stopImpression=false;//Stop Impression images
	private boolean jeuTermine=false;//FinJeu
	public Scene(FenetreJeu fenetre) {
		super();
		// Creation de l'arriere plan sur laquelle on va dessiner
		arrierePlan = new BufferedImage(740,500,BufferedImage.TYPE_INT_RGB);
		// Tous ce qui sera dessiné par le buffer sera écrit dans l'ArrierePlan
		buffer = arrierePlan.getGraphics(); 
		this.fenetre=fenetre;
		
		scenarioJeu=map.getScenario1();
		impression = new Impression(scenarioJeu.getListeObject());
		detecteur= new DetecteurCollision(this,personnage,fenetre);
		
		
		//Load Images du Fond
		iconFond1 = new ImageIcon(getClass().getResource("/Ressources_Mario/images/EntreeFond.png"));
		iconFond2 = new ImageIcon(getClass().getResource("/Ressources_Mario/images/fondSpace.png"));
		this.imgFond1=this.iconFond1.getImage();
		this.imgFond2=this.iconFond2.getImage();
		//************** Clavier
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(ecouteur);
		//************** Impression et Programme Principale
		
		timer=new Timer(30,this);// On declare le timer avec une vitesse aproximative de 30 ms
		this.timer=timer;
		timer.start();
	
	}
	//Getters
	public Personnage getPersonnage(){return personnage;}
	public Impression getImpression(){return impression;}
	public DetecteurCollision getDetecteurCollision(){return detecteur;}
	public EcouterClavier getEcouteurClavier(){return ecouteur;};
	public Scenario getScenario(){return scenarioJeu;}
	public boolean[] getNumeroReturn(){return numeroReturn;}
	
	//Setters
	public void setScenario(Scenario scenarioProchain){this.scenarioJeu = scenarioProchain;}
	
	//Methode
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(scenarioJeu.equals(scenarioDebut)){
			buffer.drawImage(this.imgFond1,this.xFond1,2,2,null);//Fond du Scenario Initial
		}else{
			buffer.drawImage(this.imgFond2,this.xFond1,0,null);//Fond des Autres Scenarios
		}
		//On imprimme les objets du scenario
		impression.paintBloc(buffer);
		//Voir les buttons appuyer par le joueur
		boolean[] buttons= ecouteur.tableauButtonActive();
		//Cherchons la Position de Mario
		int xMario=personnage.modifierPositionxMario();
		int yMario=personnage.modifierPositionyMario();
		if(temps==3){// Condition pour reduire la vitesse d´affichage
		Image imgPersonnage = personnage.getImageMario();
		personnage.choisirImageMario(temps,this.numeroReturn,imgPersonnage);
		temps=0;
		}
		personnage.paintMario(buffer,xMario,yMario);
		temps++;
		g.drawImage(arrierePlan,0,0,this);
		
	}
	
	
	//*******************************************************************************************************************
	//Partie Principale
	
	public void actionPerformed(ActionEvent e) {
	
		boolean [] numeroReturn = detecteur.analyserConditionCollision();//On commence par demander tous les informations a Detecteur Collision
		personnage.setVivante(numeroReturn[4]);//On prend l info de si le Personnage est Vivante
		personnage.setMouvementPossible(numeroReturn);//On donne a Personnage les Mouvements Possibles de Mario
		this.numeroReturn=numeroReturn;
		analyserPersonnageVivant();//Voir si personnage vivant 
		repaint();
		map.changementScenario();//Voir si changement scenario
		scenarioJeu = map.getScenarioProchain();//Indiquer le scenario de Jeu
			
		
	}
	
	//********************************************************************************************************************
	//La methode nous indique les etapes a suivre dans le cas où le personnage est mort
	//On dirige le personnage vers la fenetre du debut
	public void analyserPersonnageVivant(){
		if(personnage.getVivant()==false){
			personnage.mort();
			map.positionInitial();
			impression.setListeObjets(scenarioDebut.getListeObject());
			detecteur.remplirMatriceEspace(scenarioDebut.getMatriceObjects());
			scenarioJeu = scenarioDebut;
		}
		
	}
	
	

	
		
}
		


