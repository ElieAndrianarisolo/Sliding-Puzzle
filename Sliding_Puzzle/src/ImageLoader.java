import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/** The class ImageLoader allows
 * to load an image from the player's
 * computer and use it in the game
 */
public class ImageLoader {
	
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
		
	}
}
