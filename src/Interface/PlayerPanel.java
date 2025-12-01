package Interface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Main.GameLogic;
import Story.Story;

@SuppressWarnings("serial")
public class PlayerPanel extends JFrame {

	public static JPanel createPersonnagePanel() 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JLabel nameLabel = new JLabel("Entrez le nom de votre héros");
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextField nameField = new JTextField(15);
		nameField.setMaximumSize(new Dimension(200, 30));
		
		JLabel classLabel = new JLabel("Choisissez une classe pour votre héros !");
		classLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JRadioButton chevalierBtn = new JRadioButton("Chevalier (Défense +)");
		JRadioButton archerBtn = new JRadioButton("Archer (PV-, Attaque+, Défense-)");
		JRadioButton barbareBtn = new JRadioButton("Barbare (PV+, Attaque+, Défense--)");
		JRadioButton mageBtn = new JRadioButton("Mage (PV-, Attaque-, Défense++, Mana)");
		
		chevalierBtn.setSelected(true);
		
		ButtonGroup classGroup = new ButtonGroup();
		classGroup.add(chevalierBtn);
		classGroup.add(archerBtn);
		classGroup.add(barbareBtn);
		classGroup.add(mageBtn);
		
		JPanel radioBtnsPanel = new JPanel(new GridLayout(0, 1));
		radioBtnsPanel.setMaximumSize(new Dimension(400, 150));
		radioBtnsPanel.add(chevalierBtn);
		radioBtnsPanel.add(archerBtn);
		radioBtnsPanel.add(barbareBtn);
		radioBtnsPanel.add(mageBtn);
		
		JButton confirmBtn = new JButton("Commencer l'aventure !");
		confirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		confirmBtn.addActionListener(e -> {
			String heroName = nameField.getText();
			String heroClass = "";
			
			if(chevalierBtn.isSelected()) heroClass = "Chevalier";
			else if (archerBtn.isSelected()) heroClass = "Archer";
			else if (barbareBtn.isSelected()) heroClass = "Barbare";
			else if (mageBtn.isSelected()) heroClass = "Mage";
			
			if (heroName.trim().isEmpty()) {
				JOptionPane.showMessageDialog(panel, "Donner un nom à votre héros", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				System.out.println("Création de " + heroName + " le " + heroClass);
				GameLogic.initGameFromGUI(heroName, heroClass);
				GamePanel.updateInterface();
				GameWindow.cardLayout.show(GameWindow.mainPanel, "GAME");
				Story.Intro();
			}
		});
		
		panel.add(Box.createVerticalGlue());
		panel.add(nameLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(nameField);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(classLabel);
		panel.add(radioBtnsPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(confirmBtn);
		panel.add(Box.createVerticalGlue());
		
		return panel;
	}
}
