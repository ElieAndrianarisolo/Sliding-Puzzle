import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/** The class Board allows to
 * set the board on which the tiles
 * are when the player plays to the 
 * game.
 */
public class Board extends JPanel{


	private static final long serialVersionUID = 4762695402574413686L;
	
	// Arrays forming the board
	public static Tile[][] board;
	private ArrayList<Tile> completeBoard = new ArrayList<Tile>(); 
	
	// Coordinates in the board
	private int x;
	private int y;
	private JLabel label;

	// Color
	private final Color BLUE = new Color(91, 167, 180);

	// Constants
	public final int DIMENSION; // Dimension of the board (3x3, 4x4, or 5x5)
	private final int FIGURE_WIDTH; // Width of the figure
	private final int FIGURE_HEIGHT; // Height of the figure

	/** Constructor of the class Board
	 * @param dimension : dimension of the board (3x3, 4x4, or 5x5)
	 * @param puzzle : image of the puzzle
	 */
	public Board(int dimension, BufferedImage puzzle){
		
		this.setPreferredSize(new Dimension(410, 0));
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
		this.setBackground(BLUE);
		this.DIMENSION = dimension;

		board = new Tile[DIMENSION][DIMENSION];
		x = 0;
		y = 0;
		FIGURE_WIDTH = puzzle.getWidth()/DIMENSION;
		FIGURE_HEIGHT = puzzle.getHeight()/DIMENSION;

		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

			for(int i=0; i<DIMENSION; i++){
				for(int j=0; j<DIMENSION; j++){
					if(i == DIMENSION - 1 && j == DIMENSION -1){			
						continue;
					}
					completeBoard.add(new Tile(i, j, new Figure(i, j, new ImageIcon(puzzle.getSubimage(x, y, FIGURE_WIDTH, FIGURE_HEIGHT)), DIMENSION)));
					
					x += FIGURE_WIDTH;
				}
				x = 0;
				y += FIGURE_HEIGHT;
			}

		messBoard();
		remover();
		
	}

	/** The method messBoard allows 
	 * the tiles to be shuffled
	 */
	public void messBoard(){

		Random randomGenerator = new Random();
		ArrayList<Tile> TileStore = new ArrayList<Tile>(completeBoard);
		
			for(int i = 0; i<DIMENSION; i++){
				for(int j = 0; j<DIMENSION; j++){
					if(i == DIMENSION-1 && j == DIMENSION-1){
						board[i][j] = new Tile(i, j);
						continue;
					}
					int randomIndex = randomGenerator.nextInt(completeBoard.size());
					completeBoard.get(randomIndex).getFigure().setPos(i, j);
					board[i][j] = new Tile(i, j, completeBoard.get(randomIndex).getFigure());
					completeBoard.remove(randomIndex);

				}
			}

		completeBoard = TileStore;
		remover();
		
	}

	/** The method updateBoard allows
	 * to update the board each time
	 * the player plays
	 */
	public void updateBoard(){
		
		for(int i = 0; i<DIMENSION; i++){
			for(int j = 0; j<DIMENSION; j++){	
				if(board[i][j].getFigure() == null){
					label = new JLabel();
					label.setPreferredSize(new Dimension(FIGURE_WIDTH, FIGURE_HEIGHT));
					this.add(label);
					continue;
				}
				this.add(board[i][j].getFigure());
			}
		}
		Puzzle.getContainer().validate();

	}
	
	/** The method remover allows
	 * to remove all the elements 
	 * from the board and then update them
	 * each time the player plays
	 */
	public void remover(){
		
		this.removeAll();
		updateBoard();

	}

}
