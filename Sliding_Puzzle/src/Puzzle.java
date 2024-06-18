import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;


/** The class Puzzle allows to create a window
 * allowing the player to play to their sliding puzzle.
 * The player has access to a board (on which the tiles
 * are), a timer, a move counter and a small image showing
 * the final picture they have to achieve.
 */
public class Puzzle extends JFrame implements ActionListener{
	

	private static final long serialVersionUID = 9159165537932662801L;
	
	// Panels
	public JPanel mainPanel = new JPanel();
	private static JPanel puzzleArea;
	private JPanel timeMoves;
	private JPanel menu;
	
	// Buttons 
	private JButton home;
	private JButton playStop;
	private JButton rank;
	private JButton shuffle;

	// Image of the home, playStop, shuffle and rank buttons
	private BufferedImage playIcon;
	private BufferedImage stopIcon;
	private BufferedImage homeIcon;
	private BufferedImage shuffleIcon;
	private BufferedImage rankIcon; 
	
	// Final image to obtain
	private static JButton miniImage;
	private static BufferedImage puzzleImageTest;
	private BufferedImage puzzleImage = ImageResizer.resizeImage(puzzleImageTest, 674, 674);

	// Timer
	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;
	private JLabel time = new JLabel(" "+hours+" : "+minutes+" : "+seconds+" ");
	private JLabel timeTitle = new JLabel("Time");
	private static Timer chronometer;
	private final int delay = 1000;

	// Move counter
	private static int moveCount = 0;
	private static JLabel moves = new JLabel(" "+moveCount+" ");
	private JLabel movesTitle = new JLabel("Moves");

	// Board with the tiles
	private static Board board = null;

	// Container
	private static Container container;

	// Difficulty
	private static int level = 0;

	// Text displayed when the game is paused
	private JLabel breakTime;

	// Color
	private final Color FOND = new Color(8, 47, 71);
	private final Color GREY = new Color(251, 252, 251);
	
	// Font
	private Font police;
	private Font policeBreakTime;


