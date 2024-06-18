import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

/** The class Launcher is the main class
 * allowing to launch the entire game.
 */
public class Launcher {
   
	public static void main(String [] args){
		
    	new MenuPlayer();
    	music();
    	
	}
	
	/** The method music allows to 
	 * play a song in background 
	 */
    public static void music() {
    	
       try {
           URL url = Launcher.class.getClassLoader().getResource("background.wav");
           AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
           Clip clip = AudioSystem.getClip();
           clip.open(audioIn);
           while(true){ // Play the song as long as the player plays to the game (i.e, as long as the window is not closed)
               clip.start();
               clip.loop(LOOP_CONTINUOUSLY);              
           }
       }
	   catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			throw new RuntimeException(e);
	   }
       
    }
    
}
