import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
/*
	*Cette classe se concentre dans le mouvement du personnage.
	* Il utilise un Linked List pour garder les buttons sont appuyés par l´utilisateur
	* De cette manière on arrive a faire une combinaison des buttons
*/
public class EcouterClavier implements KeyListener {
	private Personnage personnage;
	private Scene scene;
	private DetecteurCollision detecteur;
	private boolean[] numeroReturn;
	LinkedList<KeyEvent> listeButtons = new LinkedList<KeyEvent>();
	//Code pour chaque button
	final int droite = 39;
	final int gauche = 37;
	final int space = 32;
	
	//Compteur pour suivre les nombre des fois qu on appuie Space
	//Ce nombre nous permettra de savoir le sens de la gravite
	int compteurSpace=0;
	
	//Savoir si on a appuyer un button une fois
	boolean appuyerSpace = false;
	boolean appuyerRight = false;
	boolean appuyerLeft = false;
	
	public EcouterClavier(Scene scene, Personnage bob){
		this.personnage=bob;
		this.scene=scene;
		this.detecteur=scene.getDetecteurCollision();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		
		numeroReturn=scene.getNumeroReturn();//La Classe a besoin de savoir si on a un objet en haut ou en bas de mario
											//car Mario va pouvoir changer la gravité si il est dans le sol ou le toit
		
		//Les trois premiers conditions vont nous permettre de "faire de combinaison de buttons"
		//Je voulais un peut ameliorer la experience du joueur en lui permettant d appuyer plusieurs
		//buttons a la fois.Neamoins c est pas tres efficace cette methode. Le resultat est acceptable.
		//Les trois derniers conditions indique ce qui se passe si on appuie sur chaque button
		//*****On garde les buttons que le joueur a appuyer*****
		if(this.buttonActive(droite) && this.buttonActive(gauche) && appuyerRight && appuyerLeft){
			(this.personnage).setDxDroite(5);
			(this.personnage).setDxGauche(-5);
		}else if(this.buttonActive(space) && this.buttonActive(droite) && appuyerRight && appuyerSpace){
			(this.personnage).setDxDroite(5);
			
		}else if(this.buttonActive(space) && this.buttonActive(gauche) && appuyerLeft && appuyerSpace){
			(this.personnage).setDxGauche(-5);
			
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			appuyerRight=true;
			(this.personnage).setDxDroite(5);
			if(appuyerRight){listeButtons.addFirst(e);}
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			appuyerLeft=true;
			(this.personnage).setDxGauche(-5);
			if(appuyerLeft){listeButtons.addFirst(e);}
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			appuyerSpace=true;
			if(numeroReturn[0]==false || numeroReturn[1]==false){compteurSpace++;}//Le compteur nous indique le sens de la gravité 
																		//Et si on a change la gravite
																		//Ici on voit si le joueur est dans la surface d un objet pour changer la gravite
																		//(En haut d un objet ou en bas d un objet)
			if(appuyerSpace){listeButtons.addFirst(e);}
		}	
	}
 
	@Override
	//Ici on enleve les buttons apres relacher les buttons
	//De meme on indique que le deplacment elementaire est a zero donc pas de mouvement dans le sens du button appuye
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			(this.personnage).setDxDroite(0);
			listeButtons.remove(e);
			appuyerRight=false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			(this.personnage).setDxGauche(0);
			listeButtons.remove(e);
			appuyerLeft=false;
		}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
			appuyerSpace=false;
			listeButtons.remove(e);
		}
	}
		
	@Override
	public void keyTyped(KeyEvent e){}//Dunnot LOL
	
	
	//Methode qui nous indique si un button est appuye
	public boolean buttonActive(int keyCode){
		boolean condition= false;
		for(int i=0;i<listeButtons.size();i++){
			if((listeButtons.get(i)).getKeyCode() == keyCode){
				condition=true;
			}
		}
		return condition;
	}
	
	//Voir quels buttons cont actives
	public boolean[] tableauButtonActive(){
		boolean []button = new boolean[3];
		for(int i=0;i<button.length;i++){button[i]=false;}
		if(buttonActive(gauche)){button[0]=true;}
		if(buttonActive(droite)){button[1]=true;}
		if(buttonActive(space)){button[2]=true;}
		return button;
	}
	
	//Obtenir le dernier button(Gauche/Droite)*Space exclut*
	public int getLastButton(){
		LinkedList<KeyEvent> liste = listeButtons;
		int numeroButton = 0;//0 Si il y a pas de dernier button
		boolean trouver=false;
		for(int i=0;i<liste.size();i++){
			if(liste.peekLast()!=null){
				if((liste.peekLast()).getKeyCode()==droite){
					numeroButton=droite;
					trouver=true;
				}else if(liste.peekLast().getKeyCode()==gauche){
					numeroButton=gauche;
					trouver=true;
				}
			liste.removeLast();
			}
		}
		return numeroButton;
	}
	
	
	
	//Getters
	public LinkedList<KeyEvent> getListeButton(){return listeButtons;}
	public int getCompteurSpace(){return compteurSpace;};
	
	}
 

