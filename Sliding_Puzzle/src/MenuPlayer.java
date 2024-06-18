import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;



/** The class MenuPlayer to create a window
 * allowing the player to choose if they want 
 * to play with a player's name that will be entered 
 * or that have already been entered by them in a 
 * previous game. Besides, the player can also decide 
 * to play as a guest.
 */
public class MenuPlayer extends JFrame implements ActionListener {


	private static final long serialVersionUID = -3402135640677335595L;
	
	// Panels
	private JPanel menuPrincipal;

	// Buttons
    private JButton newPlayer;
    private JButton existingPlayer;
    private JButton guestPlayer;

	// Constants
    private final int WIDTH = 1000;
	private final int HEIGHT = 800;
    private final int BUTTON_WIDTH =(int)(0.26*WIDTH);
	private final int BUTTON_HEIGHT = (int)(0.13*HEIGHT);
    
	// Color
    private final Color FOND = new Color(8, 47, 71);
	
	private static String playerName;
	private static Profile playerNameProfile; // name of the player
    private static LinkedList<Profile> listPlayers = new LinkedList<Profile>(); // List made of the players from level 3 x 3
    private static LinkedList<Profile> listPlayers2 = new LinkedList<Profile>(); // List made of the players from level 4 x 4
    private static LinkedList<Profile> listPlayers3 = new LinkedList<Profile>(); // List made of the players from level 5 x 5
    private static int indexOfMainPlayer;

    // Background
	private BufferedImage background;
	private JLabel back;
	
	/** Constructor of the class MenuPlayer
 	*/ 
    public MenuPlayer(){
    	
		// Creation of the window 
		this.setTitle("Home");
        this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuPrincipal = new JPanel();
		menuPrincipal.setBounds(0, 0, WIDTH, HEIGHT);
		menuPrincipal.setLayout(null);
		menuPrincipal.setBackground(FOND);

        // Display of the 3 buttons
		newPlayer = new JButton();
		newPlayer.setBounds(398, 352, BUTTON_WIDTH, BUTTON_HEIGHT);
		newPlayer.setOpaque(false);
		newPlayer.setContentAreaFilled(false);
		newPlayer.setBorderPainted(false);
		newPlayer.addActionListener(this);
		menuPrincipal.add(newPlayer);
		
		
		existingPlayer = new JButton();
		existingPlayer.setBounds(375, 460, BUTTON_WIDTH, BUTTON_HEIGHT);
		existingPlayer.setOpaque(false);
		existingPlayer.setContentAreaFilled(false);
		existingPlayer.setBorderPainted(false);
		existingPlayer.addActionListener(this);
		menuPrincipal.add(existingPlayer);
		
		
		guestPlayer = new JButton();
		guestPlayer.setBounds(390, 560, BUTTON_WIDTH, BUTTON_HEIGHT);
		guestPlayer.setOpaque(false);
		guestPlayer.setContentAreaFilled(false);
		guestPlayer.setBorderPainted(false);
		guestPlayer.addActionListener(this);
		menuPrincipal.add(guestPlayer);
		
		// Display of the background
		background = ImageLoader.loadImage("/fond_final.png");
		back = new JLabel(new ImageIcon(background));
		back.setBounds(0, 0, WIDTH, HEIGHT);
		back.setLayout(null);
		menuPrincipal.add(back);
		
		this.add(menuPrincipal);
		this.setVisible(true);
    
	}

