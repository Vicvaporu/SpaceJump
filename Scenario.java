import java.util.*;
/*
	La classe se a pour objectifs de créer une matrice Objets(Matrice Espace pour detecteur collision) 
	* en utilisant la liste d´objet Fournie
*/
public class Scenario {
	
	private int[][] matriceObjets = new int[80][164];//Matrice avec les objets du Scenario
	
	LinkedList<Objet> listeObjets = new LinkedList<Objet>();
	
	public Scenario(LinkedList<Objet> listeObjets){
		this.listeObjets = listeObjets;
	
		//On lit la liste d´objets
		for(Objet o : listeObjets){
			if(o.getType() == 2){
				this.addObjetMortel(o);
			}else{
				this.addObjetNonMortel(o);
				
			}
		}
	}
	//Positionner un objet Non Mortel dans sa position en Matrice
	public void addObjetNonMortel(Objet o){
		for(int i = 0; i < o.getLongueur(); i++){
			for(int j = 0; j < o.getHauteur(); j++ ){
				matriceObjets[o.getPosY()+j][o.getPosX()+i] = 1;
			}
		}
				
	}
	//Positioner un objet Mortel dans sa position dans la Matrice des Objets
	public void addObjetMortel(Objet o){
		for(int i = 0; i < o.getLongueur(); i++){
			for(int j = 0; j < o.getHauteur(); j++ ){
				matriceObjets[o.getPosY()+j][o.getPosX()+i] = 2;
			}
		}
				
	}
	
	public String toString(){
		return "Scenario";
	}
	
	
	public int getType(int coord_x,int coord_y){
		return matriceObjets[coord_y][coord_x];
	}
	
	public int[][] getMatriceObjects(){
		return this.matriceObjets;
	}
	
	public LinkedList<Objet> getListeObject(){
		return this.listeObjets;
	}
	

	
}
