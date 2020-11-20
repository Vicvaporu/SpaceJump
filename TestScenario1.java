import java.util.*;
public class TestScenario1 {
	
	public static void main (String[] args) {
		
		
		Objet o1 = new Objet(0,70,10,163,1);
		Objet o2 = new Objet(50,55,15,10,1);
		Objet o3 = new Objet(4,5,10,20,1);
		Objet o4 = new Objet(50,0,35,10,1);
		Objet o5 = new Objet(75,35,20,5,1);
		Objet o6 = new Objet(90,55,15,40,1);
		Objet o7 = new Objet(90,10,25,30,1);
		Objet o8 = new Objet(130,10,70,34,1);
		
		LinkedList<Objet> liste = new LinkedList<Objet>();
		liste.add(o1);
		liste.add(o2);
		liste.add(o3);
		liste.add(o4);
		liste.add(o5);
		liste.add(o6);
		liste.add(o7);
		liste.add(o8);
		
		Scenario SN1 = new Scenario(liste);
		
		int[][] matObj = SN1.getMatriceObjects();
		for (int i=0; i<matObj.length; i++){
			for (int j=0;j<matObj[i].length;j++){
				 System.out.print(""+matObj[i][j]);
			}
			System.out.print("\n");
		}
		
		
	}
}