	/** The method actionPerformed allows the player to:
	 * - add a player's name when they click on the button newPlayer
	 * - use an existing player's name when they click on the button existingPlayer
	 * - play as guest player when they click on the button GuestPlayerss
	 */
	public void actionPerformed (ActionEvent e){
		
		if (e.getSource() == newPlayer){ // If a player clicks on the button newPlayer
			
			playerName = JOptionPane.showInputDialog("Enter your player's name"); 
			
			if(playerName != null) {
				
				playerNameProfile = new Profile(playerName);
				
				try { // Read the backups
					
					FileInputStream fis1 = new FileInputStream("save1.dat");
					BufferedInputStream bis1 = new BufferedInputStream(fis1);
					ObjectInputStream ois1 = new ObjectInputStream(bis1);
					DataStorage dStorage1 = (DataStorage)ois1.readObject();			
					listPlayers = dStorage1.listPlayers;
					ois1.close();
					
					FileInputStream fis2 = new FileInputStream("save2.dat");
					BufferedInputStream bis2 = new BufferedInputStream(fis2);
					ObjectInputStream ois2 = new ObjectInputStream(bis2);
					DataStorage dStorage2 = (DataStorage)ois2.readObject();			
					listPlayers2 = dStorage2.listPlayers;
					ois2.close();
					
					FileInputStream fis3 = new FileInputStream("save3.dat");
					BufferedInputStream bis3 = new BufferedInputStream(fis3);
					ObjectInputStream ois3 = new ObjectInputStream(bis3);
					DataStorage dStorage3 = (DataStorage)ois3.readObject();			
					listPlayers3 = dStorage3.listPlayers;
					ois3.close();
					
				}
				
				catch(IOException ex) {
					
				} 
				catch(ClassNotFoundException a) {
					
				}
				
				try { // Rewrite the backups
		
					FileOutputStream fos1 = new FileOutputStream("BACKUP//save1.dat");
					BufferedOutputStream bos1 = new BufferedOutputStream(fos1);
					try (ObjectOutputStream oos1 = new ObjectOutputStream(bos1)) {
						DataStorage dStorage21 = new DataStorage();
						
						FileOutputStream fos2 = new FileOutputStream("BACKUP//save2.dat");
						BufferedOutputStream bos2 = new BufferedOutputStream(fos2);
						try (ObjectOutputStream oos2 = new ObjectOutputStream(bos2)) {
							DataStorage dStorage22 = new DataStorage();
							
							FileOutputStream fos3 = new FileOutputStream("BACKUP//save3.dat");
							BufferedOutputStream bos3 = new BufferedOutputStream(fos3);
							try (ObjectOutputStream oos3 = new ObjectOutputStream(bos3)) {
								DataStorage dStorage23 = new DataStorage();
								
									for(Profile profileTest : listPlayers) { // Check if the player's name is already used
										if(profileTest.equals(playerNameProfile)) {
											JOptionPane.showMessageDialog(new JPanel(), "This player's name already exists", "Error", JOptionPane.INFORMATION_MESSAGE);
											return;
										}
									}

								listPlayers.add(playerNameProfile);
								listPlayers2.add(playerNameProfile);
								listPlayers3.add(playerNameProfile);
								
								dStorage21.listPlayers = listPlayers;
								oos1.writeObject(dStorage21);
								oos1.close();
								
								dStorage22.listPlayers = listPlayers2;
								oos2.writeObject(dStorage22);
								oos2.close();
								
								dStorage23.listPlayers = listPlayers3;
								oos3.writeObject(dStorage23);
								oos3.close();
							}
						}
					} catch (HeadlessException e1) {

					}
					
				}catch(IOException ex) {
					
				}
				
				indexOfMainPlayer = listPlayers.indexOf(playerNameProfile);
				
				this.setVisible(false);
				new MenuLevel();
			}
			
		}else if(e.getSource()== existingPlayer){ // If a player clicks on the button existingPlayer
			
			try { // Read the backups
				
				FileInputStream fis1 = new FileInputStream("BACKUP//save1.dat");
				BufferedInputStream bis1 = new BufferedInputStream(fis1);
				ObjectInputStream ois1 = new ObjectInputStream(bis1);
				DataStorage dStorage1 = (DataStorage)ois1.readObject();			
				listPlayers = dStorage1.listPlayers;
				ois1.close();
				
				FileInputStream fis2 = new FileInputStream("BACKUP//save2.dat");
				BufferedInputStream bis2 = new BufferedInputStream(fis2);
				ObjectInputStream ois2 = new ObjectInputStream(bis2);
				DataStorage dStorage2 = (DataStorage)ois2.readObject();			
				listPlayers2 = dStorage2.listPlayers;
				ois2.close();
				
				FileInputStream fis3 = new FileInputStream("BACKUP//save3.dat");
				BufferedInputStream bis3 = new BufferedInputStream(fis3);
				ObjectInputStream ois3 = new ObjectInputStream(bis3);
				DataStorage dStorage3 = (DataStorage)ois3.readObject();			
				listPlayers3 = dStorage3.listPlayers;
				ois3.close();

			}
			
			catch(IOException ex) {
				
			}catch(ClassNotFoundException a) {
				
			}
			
			playerName = JOptionPane.showInputDialog("Enter the existing player's name"); 
			
			if(playerName != null) {

				playerNameProfile = new Profile(playerName);
				for(Profile profileTest : listPlayers) {
					if(profileTest.equals(playerNameProfile)) {
							for(Profile profileTest2 : listPlayers2) {
								if(profileTest2.equals(playerNameProfile)) {
										for(Profile profileTest3 : listPlayers3) {
											if(profileTest3.equals(playerNameProfile)) {
												indexOfMainPlayer = listPlayers3.indexOf(profileTest3);
												this.setVisible(false);
												new MenuLevel();
												return;
												
											}
										}
								}	
							}
					}
				}
				JOptionPane.showMessageDialog(new JPanel(), "This player's name doesn't exist", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} else if(e.getSource()== guestPlayer){ // If a player clicks on the button guestPlayer
			
			try { // Read the backups
				
				FileInputStream fis1 = new FileInputStream("BACKUP//save1.dat");
				BufferedInputStream bis1 = new BufferedInputStream(fis1);
				ObjectInputStream ois1 = new ObjectInputStream(bis1);
				DataStorage dStorage1 = (DataStorage)ois1.readObject();			
				listPlayers = dStorage1.listPlayers;
				ois1.close();
				
				FileInputStream fis2 = new FileInputStream("BACKUP//save2.dat");
				BufferedInputStream bis2 = new BufferedInputStream(fis2);
				ObjectInputStream ois2 = new ObjectInputStream(bis2);
				DataStorage dStorage2 = (DataStorage)ois2.readObject();			
				listPlayers2 = dStorage2.listPlayers;
				ois2.close();
				
				FileInputStream fis3 = new FileInputStream("BACKUP//save3.dat");
				BufferedInputStream bis3 = new BufferedInputStream(fis3);
				ObjectInputStream ois3 = new ObjectInputStream(bis3);
				DataStorage dStorage3 = (DataStorage)ois3.readObject();			
				listPlayers3 = dStorage3.listPlayers;
				ois3.close();

			}
			
			catch(IOException ex) {
				
			}catch(ClassNotFoundException a) {
				
			}
			
			this.setVisible(false);
			new MenuLevel();
		}
	}
	
	/** The method setListPlayer allows to update a 
	 * list of players depending on the level and the new time
	 * @param newChrono : new time of a player
	 * @param level : level of the sliding puzzle
	 */
	public static void setListPlayer(Temps newChrono, int level) {
		
		Temps reference = new Temps(0,0,0);
		
		try { // Read the backups
			
			FileInputStream fis1 = new FileInputStream("BACKUP//save1.dat");
			BufferedInputStream bis1 = new BufferedInputStream(fis1);
			ObjectInputStream ois1 = new ObjectInputStream(bis1);
			DataStorage dStorage1 = (DataStorage)ois1.readObject();			
			listPlayers = dStorage1.listPlayers;
			ois1.close();
			
			FileInputStream fis2 = new FileInputStream("BACKUP//save2.dat");
			BufferedInputStream bis2 = new BufferedInputStream(fis2);
			ObjectInputStream ois2 = new ObjectInputStream(bis2);
			DataStorage dStorage2 = (DataStorage)ois2.readObject();			
			listPlayers2 = dStorage2.listPlayers;
			ois2.close();
			
			FileInputStream fis3 = new FileInputStream("BACKUP//save3.dat");
			BufferedInputStream bis3 = new BufferedInputStream(fis3);
			ObjectInputStream ois3 = new ObjectInputStream(bis3);
			DataStorage dStorage3 = (DataStorage)ois3.readObject();			
			listPlayers3 = dStorage3.listPlayers;
			ois3.close();

		}
		
		catch(IOException ex) {
			
		}catch(ClassNotFoundException a) {
			
		}
		
		// Case for level 3:
		if((level == 3) && ((newChrono.compareTo(listPlayers.get(indexOfMainPlayer).getChrono()) == -1) || (listPlayers.get(indexOfMainPlayer).getChrono().compareTo(reference)) == 0) ) {
			
			listPlayers.get(indexOfMainPlayer).setChrono(newChrono);

			try { // Rewrite the backups
				
				FileOutputStream fos1 = new FileOutputStream("BACKUP//save1.dat");
				BufferedOutputStream bos1 = new BufferedOutputStream(fos1);
				ObjectOutputStream oos1 = new ObjectOutputStream(bos1);
				DataStorage dStorage21 = new DataStorage();

				dStorage21.listPlayers = listPlayers;
				oos1.writeObject(dStorage21);
				oos1.close();
				
			}catch(IOException ex) {
				
			}

		// Case for level 4:
		} else if((level == 4) && ((newChrono.compareTo(listPlayers2.get(indexOfMainPlayer).getChrono()) == -1) || (listPlayers2.get(indexOfMainPlayer).getChrono().compareTo(reference)) == 0)){
			
			listPlayers2.get(indexOfMainPlayer).setChrono(newChrono);
			
			try { // Rewrite the backups
				
				FileOutputStream fos2 = new FileOutputStream("BACKUP//save2.dat");
				BufferedOutputStream bos2 = new BufferedOutputStream(fos2);
				ObjectOutputStream oos2 = new ObjectOutputStream(bos2);
				DataStorage dStorage22 = new DataStorage();

				dStorage22.listPlayers = listPlayers2;
				oos2.writeObject(dStorage22);
				oos2.close();
				
			}catch(IOException ex) {
				
			}
		
		// Case for level 5:
		} else if ((level == 5) && ((newChrono.compareTo(listPlayers3.get(indexOfMainPlayer).getChrono()) == -1) || (listPlayers3.get(indexOfMainPlayer).getChrono().compareTo(reference)) == 0)){
			
			listPlayers3.get(indexOfMainPlayer).setChrono(newChrono);
			
			try { // Rewrite the backups
				
				FileOutputStream fos3 = new FileOutputStream("BACKUP//save3.dat");
				BufferedOutputStream bos3 = new BufferedOutputStream(fos3);
				ObjectOutputStream oos3 = new ObjectOutputStream(bos3);
				DataStorage dStorage23 = new DataStorage();

				dStorage23.listPlayers = listPlayers3;
				oos3.writeObject(dStorage23);
				oos3.close();
				
			}catch(IOException ex) {
				
			}
		}
	}
		
	/** The method setListPlayer allows to get a 
	 * list of players depending on the level
	 * @param level : level of the sliding puzzle
	 */	
	public static LinkedList<Profile> getListPlayer(int level) {
		
		
		try { // Read the backups
			
			FileInputStream fis1 = new FileInputStream("BACKUP//save1.dat");
			BufferedInputStream bis1 = new BufferedInputStream(fis1);
			ObjectInputStream ois1 = new ObjectInputStream(bis1);
			DataStorage dStorage1 = (DataStorage)ois1.readObject();			
			listPlayers = dStorage1.listPlayers;
			ois1.close();
			
			FileInputStream fis2 = new FileInputStream("BACKUP//save2.dat");
			BufferedInputStream bis2 = new BufferedInputStream(fis2);
			ObjectInputStream ois2 = new ObjectInputStream(bis2);
			DataStorage dStorage2 = (DataStorage)ois2.readObject();			
			listPlayers2 = dStorage2.listPlayers;
			ois2.close();
			
			FileInputStream fis3 = new FileInputStream("BACKUP//save3.dat");
			BufferedInputStream bis3 = new BufferedInputStream(fis3);
			ObjectInputStream ois3 = new ObjectInputStream(bis3);
			DataStorage dStorage3 = (DataStorage)ois3.readObject();			
			listPlayers3 = dStorage3.listPlayers;
			ois3.close();

		}
		
		catch(IOException ex) {
			
		}catch(ClassNotFoundException a) {
			
		}
		
		LinkedList<Profile> test = new LinkedList<Profile>();
		
		if(level == 3) {
			test = listPlayers;
			Collections.sort(test); // Sort the list of players as a function of their time
		} else if(level == 4) {
			test = listPlayers2; 
			Collections.sort(test); // Sort the list of players as a function of their time
		} else if (level == 5) {
			test = listPlayers3; 
			Collections.sort(test); // Sort the list of players as a function of their time
		}

		return test;
		
	}
	
	/** The method getFinalRanking allows to get the
	 * final ranking of a list of players
	 * @param level : list of players
	 */	
	public static String getFinalRanking(LinkedList<Profile> test){
		
		int indexRank = 0;
		Temps reference = new Temps(0,0,0);
		String ranking = "";
		
	        for (Profile p : test) {
	        	if(p.getChrono().compareTo(reference)!= 0) { // In case the time of a player is still nil, i.e., the player didn't play to the sliding puzzle yet
	        		indexRank++;
	        		ranking +=  indexRank + " - " + p.toString() + "\n";
	        	}
	        }
		return ranking;
	}
	
	/** The method getFinalChrono allows to get the
	 * final time of each player from a list of players
	 * @param level : list of players
	 */	
	public static String getFinalChrono(LinkedList<Profile> test){
		
		Temps reference = new Temps(0,0,0);
		String chronoRanking = "";
		
	        for (Profile p : test) {
	        	if(p.getChrono().compareTo(reference)!= 0) { // In case the time of a player is still nil, i.e., the player didn't play to the sliding puzzle yet
	        		chronoRanking += p.chrono() + "\n";
	        	}
	        }
		return chronoRanking;
	}
	
}
