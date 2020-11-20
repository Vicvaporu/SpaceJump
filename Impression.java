import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
/*
	*Impression se charge uniquement d´imprimer les objets "Blocs" du Scenario (Pas les Personnage)
	* Elle lit une liste d´objets et elle cherche des les imprimer chaque objet de la liste dans la fenêtre
*/
public class Impression extends JPanel implements ActionListener{
    
    private LinkedList<Objet> uneListe;  //Liste d´objets a Imprimer
   
    private ImageIcon iconBlocNormal;//Bloc Normal qui est seulement un obstacle
    private Image imageBlocNormal;
    
    private ImageIcon iconBlocMortel;//Bloc Mortel qui peut tuer le personnage
    private Image imageBlocMortel;
   
    private ImageIcon iconTrophy;//Bloc Trophé qui donne la Victoir
    private Image imageTrophy;

    
    public Impression(LinkedList<Objet> uneListe){
        this.uneListe = uneListe;
        
        iconBlocNormal = new ImageIcon(getClass().getResource("/Ressources_Mario/images/BlocNormalSmall.png"));
        this.imageBlocNormal=this.iconBlocNormal.getImage();
        
        iconBlocMortel = new ImageIcon(getClass().getResource("/Ressources_Mario/images/BlocMortelSmall.png"));      
        this.imageBlocMortel=this.iconBlocMortel.getImage();
       
        iconTrophy = new ImageIcon(getClass().getResource("/Ressources_Mario/images/TrophyGood.png"));
        this.imageTrophy=this.iconTrophy.getImage();
    }
    //Setters
    public void setListeObjets(LinkedList<Objet> uneAutreListe){this.uneListe = uneAutreListe;}
    
    
    //Pour Impression des Objets du Scenario
    public void paintBloc(Graphics g){
		
		for(Objet o: uneListe){
			if(o.getType()== 1){//Impression Bloc Normal
				int longLimite = 0;
				while(longLimite<convertirXpixel(o.getLongueur())){
					g.drawImage(this.imageBlocNormal,convertirXpixel(o.getPosX())+longLimite,convertirYpixel(o.getPosY()),null);//(image, posX, posY, angle)
					int hautLimite = 0;
					while(hautLimite<convertirYpixel(o.getHauteur())){
						g.drawImage(this.imageBlocNormal,convertirXpixel(o.getPosX())+longLimite,convertirYpixel(o.getPosY())+hautLimite,null);//(image, posX, posY, angle)
						hautLimite = hautLimite + 30;// 30 Hauteur Objet en Pixel
					}
					longLimite = longLimite + 30;// 30 Largeur Objet en Pixel
				}
			}
			if(o.getType() == 2){//Impression Bloc Mortel
				int longLimite = 0;
				while(longLimite<convertirXpixel(o.getLongueur())){
					g.drawImage(this.imageBlocMortel,convertirXpixel(o.getPosX())+longLimite,convertirYpixel(o.getPosY()),null);//(image, posX, posY, angle)
					int hautLimite = 0;
					while(hautLimite<convertirYpixel(o.getHauteur())){
						g.drawImage(this.imageBlocMortel,convertirXpixel(o.getPosX())+longLimite,convertirYpixel(o.getPosY())+hautLimite,null);//(image, posX, posY, angle)
						hautLimite = hautLimite + 30;
					}
					longLimite = longLimite + 30;
				}
			}
			if(o.getType() == 3){// Impression Trophée Victoire
				int longLimite = 0;
				while(longLimite<convertirXpixel(o.getLongueur())){
					g.drawImage(this.imageTrophy,convertirXpixel(o.getPosX())+longLimite,convertirYpixel(o.getPosY()),null);//(image, posX, posY, angle)
					int hautLimite = 0;
					while(hautLimite<convertirYpixel(o.getHauteur())){
						g.drawImage(this.imageTrophy,convertirXpixel(o.getPosX())+longLimite,convertirYpixel(o.getPosY())+hautLimite,null);//(image, posX, posY, angle)
						hautLimite = hautLimite + 30;
					}
					longLimite = longLimite + 30;
				}
			}	
					
        }
        
	}
    
    public void actionPerformed(ActionEvent e) {//Pour Imprimer
		repaint();
	}
    
    //Convertiseur de Coord Matrice en pixel
	public int convertirXpixel(int x){
		return (x*5);
	}
	
	public int convertirYpixel(int y){
		return (y*5);
	}

}
