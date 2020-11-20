/*
 * La classe Objet se concentre en definir un Objet
 * Cette objet aurra une position dans la matrices des Objets
 * et On indique aussi sa taille en nombre des cases Matriciels
 * De même on indique ici quel "type" d´objet on a (Type:)
 * 1-Objet Non Mortel / Normal
 * 2-Objet Mortel
 * 3-Objet Victoire Trophée
 * Oui, on aurrait pû réaliser un Heritage :(
 */

public class Objet{
    private int longueur;
    private int hauteur;
    private int posX;
    private int type;
    private int posY;
    private int[][] vraieObjet;
    
    
    //Constructeur de object
    //EXPLIQUER LA CHOIX DES ATTRIBUTS DU CONSTRUCTEUR
    public Objet(int posX, int posY,int haut, int longue, int type){
        this.posX= posX;
        this.posY = posY;
        hauteur = haut;
        longueur = longue;
        this.type = type;
        int [][] vraieObjet = new int[haut][longue];
    }
    
    //Definition des getteurs
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public int getHauteur(){
        return hauteur;
    }
    public int getLongueur(){
        return longueur;
    }
    public int getType(){
        return type;
    }
    public int[][] getVraieObjet(){
        return vraieObjet;
    }
 
    //Comparation des Objets
    public boolean equals(Objet o){
        if (o==null) return false;
        if((this.posX==o.posX)&&(this.posY==o.posY)&&(this.hauteur==o.hauteur)&&(this.longueur==o.longueur)){
                    return true;
        }else{
            return false;
        }
    }
}
