import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.*;

/** The class MenuFreeMode allows to create a small
 * window allowing the player to freely choose
 * a picture from their computer and play with it 
 * with a chosen difficulty.
 */
public class MenuFreeMode extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -5806092212738004734L;

	private JPanel main; // Main panel
	private JLabel instruction; // Instruction given to the player
	
	private JButton open; // Button allowing to open the file chooser
	private JButton start; // Start the game once the difficulty and a picture have been chosen by the player

	private JFileChooser filechooser; // Allow to choose a file from the player's computer
	
	// Buttons for the difficulties
	private JRadioButton easy;
    private JRadioButton medium;
    private JRadioButton hard;
	private BufferedImage image = null;
	private ButtonGroup group;

	// Colors
	private final Color FOND = new Color(8, 47, 71);
	private final Color ROSE = new Color(238, 92, 106);

	//Constants
	private final int WIDTH = 300;
	private final int HEIGHT = 360;
	
	// Font
	private Font police;

	/** Constructor of the class MenuFreeMode();
	 */
	public MenuFreeMode(){
		
		this.createPolice(); // Define the Font police
		
		// Creation of the window
        this.setTitle("Free Mode");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		main = new JPanel();
		main.setLayout(new FlowLayout(FlowLayout.CENTER));
		main.setBackground(FOND);
		
		// Display the buttons
		easy = new JRadioButton("Easy");
		easy.setBackground(FOND);
		easy.setFont(police);
		easy.setForeground(Color.WHITE);

		medium = new JRadioButton("Medium");
		medium.setBackground(FOND);
		medium.setFont(police);
		medium.setForeground(Color.WHITE);
	
		hard = new JRadioButton("Hard");
		hard.setBackground(FOND);
		hard.setFont(police);
		hard.setForeground(Color.WHITE);

		group = new ButtonGroup();
		group.add(easy);
		group.add(medium);
		group.add(hard);
		
		// Display the button open
		open = new JButton(new ImageIcon(ImageLoader.loadImage("/default.jpg").getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
		open.setName("Open");
		open.addActionListener(this);
		open.setBackground(ROSE);
		
		// Display the button start
        start = new JButton("Start");
		start.setForeground(Color.BLACK);
		start.setFont(police);
		start.addActionListener(this);
		start.setBackground(ROSE);
	
		// Display the instructions
		instruction = new JLabel("Select an image and difficulty level");
		instruction.setForeground(Color.WHITE);
		instruction.setFont(police);
		
		main.add(instruction);
		main.add(open);

		main.add(easy);
		main.add(medium);
		main.add(hard);

		main.add(start);

		this.add(main);
		this.setVisible(true);
	}

	/** The method actionPerformed allows the player to:
	 * - choose a picture from their computer when they click on the button open
	 * - start the game once they have chosen a picture and a difficulty
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==open){
			
			File workingDirectory = new File("Masterpiece");
			filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(workingDirectory);
			int action = filechooser.showOpenDialog(null); // Create a window opening the file explorer of the player's computer
                if(action == JFileChooser.APPROVE_OPTION){
                    File file = filechooser.getSelectedFile();
                    try {
                        image = ImageIO.read(file);
                        open.setIcon( new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
                        
                    } catch (IOException e1) {
                        System.out.println("You must select an image");
                    }
                }

        }else if(e.getSource()==start){
            
            if(easy.isSelected()){
                Puzzle.changeLevel1();
            }else if(medium.isSelected()){
                Puzzle.changeLevel2();
            }else if(hard.isSelected()){
                Puzzle.changeLevel3();
            }else{
                return;
            }
            
            if(image == null){
                JOptionPane.showMessageDialog(new JPanel(), "You have to select an image", "Error", JOptionPane.INFORMATION_MESSAGE);
				return;
            }
                
            BufferedImage puzzleImage = ImageResizer.resizeImage(image, 674, 674);

            Puzzle.changePuzzle(puzzleImage);
            new Puzzle();
			this.dispose();
		}
		
	}
	
	/** The method createPolice allows
	 * to use a special kind of Font
	 */
	public void createPolice() {
		try {
			police = Font.createFont(Font.TRUETYPE_FONT, new File("Font//font.ttf")).deriveFont(13f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Font//font.ttf")));
		}
		catch(IOException | FontFormatException e) {
			
		}
	}
}

