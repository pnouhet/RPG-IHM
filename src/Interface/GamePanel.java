package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import Encounters.Encounters;
import Main.GameLogic;
import Entities.Player;

@SuppressWarnings("serial")
public class GamePanel extends JFrame {

	//private static JTextArea asciiArtArea;
	private static JLabel imgLabel;
	private static JTextArea statsArea;
	private static JTextArea mapArea;
	private static JTextArea logArea;
	
	public static JPanel createGamePanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		
		//Panneau gauche
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(Color.BLACK);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		/*asciiArtArea = new JTextArea(10,20);
		asciiArtArea.setFont(new Font("Monospaced", Font.BOLD, 12));
		asciiArtArea.setEditable(false);
		asciiArtArea.setBackground(Color.BLACK);
		asciiArtArea.setForeground(Color.GREEN);*/
		imgLabel = new JLabel();
		imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		imgLabel.setPreferredSize(new Dimension(200,200));
		imgLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		statsArea = new JTextArea(10,20);
		statsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		/*asciiArtArea.setEditable(false);
		asciiArtArea.setBackground(Color.BLACK);
		asciiArtArea.setForeground(Color.WHITE);*/
		
		//leftPanel.add(new JScrollPane(asciiArtArea));
		leftPanel.add(imgLabel);
		leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
		leftPanel.add(new JScrollPane(statsArea));
		
		//Panneau droit
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel controls = createControlsPanel();
		rightPanel.setBackground(Color.DARK_GRAY);
		rightPanel.add(controls, BorderLayout.SOUTH);
		//rightPanel.setBorder(BorderFactory.createTitledBorder("Carte du DONJON"));
		
		mapArea = new JTextArea();
		mapArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		mapArea.setEditable(false);
		mapArea.setBackground(Color.DARK_GRAY);
		mapArea.setForeground(Color.CYAN);
		rightPanel.add(mapArea, BorderLayout.CENTER);
		
		JPanel bottomContainer = new JPanel(new BorderLayout());
		logArea = new JTextArea(5, 20);
	    logArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
	    logArea.setEditable(false);
	    logArea.setLineWrap(true);
	    logArea.setWrapStyleWord(true);
	    logArea.setBackground(Color.BLACK);
	    logArea.setForeground(Color.YELLOW);
	    
	    JScrollPane logScroll = new JScrollPane(logArea);
	    logScroll.setBorder(BorderFactory.createTitledBorder("Journal d'action"));
	    
	    bottomContainer.add(logScroll, BorderLayout.CENTER);
	    bottomContainer.add(controls, BorderLayout.SOUTH);
	    rightPanel.add(bottomContainer, BorderLayout.SOUTH);
		
	    //Divider
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setDividerLocation(300);
		splitPane.setResizeWeight(0.3);
		splitPane.setOneTouchExpandable(true);
		
		panel.add(splitPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	public static void updateInterface() {
        if (GameLogic.player == null) return;

        Player p = GameLogic.player;
        String imgPath = "";

        String statsText = 
            "Nom   : " + p.getName() + "\n" +
            "Classe: " + p.getPlayerClass() + "\n" +
            "------------------\n" +
            "PV    : " + p.getHp() + " / " + p.getMaxHp() + "\n" +
            "Or    : " + p.getGold() + "\n" +
            "XP    : " + p.getXp();
            
        if(p.getMana() > 0) {
            statsText += "\nMana  : " + p.getMana();
        }
            
        statsText += "\n\nInventaire (" + p.getInv().size() + "):";
        
        statsArea.setText(statsText);

        if (Encounters.currentDungeon != null) {
        	String mapText = Encounters.currentDungeon.getMapString();
        	mapArea.setText(mapText);
        }
        
        /*if (p.playerClass == PlayerClass.CHEVALIER) {
        	//asciiArtArea.setText(Arts.knightVisual());
        } else if (p.playerClass == PlayerClass.ARCHER) {
        	//asciiArtArea.setText(Arts.archerVisual());
        } else if (p.playerClass == PlayerClass.BARBARE) {
        	//asciiArtArea.setText(Arts.barbareVisual());
        } else if (p.playerClass == PlayerClass.MAGE) {
        	//asciiArtArea.setText(Arts.mageVisual());
        }*/
        
        switch (p.getPlayerClass()) {
        	case CHEVALIER: imgPath = "/Images/chevalier.jpg"; break;
        	case ARCHER: imgPath = "/Images/archer.jpg"; break;
        	case BARBARE: imgPath = "/Images/barbare.jpg"; break;
        	case MAGE: imgPath = "/Images/mage.jpg"; break;
        	default: imgPath = "/Images/default.jpg"; break;
        }
        
        ImageIcon icon = loadIcon(imgPath);
        if (icon != null) {
        	imgLabel.setIcon(icon);
        	imgLabel.setText("");
        } else {
        	imgLabel.setIcon(null);
        	imgLabel.setText("Image introuvable");
        }
        
        // GESTION DU LOG
        if (!GameLogic.currentMessage.isEmpty()) {
            logArea.append("> " + GameLogic.currentMessage + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
            GameLogic.currentMessage = ""; 
        }
    }
	
	private static JPanel createControlsPanel()
	{
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(2,3));
		controls.setBackground(Color.DARK_GRAY);
		
		JButton btnHaut = new JButton("^");
		JButton btnBas = new JButton("v");
		JButton btnGauche = new JButton("<");
		JButton btnDroite = new JButton(">");
		
		btnHaut.addActionListener(e -> {
			GameLogic.processMovement(1);
			updateInterface();
		});
		btnBas.addActionListener(e -> {
			GameLogic.processMovement(2);
			updateInterface();
		});
		btnGauche.addActionListener(e -> {
			GameLogic.processMovement(3);
			updateInterface();
		});
		btnDroite.addActionListener(e -> {
			GameLogic.processMovement(4);
			updateInterface();
		});
		
		controls.add(new JLabel(""));
		controls.add(btnHaut);
		controls.add(new JLabel(""));
		controls.add(btnGauche);
		controls.add(btnBas);
		controls.add(btnDroite);
		
		return controls;
	}
	
	private static ImageIcon loadIcon(String path)
	{
		try {
			java.net.URL imgURL = GamePanel.class.getResource(path);
			
			if (imgURL != null) {
				ImageIcon icon = new ImageIcon(imgURL);
				Image img = icon.getImage();
				Image newImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				return new ImageIcon(newImg);
			} else {
				System.err.println("Impossible de trouver l'image : " + path);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
