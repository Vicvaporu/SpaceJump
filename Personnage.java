import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
	* Cette classe se charge de controler principalement la position du personnage en utilisant des informations de detecteur collision
	* De même on contrôle l affichage du personnage et on indique l´image pour le personnage afin de pouvoir réaliser une animation
*/
public class Personnage {
	//Scene
	private Scene scene;
	
	//Images
	private BufferedImage arrierePlan;
	private Graphics buffer;
	private ImageIcon iconMario;//Pour le debut Uniquement
	//Mouvement Lateral dans le Sol
	private ImageIcon iconMarioArretGaucheNormal = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceStandingLeft.png"));
	private ImageIcon iconMarioArretDroiteNormal = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceStandingRight.png"));
	private ImageIcon iconMarioMarcheDroiteNormal = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceWalkRight.png"));
	private ImageIcon iconMarioMarcheGaucheNormal = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceWalkLeft.png"));
	//Mouvement Lateral dans le Toit
    private ImageIcon iconMarioArretGaucheInverse = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceStandingLeftInverse.png"));
	private ImageIcon iconMarioArretDroiteInverse = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceStandingRightInverse.png"));
	private ImageIcon iconMarioMarcheDroiteInverse = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceWalkRightInverse.png"));
	private ImageIcon iconMarioMarcheGaucheInverse = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceWalkLeftInverse.png"));
	//Saut avec gravite vers le Bas
	private ImageIcon iconMarioSautDroiteNormal = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceJumpRight.png"));
	private ImageIcon iconMarioSautGaucheNormal = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceJumpLeft.png"));
	//Saut avec Gravite vers l Haut
	private ImageIcon iconMarioSautDroiteInverse = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceJumpRightInverse.png"));
	private ImageIcon iconMarioSautGaucheInverse = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceJumpLeftInverse.png"));
	//Rotation dans l Air
	//Aller de l Haut vers le Bas
	private ImageIcon iconMarioSautGaucheRotationHB = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceJumpRightHB.png"));
	private ImageIcon iconMarioSautDroiteRotationHB = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceJumpRightHB.png"));
	//Aller du Bas vers l Haut
	private ImageIcon iconMarioSautGaucheRotationBH = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceJumpLeftBH.png"));
	private ImageIcon iconMarioSautDroiteRotationBH = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceJumpLeftBH.png"));
	
	private boolean rotationTermine = true;//Nous indique quand la rotation du personnage est Termine
	private boolean rotationDroiteNormal=false;//Pour savoir quel rotation
	private boolean rotationGaucheNormal=false;
	private boolean rotationDroiteInverse=false;
	private boolean rotationGaucheInverse=false;
	private boolean sansRotationHBdroite = false;
	private boolean sansRotationHBgauche = false;
	private boolean sansRotationBHdroite = false;
	private boolean sansRotationBHgauche = false;
	
	//Image de Mario qui sera Affiche
	private Image imgMario;
	private Image imagePrecedente;//On garde l image precedente
	
	private String nom = "Mario";//Nom du Personnage
	
	//Position Personnage
	private int xMario=50;//300*****50
	private int yMario=45;//245*****45
	//Position Respawn
	private int xRespawn=xMario;
	private int yRespawn=yMario;
	//Attributs pour definir le Mouvement du personnage
	private int dx;//La somme du mouvement Gauche et Droite
	private int dy;//La somme du mouvemnt Bas et Haut
	private int dxDroite=0;//Mouvement vers la Droite
	private int dxGauche=0;//Mouvemnt vers la Gauche
	private int dyHaut=0;//Mouvemnt vers l haut
	private int dyBas=0;//Mouvemnt vers le bas
	
	//Taille de l´image
	private int hauteur=38;//50*****10
	private int largeur=28;//28*****10
	
	final int droite=39;
	final int gauche = 37;
	final int space = 32;
	
	//Tableau a une Dimension qui nous indique les mouvements possibles du personnage
	private boolean[] mouvementPossible;//Meme chose que numeroReturn de Detecteur Collision
	private boolean intro=true;
	//Indicateur si le personnage Vivant
	private boolean vivante=true;
	
