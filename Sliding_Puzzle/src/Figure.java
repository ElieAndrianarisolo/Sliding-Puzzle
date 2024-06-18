import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/** The class Figure allows to create a JButton
 * represented by a cut-out image having coordinates
 */
public class Figure extends JButton implements ActionListener{

	private static final long serialVersionUID = -993761549787689684L;

	// Coordinates of the cut-out picture
	private int posX;
	private int posY;

	// Coordinates of the solution
	private final int solutionPosX;
	private final int solutionPosY;

	private static int dimension; // Dimension of the board (3x3, 4x4, or 5x5)
	
	/** Constructor of the class Figure
	 * @param solPosX : x-coordinate of the solution
	 * @param solPosY : y-coordinate of the solution
	 * @param figure : cut-out image
	 * @param size : dimension of the board (3x3, 4x4, or 5x5)
	 */
	public Figure(int solPosX, int solPosY, ImageIcon figure, int size){
		
		dimension = size;
		this.solutionPosX = solPosX;
		this.solutionPosY = solPosY;
		this.posX = solPosX;
		this.posY = solPosY;
		
		this.setIcon(figure); // The JButton is now represented by the cut-out picture
		this.setPreferredSize(new Dimension(figure.getIconWidth(), figure.getIconHeight())); // Size the picture in such a way as it fits with the level of the game and the dimension of the board
		this.addActionListener(this);
		
	}

	/** The method getPosX gives access to the x-coordinate of a figure
	 * @return the x position of a figure
	 */
	public int getPosX() {
		return posX;
	}

	/** The method setPos set the x-coordinate and y-coordinate of a figure
	 */
	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	/** The method getPosY gives access to the y-coordinate of a figure
	 * @return the y position of a figure
	 */
	public int getPosY() {
		return posY;
	}

	/** The method getSolutionPosX gives access to the x-coordinate of the solution
	 * @return the x position of the solution
	 */
	public int getSolutionPosX() {
		return solutionPosX;
	}

	/** The method getSolutionPosY gives access to the y-coordinate of the solution
	 * @return the y position of the solution
	 */
	public int getSolutionPosY() {
		return solutionPosY;
	}

	/** The method actionPerformed allows to
	 * continuously consider the method move()
	 */
	public void actionPerformed(ActionEvent e) {
		move();
	}

	/** The method move allows to make
	 * move the figures in the board
	 */
	private void move(){
		
		Tile[][] board = Board.board;
		
		try{
			if(board[posX][posY +1].getFigure() == null){ 
				Board.board[posX][posY +1].setFigure(this);
				Board.board[posX][posY].setFigure(null);
				posY ++;
				Puzzle.getBoard().removeAll();
				Puzzle.getBoard().updateBoard();
				checkAnswer();
				Puzzle.addMove();
				return;

			}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(board[posX+1][posY].getFigure() == null){ 
				Board.board[posX+1][posY].setFigure(this);
				Board.board[posX][posY].setFigure(null);
				posX ++;
				Puzzle.getBoard().remover();
				checkAnswer();
				Puzzle.addMove();
				return;

			}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(board[posX-1][posY].getFigure() == null){ 
				Board.board[posX-1][posY].setFigure(this);
				Board.board[posX][posY].setFigure(null);
				posX --;
				Puzzle.getBoard().remover();
				checkAnswer();
				Puzzle.addMove();
				return;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(board[posX][posY-1].getFigure() == null){ 
				Board.board[posX][posY-1].setFigure(this);
				Board.board[posX][posY].setFigure(null);
				posY --;
				Puzzle.getBoard().remover();
				checkAnswer();
				Puzzle.addMove();
				return;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
	}
	
	/** The method checkAnswer allows to check
	 * if the puzzle is solved or not
	 * @return true if the puzzle is solved, false otherwise
	 */
	public static boolean checkAnswer(){
		Figure figure = null;
		for(int i = 0; i<dimension; i++){
			for(int j = 0; j<dimension; j++){
				
				figure = Board.board[i][j].getFigure();
				if(figure == null){
					continue; // Break one iteration in the loop if figure==null, and continues with the next iteration in the loop.
				}
				if(figure.getPosX() != figure.getSolutionPosX() || figure.getPosY() != figure.getSolutionPosY()){
					return false;
				}
			}	
		}
		return true;
	}
}
