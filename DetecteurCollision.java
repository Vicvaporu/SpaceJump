import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
	*La classe se charge principalement de la detection des objets proches du personnage,
	* de la detection d une collison entre le personnage est un objet mortel ou un objet Victoire(Trophée)
	* et de detecter les limites du Scenario
*/
public class DetecteurCollision{
	
	private Scene scene;
	private Personnage personnage;
	private FenetreJeu fen;
	private Scenario scenario;
	
	//Attributs
	//Les caracteristiques des dimensions de Notre Fenetre de Reference
	//en format de "Pixel" et en "Matrice"
	//Fenetre de Reference
	private final int largeurInitialPixel=820;//820*****164 bloc matrice
	private final int hauteurInitialPixel=400;//400*****80 bloc matrice
	private final int dxInitial=5;
	private final int dyInitial=5;
	private final int largeurInitialMatrice=(int)(largeurInitialPixel/dxInitial);
	private final int hauteurInitialMatrice=(int)(hauteurInitialPixel/dyInitial);
	
	private double G;//Grandissemnt
	private int[][]matriceEspace;
	private int[][]matricePersonnage;
	

	//Position en Coordonnees des Sommets(Rectangle) du Personnage *Pixel*
	private int[]A=new int[2];
	private int[]B=new int[2];
	private int[]C=new int[2];
	private int[]D=new int[2];
	
	
	//Taille de Mario en Matrice
	private int nbCasesLargeur;
	private int nbCasesHauteur;
	//Coord en Matrice du point A
	private int xPersonnageMatrice;
	private int yPersonnageMatrice;
	
	private boolean positionLimiteHaut=false;
	private boolean positionLimiteBas=false;
	private boolean positionLimiteGauche=false;
	private boolean positionLimiteDroite=false;
	
	private boolean victoirePersonnage=false;
	
	public DetecteurCollision(Scene scene,Personnage personnage, FenetreJeu fen) {
		this.scene=scene;
		this.personnage=personnage;
		this.fen=fen;
		this.scenario=scene.getScenario();
		//Calcules de base pour determiner la taille de la matrice Espace et Personnage
		//Calculer le Gradissement de le scenario(A reviser)
		//determinerGrandissement();
		//Transformer les coord du personnage (les sommets du sprite Mario) en coord en Matrice;
		determinerCoordPixelPersonnage();
		//A reviser cette Methode
		//calculCaracteristiquesFenetreNouveau();
		
		//Creation et remplisage de la matrice Espace
		creationMatriceEspace();
		remplirMatriceEspace(this.scenario.getMatriceObjects());
		
		//Creation de la matrice du personnage et remplisage de Mario dans la matrice
		creationMatricePersonnage();
		remplirMatricePersonnage();
	}
	
	//Getters
	public int[][] getMatriceEspace(){return matriceEspace;}
	public int[][] getMatricePersonnage(){return matricePersonnage;}
	public boolean getPositionLimiteHaut(){return positionLimiteHaut;}	
	public boolean getPositionLimiteBas(){return positionLimiteBas;}
	public boolean getPositionLimiteGauche(){return positionLimiteGauche;}
	public boolean getPositionLimiteDroite(){return positionLimiteDroite;}
		
