import java.util.logging.Level;
import java.util.logging.Logger;
//Uniquement pour jouer la musique de fond
public class TestBackgroundMusic {

    public TestBackgroundMusic(){

        try {
            BackgroundMusic bm = new BackgroundMusic("09 the moon.mp3");
            for(int i=0;i<100;i++){//On repete la musique 100 fois apres on arrete
			bm.start();
            Thread.sleep(96000);//Duration de la musique en ms
            for(int j=0;j<96000;j++){}//Timer
            }
            bm.stop();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

}
