import java.io.Serializable; 

/** The class Profile allows to create
 * the profile of the players
 */
public class Profile implements Serializable, Comparable<Profile> {
    
	private static final long serialVersionUID = -7051861837651155841L;
	
	private String name;
	private Temps chrono = new Temps(0,0,0);
    
	/** Constructor of the class Profile if 
	 * the player already have a time
	 * @param id : name of the player
	 * @param time : time of the player
	 */
    public Profile(String id, Temps time){
        name=id;
        chrono = time;
    }
    
    /** Constructor of the class Profile if 
	 * the player doesn't have a time yet
	 * @param id : name of the player
	 * @param time : time of the player
	 */
    public Profile(String id){
        name=id;
    }
    
    /** The method getName allows
     * to get the name of a player
     * @return name : name of the player
     */
    public String getName(){
        return name;
    }
    
    /* The method Temps allows
     * to get the time of a
     * player
     * @return chrono : time of a player
     */
    public Temps getChrono() {
    	return chrono;
    }
    
    /** The method setName allows
     * to change the name of a
     * player
     * @param newName : new name of a player
     */
    public void setName(String newName) {
    	name = newName;
    }
    
    /** The method setChrono allows
     * to change the time of a player
     * @param newChrono : new time of a player
     */
    public void setChrono(Temps newChrono) {
    	chrono = newChrono;
    }
    
    /** The method equals allows 
     * to check if 2 players
     * are the same
     * @param Object o : compared player
     */
    public boolean equals(Object o) {
    	if(o==null) {
    		return false;
    	}
    	if (!( o instanceof Profile)){
    		return false;
    	}
    	Profile otherProfile = (Profile) o;
    	return (this.name.equals(otherProfile.name));
    }
    
    /** The method compareTo allows to
     * compare 2 players as a function of 
     * their time
     * @param otherProfile : compared player
     */
    public int compareTo(Profile otherProfile) {
    	return chrono.compareTo(otherProfile.chrono);
    }
    
    /** The method toString allows to 
     * display information about a player
     */
    public String toString() {
    	Temps reference = new Temps(0,0,0);
    	if(chrono.compareTo(reference) == 0){
    		return "The player named " + name +" doesn't have a time yet ";
    	} else if ((chrono.compareTo(reference) != 0) && (Puzzle.getLevel()==0)) {
    		return "The player is named " + name;
    	} else {
    		return name + "\n";
    	}
    }

    public String chrono() {
    	Temps reference = new Temps(0,0,0);
    	if(chrono.compareTo(reference) == 0){
    		return "The player named " + name +" doesn't have a time yet ";
    	} else if ((chrono.compareTo(reference) != 0) && (Puzzle.getLevel()==0)) {
    		return "The player is named " + name;
    	} else {
    		return chrono + "\n";
    	}
    }
}