	public Personnage(Scene scene){
		//Pour Animation de Mario
		//BufferedImage arrierePlan = new BufferedImage(getLargeur(),getHauteur(),BufferedImage.TYPE_INT_RGB);
		//Graphics buffer =arrierePlan.getGraphics();
		this.buffer=buffer;
		this.arrierePlan=arrierePlan;
		this.scene=scene;	
		iconMario = new ImageIcon(getClass().getResource("/Ressources_Mario/images/SpaceStandingRight.png"));
		this.imgMario=this.iconMario.getImage();
	}
	
		//Getters
	public String getNom(){return nom;}
	public boolean getVivant(){return vivante;}
	public int getHauteur(){return this.hauteur;};
	public int getLargeur(){return this.largeur;};
	public int getDx(){return this.dx;}
	public int getDy(){return this.dy;}
	public int getxMario(){return this.xMario;}
	public int getyMario(){return this.yMario;}
	public int getxRespawn(){return xRespawn;}
	public int getyRespawn(){return yRespawn;}
	public Image getImageMario(){return this.imgMario;}
	//Setters
	public void setYmario(int yMario){this.yMario = yMario;}
	public void setXmario(int xMario){this.xMario = xMario;}
	public void setxRespawn(int xRespawn){this.xRespawn=xRespawn;}
	public void setyRespawn(int yRespawn){this.yRespawn=yRespawn;}
	public void setVivante(boolean vivante){this.vivante=vivante;}
	public void setDx(int dxDroite,int dyGauche){this.dx=dxDroite+dyGauche;}
	public void setDy(int dyHaut, int dyBas){this.dy=dyHaut+dyBas;}
	public void setDxDroite(int dxDroite){this.dxDroite=dxDroite;}
	public void setDxGauche(int dxGauche){this.dxGauche=dxGauche;}
	public void setDyHaut(int dyHaut){this.dyHaut=dyHaut;}
	public void setDyBas(int dyBas){this.dyBas=dyBas;}
	public void setMouvementPossible(boolean[]mouvementPossible){this.mouvementPossible=mouvementPossible;}
	
	//Methode
	public int modifierPositionxMario(){
		if(mouvementPossible[2]==false){this.setDxGauche(0);}
		if(mouvementPossible[3]==false){this.setDxDroite(0);}
		this.setDx(dxDroite,dxGauche);	
		return this.xMario=xMario+this.getDx();
	}
	
	public int modifierPositionyMario(){
		//La Gravité Scenario:Ecouteur clavier donnera un numero pair ou impar
		//Si pair alors gravite vers le bas sinon l inverse
		if((scene.getEcouteurClavier()).getCompteurSpace()%2==0){
				this.setDyBas(5);
				this.setDyHaut(0);
			}
		if((scene.getEcouteurClavier()).getCompteurSpace()%2==1){
				this.setDyBas(0);
				this.setDyHaut(-5);
			}
		if(mouvementPossible[0]==false){this.setDyHaut(0);}
		if(mouvementPossible[1]==false){this.setDyBas(0);}
		
		this.setDy(dyHaut,dyBas);
		return this.yMario=yMario+this.getDy();
	}
	
	public void mort(){//Si le personnage est mort alors on le place a cette position
		setXmario(xRespawn);
		setYmario(yRespawn);
		
	}
	
