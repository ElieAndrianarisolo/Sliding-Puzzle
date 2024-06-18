import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

/** The class MenuRank allows the player
 * to have access to a ranking of all
 * the players that have played to the
 * game so far.
 */
public class MenuRank extends JFrame implements ActionListener{
    
	private static final long serialVersionUID = -1902250361673902021L;

	private JPanel menuRank; // main panel
	private JPanel area;
	private JPanel area2;
	private JTextArea rankList; // Text area for the rank list
	private JTextArea chronoList;

	// Color
    private final Color FOND = new Color(8, 47, 71);
    
    // Home button
    private JButton home;
    
    // Constants
    private final int WIDTH = 1000;
	private final int HEIGHT = 800;
	
	// List of players and ranking
	private LinkedList<Profile> listPlayers =  MenuPlayer.getListPlayer(Puzzle.getLevel());
	private String ranking = MenuPlayer.getFinalRanking(listPlayers);
	private String chrono = MenuPlayer.getFinalChrono(listPlayers);
	
	// Font
    private Font police;

    // Background
	private BufferedImage background;
	private JLabel back;

    /** Constructor of the class MenuRank
     */
    public MenuRank(){
    	
    	this.createPolice(); // Define the font police
    	
    	// Creation of the window 
        this.setTitle("Ranking");
        this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		
        menuRank = new JPanel();
        menuRank.setBounds(0, 0, WIDTH, HEIGHT);
        menuRank.setLayout(new GridBagLayout());
        
        // Creation of a panel to display the rank and the players' names
		area = new JPanel();
        area.setBounds(260, 200, 450, 400);
		area.setBackground(FOND);
		
		// Creation of a panel to display the time of each player
		area2 = new JPanel();
        area2.setBounds(550, 200, 250, 400);
		area2.setBackground(FOND);

		// Display the home button
		home = new JButton();
		home.setBounds(420, 730, 100, 100);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.addActionListener(this);
		menuRank.add(home);

		// Display the rank and the player's name
		rankList = new JTextArea();
        rankList.setLineWrap(true);
        rankList.setBounds(260, 200, 450, 400);
		rankList.setBackground(FOND);
		rankList.setForeground(Color.WHITE);
		rankList.setFont(police);
		rankList.append(ranking);
		area.add(rankList);

		// Display the time of each player
		chronoList = new JTextArea();
        chronoList.setLineWrap(true);
        chronoList.setBounds(550, 200, 250, 400);
		chronoList.setBackground(FOND);
		chronoList.setForeground(Color.WHITE);
		chronoList.setFont(police);
		chronoList.append(chrono);
		area2.add(chronoList);
		
		// Display the background
		background = ImageLoader.loadImage("/ranking_test.png");
		back = new JLabel(new ImageIcon(background));
		back.setBounds(0, 0, WIDTH, HEIGHT);
		back.setLayout(null);
		menuRank.add(back);

		this.add(area2);
		this.add(area);
		this.add(home);
        this.add(menuRank);
        this.setVisible(true);
    }
    
    /** The method actionPerformed allows the player to
	 * go back to the Home menu when they click on the 
	 * button home
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == home) {
            new MenuLevel();
            this.dispose(); 
        }
	}
	
	/** The method createPolice allows
	 * to use a special kind of Font
	 */
	public void createPolice() {
		try {
			police = Font.createFont(Font.TRUETYPE_FONT, new File("Font//font.ttf")).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Font//font.ttf")));
		}
		catch(IOException | FontFormatException e) {
			
		}
	}
   
}

