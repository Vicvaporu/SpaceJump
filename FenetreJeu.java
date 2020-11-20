import java.awt.*;
import javax.swing.*;
import java.util.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.*;
import javax.swing.Timer;
/*
	On declare la fenetre. Sa taille et on declare l´objet Scene
*/
public class FenetreJeu extends JFrame{
	public static Scene scene;
	
	public final int xInitial=820;
	public final int yInitial=400;
	
	public FenetreJeu(){
		super("Jeu Magique");
		setSize(xInitial,yInitial);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Instatiation Scene
		scene=new Scene(this);
		
		add(scene);
		setVisible(true);
	}
	
	//Getters
	public int getXinitial(){return xInitial;}
	public int getYinitial(){return yInitial;}
	public double getXnouveau(){return this.getWidth();}//Uniquement un choix de commodite pour l ecriture :p
	public double getYnoveau(){return this.getHeight();}//************************************************ :D
	
}

