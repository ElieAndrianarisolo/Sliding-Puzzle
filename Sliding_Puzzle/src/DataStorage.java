import java.io.Serializable;
import java.util.*;

/** The class DataStorage allows to
 * create a backup of a list of players
 * with their name and time
 */
public class DataStorage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	LinkedList<Profile> listPlayers = new LinkedList<Profile>();
}
