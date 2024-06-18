import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.image.BufferedImage;

/** The class MenuLevel allows to create a window
 * allowing the player to choose the level they 
 * want to start with, but also to open either the 
 * gallery or the Home menu. 
 */
public class MenuLevel extends JFrame implements ActionListener{
    
	private static final long serialVersionUID = 559250474407477807L;

	// Main panel
    private JPanel listLevel;

	// Painting
	private JButton art1;
	private JButton art2;
	private JButton art3;
	private JButton art4;
    
	// Home button
    private JButton home;

    //Colors
    private final Color FOND = new Color(8, 47, 71);
	
	// Constants
	private final int WIDTH = 1000;
	private final int HEIGHT = 800;
	private final int ART_SIZEX = 220;
	private final int ART_SIZEY = 220;

	
	// Background
	private BufferedImage background;
	private JLabel back;

	/** Constructor of the class MenuLevel
	 */
    public MenuLevel(){
    	
    	// Creation of the window 
		this.setTitle("Home");
        this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		listLevel = new JPanel();
		listLevel.setBounds(0, 0, WIDTH, HEIGHT);
		listLevel.setLayout(null);
		listLevel.setBackground(FOND);

		// Display of the level 1
		art1 = new JButton(new ImageIcon(ImageLoader.loadImage("/Sunflowers-Van_Gogh.jpg").getScaledInstance(220, 220, Image.SCALE_DEFAULT)));
		art1.setBounds(30,180,ART_SIZEX,ART_SIZEY);
		art1.addActionListener(this);
		listLevel.add(art1);

		// Display of the level 2
		art2 = new JButton(new ImageIcon(ImageLoader.loadImage("/The_Great_Wave_of_Kanagawa-Hokusai.jpg").getScaledInstance(220, 220, Image.SCALE_DEFAULT)));
		art2.setBounds(270,180,ART_SIZEX,ART_SIZEY);
		art2.addActionListener(this);
		listLevel.add(art2);
		
		// Display of the level 3
		art3 = new JButton(new ImageIcon(ImageLoader.loadImage("/Morning_Sun-Edward_Hopper.jpeg").getScaledInstance(220, 220, Image.SCALE_DEFAULT)));
		art3.setBounds(510,180,ART_SIZEX,ART_SIZEY);
		art3.addActionListener(this);
		listLevel.add(art3);

		// Display of the free mode
		art4 = new JButton(new ImageIcon(ImageLoader.loadImage("/Musee.jpg").getScaledInstance(220, 220, Image.SCALE_DEFAULT)));
		art4.setBounds(750,180,ART_SIZEX,ART_SIZEY);
		art4.addActionListener(this);
		listLevel.add(art4);
	
		// Display of the home button
		home = new JButton();
		home.setBounds(420, 730, 100, 100);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.addActionListener(this);
		listLevel.add(home);
		
		// Display of the background
		background = ImageLoader.loadImage("/fond2_final.png");
		back = new JLabel(new ImageIcon(background));
		back.setBounds(0, 0, WIDTH, HEIGHT);
		back.setLayout(null);
		listLevel.add(back);
		
		this.add(listLevel);
		this.setVisible(true);
    }
	

	/** The method actionPerformed allows the player to:
	 * - go back to the Home menu when they click on the button home
	 * - open the gallery when they click on the button galleryTitle
	 * - play to the level 1 of the game when they click on the button Play1
	 * - play to the level 2 of the game when they click on the button Play2
	 * - play to the level 3 of the game when they click on the button Play3
	 * - play to the free mode of the game when they click on the button play4
	 */
    public void actionPerformed (ActionEvent e){

        if(e.getSource() == home) {
            new MenuPlayer();
            this.dispose();
        }else if (e.getSource() == art4) {
            new MenuFreeMode();
            this.dispose();
        }else if (e.getSource() == art1) {
            Puzzle.changePuzzle(ImageLoader.loadImage("/Sunflowers-Van_Gogh.jpg"));
			Puzzle.changeLevel1();
			new Puzzle();
			this.dispose();
        } else if (e.getSource() == art2) {
            Puzzle.changePuzzle(ImageLoader.loadImage("/The_Great_Wave_of_Kanagawa-Hokusai.jpg"));
			Puzzle.changeLevel2();
			new Puzzle();
			this.dispose();
        } else if (e.getSource() == art3) {
            Puzzle.changePuzzle(ImageLoader.loadImage("/Morning_Sun-Edward_Hopper.jpeg"));
			Puzzle.changeLevel3();
			new Puzzle();
			this.dispose();
        } 

    }
	
}
