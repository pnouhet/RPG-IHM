package Interface;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	public static JPanel mainPanel;
	public static CardLayout cardLayout;
	
	public GameWindow() {
		setTitle("TextRPG by pnouhet");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		
		JPanel menuPanel = createMenuPanel();
		JPanel creationPersoPanel = PlayerPanel.createPersonnagePanel();
		JPanel gamePanel = GamePanel.createGamePanel();
		mainPanel.add(menuPanel, "MENU");
		mainPanel.add(creationPersoPanel, "CREATIONPERSO");
		mainPanel.add(gamePanel, "GAME");
		
		add(mainPanel);
		
		cardLayout.show(mainPanel, "MENU");
	}

	private JPanel createMenuPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.DARK_GRAY);
		
		JLabel titleLabel = new JLabel("Le Fort de Gobelins");
		titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel creatorLabel = new JLabel("by pnouhet");
		creatorLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
		creatorLabel.setForeground(Color.WHITE);
		creatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Création des boutons
		Dimension btnSize = new Dimension(200, 40);
		
		JButton startButton = new JButton("Lancer le jeu");
		startButton.setMaximumSize(btnSize);
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton optionsButton = new JButton("Options");
		optionsButton.setMaximumSize(btnSize);
		optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton quitButton = new JButton("Quitter le jeu");
		quitButton.setMaximumSize(btnSize);
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Actions des boutons
		quitButton.addActionListener(e -> System.exit(0));
		optionsButton.addActionListener(e -> System.out.println("Options cliquées"));
		startButton.addActionListener(e -> {
			System.out.println("Vers la création du personnage...");
			cardLayout.show(mainPanel,"CREATIONPERSO");
		});
		
		panel.add(Box.createVerticalGlue());
		panel.add(titleLabel);
		panel.add(creatorLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(startButton);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(optionsButton);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(quitButton);
		panel.add(Box.createVerticalGlue());
		
		return panel;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new GameWindow().setVisible(true);
		});
	}
}