	//On peut ameliorer mais je suis fatigue
	//Mario est un Rectangle et on a 4 sommets
	//A sommet de gauche en haut
	//B sommet de doite en haut
	//C sommet de gauche en bas 
	//D sommet de doite en bas
	//La methode determine les cooord en Pixel des ces points
	//Cella sera utile pour mesurer l hauteur et largeur de Mario en nb de Cases Matrice
	public void determinerCoordPixelPersonnage(){
		//En X 
		A[0]=this.personnage.getxMario();
		C[0]=A[0];
		B[0]=this.personnage.getxMario()+this.personnage.getLargeur();
		D[0]=B[0];
		
		//En Y
		A[1]=this.personnage.getyMario();
		B[1]=A[1];
		C[1]=this.personnage.getyMario()+this.personnage.getHauteur();
		D[1]=C[1];
	}
	
	
	//On cree ici la matrice 2D qui va representer notre espace 
	//dans la fenetre de coordonnes
	public int[][] creationMatriceEspace(){
		System.out.println("Hauteur"+hauteurInitialMatrice+"Largeur"+largeurInitialMatrice);
		int[][] matriceEspace = new int[hauteurInitialMatrice][largeurInitialMatrice];
		this.matriceEspace=matriceEspace;
		return matriceEspace;
	}
	
	
	
	
	//Declaration de la Matrice Personnage
	public int[][] creationMatricePersonnage(){
		int[][] matricePersonnage = new int[hauteurInitialMatrice][largeurInitialMatrice];
		this.matricePersonnage=matricePersonnage;
		return matricePersonnage;
	}
	
	
	
	
	//Matrice qui contient uniquement a Mario
	//On efface la Matrice et on l imprime a nouveau Mais avec la nouvelle Position Mario
	public void remplirMatricePersonnage(){
		xPersonnageMatrice=convertirXpixel(A[0]);
		yPersonnageMatrice=convertirYpixel(A[1]);
		nbCasesLargeur=convertirXpixel(B[0])-convertirXpixel(A[0]);
		nbCasesHauteur=convertirYpixel(C[1])-convertirYpixel(A[1]);
		//On Vide le tableau(On peut ameliorer)
		for(int i=0;i<matricePersonnage.length;i++){
			for(int j=0;j<matricePersonnage[i].length;j++){
				matricePersonnage[i][j]=0;
			}
		}
		
		//On remplit le tableau a nouveau mais avec les nouvelles positions du personnage
		for(int i=0;i<nbCasesHauteur;i++){
			for(int j=0;j<nbCasesLargeur;j++){
				matricePersonnage[yPersonnageMatrice+i][xPersonnageMatrice+j]=4;
			}
		}
	}
	
	
	//Matrice qui contient les Objets du Scenario/Mario exclut
	public void remplirMatriceEspace(int[][]scenario){//160*75
		matriceEspace=scenario;
	}
	
	
	//Condition de Choc avec un objet
	//Cette methode nous permet de detecter s il y a une collision entre le personnage et l objet 
	//De meme pendant l execution de cette methode, on va garder aussi d´autres informations par exemple:
	//1)Collison objet Non Mortel avec Mario (Cette info sera utilise par la Classe Personnage pour la Position du Personnage)
	//2)Collision objet Mortel avec Mario =======> Mort de Mario :(
	//3)Voir si le personnage peut aller au prochain scenario(Voir si Mario est dans la limite du scenario)
	
	public boolean[] analyserConditionCollision(){
		//Tableau qui nous indiquera les mouvements possibles
		boolean[] numeroReturn= new boolean[5];
		//NumeroReturn est un tableau qui nous dit si les mouvemnts possibles du Personnage ET si Mario est Vivant ou non
		//On dit d abord que toute est Vraie 
		for(int i=0;i<numeroReturn.length;i++){numeroReturn[i]=true;}
		
		int[][]scenario=new int[hauteurInitialMatrice][largeurInitialMatrice];//Scenario sera la somme de matrice space et personnage
		//Dterminer coord du personnage
		this.determinerCoordPixelPersonnage();
		this.remplirMatricePersonnage();
		
		//On somme les deux matrices(Space et Personnage)dans "scenario" pour apres analyser si possible d´avancer
		//******De meme ici on voit si Mario a toucher objet Mortel*******
		//Remarque c est pas necessaire de savoir si l objet Mortel est proche de Mario il suffit que Mario touche l objet
		for(int i=0;i<scenario.length;i++){
			for(int j=0;j<scenario[i].length;j++){
				scenario[i][j]=matriceEspace[i][j]+matricePersonnage[i][j];
				if(scenario[i][j]==6){numeroReturn[4]=false;}//La condition d ici nous va dire si Mario a touché un objet Mortelle
															//La somme de Mario "4" +ObjetMortelle "2" donne Mario est Mort "6"
				if(scenario[i][j]==7){this.victoirePersonnage = true;}//On verifie si Mario a Gagner
					//System.out.print(""+scenario[i][j]);
																	//La Somme de Mario "4" + Objet Victoire "3" est Personnage Gagne "7"
			}
			//System.out.print("\n");
		}
		
		//***Analyser si possible avancer***
		//*Dans cette partie on detecte les objets qui sont autour de Mario ET
		//*Si Mario est dans la limite du scenario
		//Pour savoir si on est dans les bornes de la matrice("scenario")
		this.positionLimiteHaut=false;
		this.positionLimiteBas=false;
		this.positionLimiteGauche=false;
		this.positionLimiteDroite=false;
		
		//Partie I:Voir si on est dans la limite de la Matrice
		//Dans en haut de la matrice
		if(yPersonnageMatrice==0){numeroReturn[0]=false; positionLimiteHaut=true;}//Pas Possible Mvt Haut
	    else//Dans en bas de la matrice
		if(yPersonnageMatrice+nbCasesHauteur==hauteurInitialMatrice){numeroReturn[1]=false; positionLimiteBas=true;}//Pas Possible Mvt Bas
		//Dans l extremite gauche
		if(xPersonnageMatrice==0){numeroReturn[2]=false; positionLimiteGauche=true;}
		else//Dans l extremite droite
		if(xPersonnageMatrice+nbCasesLargeur==largeurInitialMatrice){numeroReturn[3]=false; positionLimiteDroite=true;}
		
		//*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/
		//Partie II:Voir si Mario a des objets Non Mortels Proche de lui
		//Analyser si objet en Haut
		if(positionLimiteHaut==false){
			for(int i=0;i<nbCasesLargeur;i++){
				if(scenario[yPersonnageMatrice-1][xPersonnageMatrice+i]==1){numeroReturn[0]=false;}
			}
		}
		
		//Analyser si objet en Bas
		if(positionLimiteBas==false){
			for(int i=0;i<=nbCasesLargeur-1;i++){
				if(scenario[(yPersonnageMatrice+nbCasesHauteur)][xPersonnageMatrice+i]==1){numeroReturn[1]=false;}
			}
		}
		
		//Analyser si objet a Gauche
		if(positionLimiteGauche==false){
			for(int i=0;i<nbCasesHauteur;i++){
				if(scenario[yPersonnageMatrice+i][xPersonnageMatrice-1]==1){numeroReturn[2]=false;}
			}
		}
		
		//Analyser si objet a Droite
		if(positionLimiteDroite==false){	
			for(int i=0;i<nbCasesHauteur;i++){
				if(scenario[yPersonnageMatrice+i][(xPersonnageMatrice+nbCasesLargeur)]==1){numeroReturn[3]=false;}
			}
		}
		
		//On revoie Uniquement les mouvements Possibles que le Personnage peut Faire
		//ET si Mario a touche un Objet Mortel
		//Au debut les cases de numeroReturn sont Vraies
		/*
		numeroReturn[0]=Mvt Haut Possible?
		numeroReturn[1]=Mvt Bas Possible?
		numeroReturn[2]=Mvt Gauche Possible?
		numeroReturn[3]=Mvt Droite Possible?
		numeroReturn[4]=Mario a touche objet Mortel==>(Mario est Vivant?)
		*/
		return numeroReturn;
	}
	
//**************************************************************************************************************************************
//**************************************************************************************************************************************	
	