	public void paintMario(Graphics g, int xMario, int yMario){
		//buffer.drawImage(imgMario,xMario,yMario,null);
		//buffer.setColor(Color.yellow);	
		g.drawImage(imgMario,xMario,yMario,null);
	}	
	//En fontion où le personnage regarde on realise une certaine rotation
	public void determinerSensDeRotation(Image imagePrecedente){
		if(dyHaut == -5){//Rotation possible Bas vers l Haut
		if(imagePrecedente.equals(iconMarioArretDroiteNormal.getImage()) || imagePrecedente.equals(iconMarioMarcheDroiteNormal.getImage())){rotationDroiteNormal=true;}
		if(imagePrecedente.equals(iconMarioArretGaucheNormal.getImage()) || imagePrecedente.equals(iconMarioMarcheGaucheNormal.getImage())){rotationGaucheNormal=true;}
		}
		
		if(dyBas == 5){//Rotation possible Hait vers le Bas
		if(imagePrecedente.equals(iconMarioArretDroiteInverse.getImage()) || imagePrecedente.equals(iconMarioMarcheDroiteInverse.getImage())){rotationDroiteInverse=true;}
		if(imagePrecedente.equals(iconMarioArretGaucheInverse.getImage()) || imagePrecedente.equals(iconMarioMarcheGaucheInverse.getImage())){rotationGaucheInverse=true;}
		}
		//*/*/*//*/*Laisser Tomber au Personnage (Pas de Rotation)*/*/*/*/*/*/
		if(dyHaut == -5){//Tomber sans Rotation Bas vers l Haut
		if(imagePrecedente.equals(iconMarioArretDroiteInverse.getImage()) || imagePrecedente.equals(iconMarioMarcheDroiteInverse.getImage())){sansRotationBHdroite=true;}
		if(imagePrecedente.equals(iconMarioArretGaucheInverse.getImage()) || imagePrecedente.equals(iconMarioMarcheGaucheInverse.getImage())){sansRotationBHgauche=true;}
		}
		
		if(dyBas == 5){//Tomber sans Rotation Haut vers le Bas
		if(imagePrecedente.equals(iconMarioArretDroiteNormal.getImage()) || imagePrecedente.equals(iconMarioMarcheDroiteNormal.getImage())){sansRotationHBdroite=true;}
		if(imagePrecedente.equals(iconMarioArretGaucheNormal.getImage()) || imagePrecedente.equals(iconMarioMarcheGaucheNormal.getImage())){sansRotationHBgauche=true;}
		}
		
		
	}
	
	
	//Methode pour Indiquer quel image doit être afficher pour réaliser l´animation du personnage
	public void choisirImageMario(int temps,boolean[] detecteur, Image imagePrecedente){
		
			if(detecteur[4]==false){//Mario est Mort
				
				
				
			}else if(detecteur[0] && detecteur[1]){//*/*/*/*/*/*/*/*/*/ Mario dans l Air*/*/*/*/*/*/*/*/*/*/*/*/
				if(rotationTermine==false){ 
					this.determinerSensDeRotation(imagePrecedente);
					if(rotationDroiteNormal){//Rotation avec Mario voyant a Droite et dans le Sol
						this.imgMario = iconMarioSautDroiteNormal.getImage();
						if(imagePrecedente.equals(iconMarioSautDroiteNormal.getImage())){this.imgMario = iconMarioSautDroiteRotationBH.getImage();}
						if(imagePrecedente.equals(iconMarioSautDroiteRotationBH.getImage())){
							this.imgMario = iconMarioSautGaucheInverse.getImage();
							rotationTermine=true; rotationDroiteNormal = false;
						}
					}
					if(rotationGaucheNormal){//Rotation avec Mario voyant a Gauche et dans le sol
						this.imgMario = iconMarioSautGaucheNormal.getImage();
						if(imagePrecedente.equals(iconMarioSautGaucheNormal.getImage())){this.imgMario = iconMarioSautGaucheRotationBH.getImage();}
						if(imagePrecedente.equals(iconMarioSautGaucheRotationBH.getImage())){
							this.imgMario = iconMarioSautDroiteInverse.getImage();
							rotationTermine=true; rotationGaucheNormal = false;
						}
					
					}
					if(rotationDroiteInverse){//Rotation avec Mario voyant a Droite et dans le toit
						this.imgMario = iconMarioSautDroiteInverse.getImage();
						if(imagePrecedente.equals(iconMarioSautDroiteInverse.getImage())){this.imgMario = iconMarioSautDroiteRotationHB.getImage();}
						if(imagePrecedente.equals(iconMarioSautDroiteRotationHB.getImage())){
							this.imgMario = iconMarioSautGaucheNormal.getImage();
							rotationTermine=true; rotationDroiteInverse = false;
						}
					}
					if(rotationGaucheInverse){//Rotation avec Mario voyant a Gauche et dans le toit
						this.imgMario = iconMarioSautGaucheInverse.getImage();
						if(imagePrecedente.equals(iconMarioSautGaucheInverse.getImage())){this.imgMario = iconMarioSautGaucheRotationHB.getImage();}
						if(imagePrecedente.equals(iconMarioSautGaucheRotationHB.getImage())){
							this.imgMario = iconMarioSautDroiteNormal.getImage();
							rotationTermine=true; rotationGaucheInverse = false;
						}
					}
					//Sans Rotation en indiquant comment le personnage doit tomber(Pas de chamgement de la gravite)
					if(sansRotationBHdroite){rotationTermine=true;sansRotationBHdroite=false;this.imgMario = iconMarioSautDroiteInverse.getImage();}
					if(sansRotationBHgauche){rotationTermine=true;sansRotationBHgauche=false;this.imgMario = iconMarioSautGaucheInverse.getImage();}
					if(sansRotationHBdroite){rotationTermine=true;sansRotationHBdroite=false;this.imgMario = iconMarioSautDroiteNormal.getImage();}
					if(sansRotationHBgauche){rotationTermine=true;sansRotationHBgauche=false;this.imgMario = iconMarioSautGaucheNormal.getImage();}
					
					
				}else{//Personnage dans l air */
					if(getDx()==0){
						this.imgMario = imagePrecedente;
					}else{
						if(dxDroite==5){
							if(imagePrecedente.equals(iconMarioSautDroiteNormal.getImage()) || imagePrecedente.equals(iconMarioSautGaucheNormal.getImage())){
								this.imgMario = iconMarioSautDroiteNormal.getImage();
							}else{
								this.imgMario = iconMarioSautDroiteInverse.getImage();
						}
					}
						if(dxGauche==-5){
							if(imagePrecedente.equals(iconMarioSautDroiteNormal.getImage()) || imagePrecedente.equals(iconMarioSautGaucheNormal.getImage())){
								this.imgMario = iconMarioSautGaucheNormal.getImage();
							}else{
								this.imgMario =	iconMarioSautGaucheInverse.getImage();
							}
						}
						}
					}
				
			}else if(detecteur[2] || detecteur[3]){ //*/*/*/*/*/*/Mario au Sol*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/
				rotationTermine=false;//On dit que c est possible de faire une rotation 
				if(detecteur[1]==false){//************Sol en Bas***************
					if(getDx()==0){//Si Mario Arret
					if(imagePrecedente.equals(iconMarioSautDroiteNormal.getImage()) || imagePrecedente.equals(iconMarioArretDroiteNormal.getImage()) || imagePrecedente.equals(iconMarioMarcheDroiteNormal.getImage())){
						this.imgMario = iconMarioArretDroiteNormal.getImage();
					}else{
						this.imgMario = iconMarioArretGaucheNormal.getImage();
					}
					
					}else{
					if(dxDroite==5){//Mouvement Vers La Droite
						if(imagePrecedente.equals(iconMarioArretDroiteNormal.getImage())){
							this.imgMario = iconMarioMarcheDroiteNormal.getImage();
						}else{
							this.imgMario = iconMarioArretDroiteNormal.getImage();
							}
						}
					if(dxGauche==-5){//Mouvement Vers La Gauche
						if(imagePrecedente.equals(iconMarioArretGaucheNormal.getImage())){
							this.imgMario = iconMarioMarcheGaucheNormal.getImage();
						}else{
							this.imgMario = iconMarioArretGaucheNormal.getImage();
							}
						}
					}
					}else{//*********Sol en Haut*************
					if(getDx()==0){//Mario Arret
					if(imagePrecedente.equals(iconMarioSautDroiteInverse.getImage()) || imagePrecedente.equals(iconMarioArretDroiteInverse.getImage()) || imagePrecedente.equals(iconMarioMarcheDroiteInverse.getImage())){
						this.imgMario = iconMarioArretDroiteInverse.getImage();
					}else{
						this.imgMario = iconMarioArretGaucheInverse.getImage();
					}
					}else{
						if(dxDroite==5){//Mouvement vers la Droite
						if(imagePrecedente.equals(iconMarioArretDroiteInverse.getImage())){
							this.imgMario = iconMarioMarcheDroiteInverse.getImage();
						}else{
							this.imgMario = iconMarioArretDroiteInverse.getImage();
							}
						}
					if(dxGauche==-5){//Mouvement vers la Gauche
						if(imagePrecedente.equals(iconMarioArretGaucheInverse.getImage())){
							this.imgMario = iconMarioMarcheGaucheInverse.getImage();
						}else{
							this.imgMario = iconMarioArretGaucheInverse.getImage();
							}
						}
					}
					
				}
			}
		
		
	}
	
	
}