	/** Constructor of the class Puzzle
	 */
	public Puzzle(){
		
		this.createPolice(); // Define the Font police
		
		// Creation of the window
		this.setTitle("Sliding Puzzle");
		this.setSize(1000,800);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		container = this.getContentPane();
		
		// Create the timer which activates each second
		chronometer = new Timer(delay, this);

		// Board area
		puzzleArea = new JPanel();
		puzzleArea.setBackground(Color.BLACK);
		puzzleArea.setBounds(20,40,680,680);
		puzzleArea.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 320));
		container.add(puzzleArea);	
			
		// Timer
		timeMoves = new JPanel();
		timeMoves.setBackground(GREY);
		timeMoves.setLayout(null);
		timeMoves.setBounds(720,470,245,250); 
		container.add(timeMoves);

		timeTitle.setFont(police);
		timeTitle.setForeground(Color.BLACK);
		timeTitle.setBounds(77,40,200,40);
		timeMoves.add(timeTitle);

		time.setForeground(Color.BLACK);
		time.setFont(police);
		time.setBounds(45,80,200,40);
		timeMoves.add(time);
		
		// Move counter
		movesTitle.setForeground(Color.BLACK);
		movesTitle.setFont(police);
		movesTitle.setBounds(65,140,200,40);
		timeMoves.add(movesTitle);

		moves.setForeground(Color.BLACK);
		moves.setFont(police);
		moves.setBounds(98,180,200,40);
		timeMoves.add(moves);

		// Panel menu with buttons
		menu = new JPanel();
		menu.setBounds(720,240,245,220); 
		menu.setLayout(null);
		menu.setBackground(FOND);
		container.add(menu);
		
		// Loading of all the button icons
		playIcon = ImageLoader.loadImage("/playIcon.png");
		stopIcon = ImageLoader.loadImage("/stopIcon.png");
		homeIcon = ImageLoader.loadImage("/homeIcon.png");
		shuffleIcon = ImageLoader.loadImage("/shuffleIcon.png");
		rankIcon = ImageLoader.loadImage("/rankIcon.png");	

		// Display of the button playStop
		playStop = new JButton(new ImageIcon(stopIcon.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		playStop.setName("stop");
		playStop.setBackground(GREY);
		playStop.setBounds(15,5,100,100);
		playStop.addActionListener(this);
		menu.add(playStop);
		
		// Display of the button shuffle
		shuffle = new JButton(new ImageIcon(shuffleIcon.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		shuffle.setName("Shuffle");
		shuffle.setBackground(GREY);
		shuffle.setBounds(15,115,100,100);
		shuffle.addActionListener(this);
		menu.add(shuffle);
		
		// Display of the button home
		home = new JButton(new ImageIcon(homeIcon.getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
		home.setName("Home");
		home.setBackground(GREY);
		home.setBounds(130,5,100,100);
		home.addActionListener(this);
		menu.add(home);
		
		// Display of the button rank
		rank = new JButton(new ImageIcon(rankIcon.getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
		rank.setName("Rank");
		rank.setBackground(GREY);
		rank.setBounds(130,115,100,100);
		rank.addActionListener(this);
		menu.add(rank);

		// Display of the final image on the left
		miniImage = new JButton(new ImageIcon(puzzleImageTest.getScaledInstance(245, 190, Image.SCALE_DEFAULT)));
		miniImage.setBounds(720,40,245,190);
		container.add(miniImage);

		Puzzle.start(puzzleImage,level,puzzleImageTest); // Launch the game, the timer and the move counter
		
		mainPanel.setBounds(0, 0, 1000, 800);
		mainPanel.setLayout(null);
		mainPanel.setBackground(FOND);

		// Break time screen
		breakTime = new JLabel("The game is paused");
		breakTime.setForeground(Color.WHITE);
		breakTime.setFont(policeBreakTime);
		breakTime.setBounds(200,240,300,300);
		mainPanel.add(breakTime);

		this.add(mainPanel);
		this.setVisible(true);

	}
	
	/** The method actionPerformed allows the player to:
	 * - pause the game when they click on the button playStop
	 * - open the Home menu when they click on the button home
	 * - shuffle the tiles when they click on the button shuffle
	 * - open the ranking window when they click on the button rank
	 * Besides, this method allows to increment the timer
	 */
	public void actionPerformed(ActionEvent e) {
			
		Object comp = e.getSource();

			if(comp instanceof JButton){ // Check if comp is a JButton
				JButton button = (JButton)comp;
					if(button.getName().equals("stop")){
						if(board != null){
							chronometer.stop();
							button.setIcon(new ImageIcon(playIcon.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
							button.setName("play");
							board.setVisible(false);
							container.add(puzzleArea);
						}
						

					} else if(button.getName().equals("play")){
						if(board != null){
							chronometer.start();
							button.setIcon(new ImageIcon(stopIcon.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
							button.setName("stop");
							board.setVisible(true);
							container.remove(puzzleArea);
						}

					} else if(button.getName().equals("Shuffle")){
						moveCount = 0;
						seconds = 0;
						minutes = 0;
						hours = 0;
						time.setText(" "+hours+" : "+minutes+" : "+seconds+" ");
						moves.setText(" "+moveCount+" ");
						if(board != null){
							board.messBoard();
						}
					} else if(button.getName().equals("Home")){
						moveCount = 0;
						moves.setText(" "+moveCount+" ");
						new MenuLevel();
						this.dispose();
					} else if(button.getName().equals("Rank")){
						moveCount = 0;
						moves.setText(" "+moveCount+" ");
						new MenuRank();
						this.dispose();
					}
					
				} else if(comp instanceof Timer){ // Check if comp is a Timer
				
					seconds ++;
						if(seconds == 60){
							seconds = 0;
							minutes ++;
							if(minutes == 60){
								minutes = 0;
								hours ++;
							}
						}
					time.setText(" "+hours+" : "+minutes+" : "+seconds+" ");
					
					
					if(Figure.checkAnswer() == true){
						
						Puzzle.getTimer().stop();
						moveCount =0;
						Temps chronoFinal = new Temps(hours, minutes, seconds);
						MenuPlayer.setListPlayer(chronoFinal,level);
						
						JOptionPane.showMessageDialog(new JPanel(), "Congratulations", "Puzzle completed", JOptionPane.INFORMATION_MESSAGE);
						moves.setText(" "+moveCount+" ");
						this.dispose();
						new MenuLevel();

					}
			
				}
	}
	

	/** The method getContainer allows to 
	 * have access to the container
	 * @return container
	 */
	public static Container getContainer(){
		return container;
	}

	/** The method addMove allows to increment the move counter
	 */
	public static void addMove(){
		moveCount ++;
		moves.setText(" "+moveCount+" ");
	}

	/** The method getBoard allows to
	 * have access to the Board
	 * @return board
	 */
	public static Board getBoard() {
		return board;
	}

	/** The method getTimer allows
	 * to have access to the Timer
	 */
	public static Timer getTimer(){
		return chronometer;
	}

	/** The method changeLevel1
	 * allows to set the level to 3X3
	 */
	public static void changeLevel1(){
		level = 3;
	}

	/** The method changeLevel2
	 * allows to set the level to 4X4
	 */
	public static void changeLevel2(){
		level = 4;
	}
	
	/** The method changeLevel3
	 * allows to set the level to 5X5
	 */
	public static void changeLevel3(){
		level = 5;
	}

	/** The method changePuzzle
	 * allows to change the image
	 * of the puzzle
	 */
	public static void changePuzzle(BufferedImage img){
		puzzleImageTest = img;
	}


	/** The method start allows to launch the game
	 * @param img : image of the puzzle
	 * @param level : level of the game
	 * @param mini : small picture of the final image of the puzzle
	 */
	public static void start(BufferedImage img, int level, BufferedImage mini){
		
		if(board != null){
			container.remove(board);
		}
		board = new Board(level, img);
		board.setBounds(20,40,680,680);

		chronometer.start();
		container.remove(puzzleArea);
		container.add(board, BorderLayout.WEST);
		container.validate();
		
	}
	
	/** The method getLevel() allows
	 * to get the level of the sliding
	 * puzzle
	 * @return level : level of the game
	 */
	public static int getLevel() {
		return level;
	}
	
	/** The method getLevel() allows
	 * to get the number of moves to 
	 * finish the puzzle
	 * @return moveCount : number of moves to finish the puzzle
	 */
	public static int getMoveCount() {
		return moveCount;
	}
	
	/** The method createPolice allows
	 * to use a special kind of Font
	 */
	public void createPolice() {
		try {
			police = Font.createFont(Font.TRUETYPE_FONT, new File("Font//font.ttf")).deriveFont(35f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Font//font.ttf")));
			
			policeBreakTime= Font.createFont(Font.TRUETYPE_FONT, new File("Font//font.ttf")).deriveFont(31f);
			GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge2.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Font//font.ttf")));
		}
		catch(IOException | FontFormatException e) {
			
		}
	}
	
}
	

