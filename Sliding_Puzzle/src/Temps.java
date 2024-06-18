import java.io.Serializable;

/* The class Temps allows to
 * create a time
 * 
 */
public class Temps implements Serializable{
	
	private static final long serialVersionUID = -899339645263917706L;
	
	// Time units
	private int s;
	private int min;
	private int h;
	
	/** Constructor of the class
	 * Temps
	 */
	public Temps(int heure, int minute, int seconde) {
		s = seconde;
		min = minute;
		h = heure;
	}
	
	/** The method getSec() allows
	 * to get the seconds of a time
	 * @return s : seconds of a time
	 */
	public int getSec() {
		return s;
	}
	
	/** The method setSec() allows 
	 * to change the seconds of a time
	 * @param sec : new seconds of a time
	 */
	public void setSec(int sec) {
		s = sec;
	}
	
	/** The method getMin allows
	 * to get the minutes of a time
	 * @return min : minutes of a time
	 */
	public int getMin() {
		return min;
	}
	
	/** The method setMin() allows 
	 * to change the minutes of a time
	 * @param minute : new minutes of a time
	 */
	public void setMin(int minute) {
		min = minute;
	}
	
	/** The method getHeure allows
	 * to get the hours of a time
	 * @return h : hours of a time
	 */
	public int getHeure() {
		return h;
	}
	
	/** The method setHeure() allows 
	 * to change the hours of a time
	 * @param heure : new hours of a time
	 */
	public void setHeure(int heure) {
		h = heure;
	}
	
	 /** The method toString allows to 
     * display information about a time
     */
	public String toString() {
		return h + " h " + min + " min " + s + " s"; 
	}
	
	/** The method equals allows 
	* to check if 2 times
	* are the same
	* @param Object o : compared time
	*/
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		if (!( o instanceof Temps)){
			return false;
		}
		Temps otherTemps = (Temps) o;
		return (this.s == otherTemps.s) &&
				(this.min == otherTemps.min)&&
				(this.h == otherTemps.h);
	}
	 
	/** The method compareTo allows to
     * compare 2 times
     * @param otherTemps : compared time
     */
	public int compareTo(Temps otherTemps) {
		if ((this.h < otherTemps.h) || 
			((this.h == otherTemps.h) && (this.min < otherTemps.min)) ||
			((this.h == otherTemps.h) && (this.min == otherTemps.min) && (this.s < otherTemps.s))){
				return -1;
		} else if ((this.h > otherTemps.h) || 
					((this.h == otherTemps.h) && (this.min > otherTemps.min)) ||
					((this.h == otherTemps.h) && (this.min == otherTemps.min) && (this.s > otherTemps.s))){
						return 1;
		} else {
			return 0;
		}
	}
}
