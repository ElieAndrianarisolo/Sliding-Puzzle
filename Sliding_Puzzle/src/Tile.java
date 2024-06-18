/** The class Tile allows to 
 * create the tiles in which 
 * are the cut-out pictures
 */
public class Tile {
	
	// Coordinates of the tiles in the board
	private final int X;
	private final int Y;

	private Figure figure; // Button of the cut-out picture
	
	/** Constructor of the class Tile
	 * @param X : x-coordinate of the tile in the board
	 * @param Y : y-coordinate of the tile in the board
	 * @param figure : button of the cut-out picture
	 */
	public Tile(int X, int Y, Figure figure){
		
		this.X = X;
		this.Y = Y;
		this.figure = figure;
		
	}

	/** Constructor of the class Tile leading to a tile without figure
	 * @param X : x-coordinate of the tile in the board
	 * @param Y : y-coordinate of the tile in the board
	 */
	public Tile(int X, int Y){
		
		this.X = X;
		this.Y = Y;
		figure = null;
		
	}

	/** The method getFigure allows to 
	 * have access to the figure of a tile
	 * @return a figure
	 */
	public Figure getFigure(){
		return figure;
	}

	/** The method setFigure allows to 
	 * set the actual figure to a chosen 
	 * figure
	 * @param figure : button of a cut-out picture
	 */
	public void setFigure(Figure figure){
		this.figure = figure;
	}

	/** The method getX allows to
	 * have access to the x-coordinate 
	 * of the tile
	 * @return x-position of the tile in the board
	 */
	public int getX() {
		return X;
	}

	/** The method getX allows to
	 * have access to the y-coordinate 
	 * of the tile
	 * @return y-position of the tile in the board
	 */
	public int getY() {
		return Y;
	}
	
}
