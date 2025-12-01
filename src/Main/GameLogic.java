package Main;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Entities.Player;
import Entities.Player.PlayerClass;
import Items.Item;
import Items.Weapon;
import World.Dungeon;
import World.Tile;
import Encounters.Encounters;

public class GameLogic {

	public static Player player;
	public static boolean isRunning;
	//private static Scanner scanner = new Scanner(System.in);
	public static String currentMessage = "";
	
	public static void initGameFromGUI(String nameStr, String classStr) 
	{
        PlayerClass selectedClass;
        
        switch(classStr) {
            case "Mage": selectedClass = PlayerClass.MAGE; break;
            case "Chevalier": selectedClass = PlayerClass.CHEVALIER; break;
            case "Barbare": selectedClass = PlayerClass.BARBARE; break;
            case "Archer": selectedClass = PlayerClass.ARCHER; break;
            default: selectedClass = PlayerClass.CHEVALIER;
        }
        player = new Player(nameStr, selectedClass);
        Encounters.currentDungeon = new Dungeon(Encounters.dungeonLvl, player);
        isRunning = true;
        System.out.println("Jeu initialisé via GUI pour : " + player.getName());
    }

	//Methode pour afficher l'inventaire du joueur
	/*public static void showPlayerInventory() 
	{
		System.out.println("\n---INVENTAIRE---");
		
		//Afficher l'arme equipée
		Weapon equippedWeapon = player.getCurrentWeapon();
		System.out.println("ARME ÉQUIPÉE: ");
		if (equippedWeapon != null) {
			System.out.println(" - " + equippedWeapon.getItemName() + "(Atk " + equippedWeapon.getAttackBonus() + ")");
			System.out.println(" {'" + equippedWeapon.getItemDesc() + "'}");
		} else {
			System.out.println(" - Aucune arme équipée");
		}
		
		//Afficher les items ArrayList inventory
		System.out.println("\nSAC À DOS: ");
		ArrayList<Item> inventory = player.getInv();
		
		if(inventory.isEmpty()) {
			System.out.println("Votre sac est vide.");
		} else {
			for(int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				System.out.println("  (" + (i+1) + ")  " + item.getItemName());
				System.out.println("  {" + item.getItemDesc() + "}  ");
			}
		}
		
		//Actions de l'inventaire
		System.out.println("\nQue voulez-vous faire ? \n(1)Utiliser un objet \n(2)Fermer l'inventaire");
		int choice = scanner.nextInt();
		
		if(choice == 1) {
			if (inventory.isEmpty()) {
				System.out.println("Vous n'avez pas aucun objet à utiliser");
				return;
			}
			
			System.out.println("Quel objet souhaitez-vous utiliser ? (Entrez le numéro de l'objet)");
			try {
				int input = scanner.nextInt();
				player.useItem(input - 1);
			} catch (NumberFormatException e) {
				System.out.println("Erreur, entrez un numéro");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Numéro d'objet invalide");
			}
		}
	}*/

	public static void showPlayerInventory() {
	    StringBuilder info = new StringBuilder();
	    ArrayList<Item> inventory = player.getInv();

	    if (inventory.isEmpty()) {
	        info.append("Votre sac est vide.");
	        JOptionPane.showMessageDialog(null, info.toString(), "Inventaire", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    String[] itemList = new String[inventory.size()];
	    for (int i = 0; i < inventory.size(); i++) {
	        Item item = inventory.get(i);
	        itemList[i] = (i + 1) + ". " + item.getItemName() + " { " + item.getItemDesc() + " }";
	    }

	    String selectedValue = (String) JOptionPane.showInputDialog(
	    	null, info.toString() + "Choisissez un objet à utiliser :", "Inventaire", JOptionPane.PLAIN_MESSAGE, null, itemList, itemList[0]
	    );

	    if (selectedValue != null) {
	        int selectedIndex = -1;
	        for (int i = 0; i < itemList.length; i++) {
	            if (itemList[i].equals(selectedValue)) {
	                selectedIndex = i;
	                break;
	            }
	        }

	        if (selectedIndex != -1) {
	            Item usedItem = inventory.get(selectedIndex);
	            player.useItem(selectedIndex);
	            JOptionPane.showMessageDialog(null, "Vous avez utilisé : " + usedItem.getItemName());
	        }
	    }
	}

	//Methode pour récupérer le mouvement dans la map
	public static void processMovement(int input) 
	{
		int newX = player.getX();
		int newY = player.getY();
		
		switch (input){
			case 1: newX--; break; //newX--
			case 2: newX++; break; //newX++
			case 3: newY--; break; //newY--
			case 4: newY++; break; //newY++
			default: System.out.println("Entrez un choix entre 1 et 7.");
			return;
		}
		
		//Verifier bords de la map
		if(newX < 0 || newX >= 12 || newY < 0 || newY >= 12) {
			currentMessage = "Vous ne pouvez avancé dans le mur.";
			return;
		}
		
		//Verifier la case où le joueur veut aller
		Tile targetTile = Encounters.currentDungeon.getTileAt(newX, newY);
		
		switch(targetTile) {
			case Tile.EMPTY: 
				System.out.println("Vous avancez d'une case");
				player.setPos(player, newX, newY);
				break;
			case Tile.OBSTACLE:
				Encounters.handleObstacle(player, newX, newY);
				break;
			case Tile.MONSTER:
				Encounters.handleMonster(player, newX, newY);
				break;
			case Tile.SHOP:
				Encounters.handleShop(player, newY, newY);
				player.setPos(player, newX, newY);
				break;
			case Tile.EXIT:
				Encounters.handleExit(player);
				break;
		}
		
	}
	
}