	//Convertiseur de Pixel en Coord Matrice
	public int convertirXpixel(int x){
		return (int)(x/dxInitial);
	}
	
	public int convertirYpixel(int y){
		return (int)(y/dyInitial);
	}
	

	
	//*****Pour imprimer matrices
		/*for(int i = 0 ; i < matricePersonnage.length; i++ ){
			for(int j = 0; j< matricePersonnage[i].length; j++){
				System.out.print(""+matricePersonnage[i][j]);
			}
				System.out.print("\n");
		}*/
			/*
		for(int i=0;i<numeroReturn.length;i++){
			System.out.println(numeroReturn[i]);
		}
		System.out.println("**************");
		System.out.println(positionLimiteHaut);
		System.out.println(positionLimiteBas);
		System.out.println(positionLimiteGauche);
		System.out.println(positionLimiteDroite);
		System.out.println("X:"+xPersonnageMatrice+" Y:"+yPersonnageMatrice);
		System.out.println("NbCasesLargeur:"+nbCasesLargeur+"NbCasesHauteur:"+nbCasesHauteur);
		System.out.println("**************");*/
		
			//Fenetre Variable
	/*private double largeurNouveauPixel;
	private double hauteurNouveauPixel;
	private double dxNouveau;
	private double dyNouveau;
	private int largeurNouveauMatrice;
	private int hauteurNouveauMatrice;*/
	
		
	//Determination du Grandissement
	/*public void determinerGrandissement(){
		G=(this.fen.getXnouveau()*this.fen.getYnoveau())/(this.fen.xInitial*this.fen.yInitial);
	}
	*/
		
	//Determination des sizes de la Matrice et deplacement elementaire
	/*public void calculCaracteristiquesFenetreNouveau(){
		largeurNouveauPixel=this.fen.getXnouveau();
		hauteurNouveauPixel=this.fen.getYnoveau();
		dxNouveau=dxInitial*G;
		dyNouveau=dyInitial*G;
		largeurNouveauMatrice=(int)(largeurNouveauPixel/dxNouveau);
		hauteurNouveauMatrice=(int)(hauteurNouveauPixel/dyNouveau);
		System.out.println();
	}*/
	
	
	
	
}

