package Encounters;

import java.util.Random;
import javax.swing.JOptionPane; // <--- IMPORTANT

import Entities.Monster;
import Entities.Player;
import Items.Potion;
import Main.GameLogic;
import Story.Story;
import World.Dungeon;

public class Encounters {
    
	public static Dungeon currentDungeon;
    public static int dungeonLvl = 1; 
    private static Random random = new Random();
    
    // --- GESTION DU COMBAT ---
    public static void handleMonster(Player player, int x, int y)
    {
        Monster monster = createRandomMonster();
        JOptionPane.showMessageDialog(null, "Vous rencontrez un " + monster.getName() + " ! Battez-vous !");
        
        while(player.isAlive() && monster.isAlive()) {
        	
            String combatStatus = "--- COMBAT ---\n" +
                                  player.getName() + " PV: " + player.getHp() + "/" + player.getMaxHp() + "\n" +
                                  monster.getName() + " PV: " + monster.getHp() + "/" + monster.getMaxHp() + "\n\n" +
                                  "Que voulez-vous faire ?";
            
            String[] options = {"Attaquer", "Inventaire", "Fuir"};
            
            // Affichage de la fenêtre de choix
            int choice = JOptionPane.showOptionDialog(null, combatStatus, "Combat en cours",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);

            int playerDmg = Math.max(1, player.getAttack() - monster.getDefense());
            int monsterDmg = Math.max(1, monster.getAttack() - player.getDefense());
            
            // --- LOGIQUE DES CHOIX ---
            if(choice == 0) { // ATTAQUER
                monster.takeDmg(playerDmg);
                String result = "Vous infligez " + playerDmg + " dégats à " + monster.getName();
                
                if(monster.isAlive()) {
                    player.takeDmg(monsterDmg);
                    result += "\n" + monster.getName() + " riposte et inflige " + monsterDmg + " dégats !";
                }
                JOptionPane.showMessageDialog(null, result);
                
            } else if (choice == 1) { // INVENTAIRE
                 GameLogic.showPlayerInventory(); 
                 
            } else if (choice == 2){ // FUIR
                if(random.nextBoolean()) {
                    JOptionPane.showMessageDialog(null, "Vous avez réussi à fuir !");
                    return;
                } else {
                    player.takeDmg(monsterDmg);
                    JOptionPane.showMessageDialog(null, "Fuite ratée !\n" + monster.getName() + " vous frappe pour " + monsterDmg + " dégats.");
                }
            } else {
                return;
            }
        }
        
        // --- RESULTAT DU COMBAT ---
        if(!player.isAlive()) {
            JOptionPane.showMessageDialog(null, "Vous êtes mort... GAME OVER.");
            GameLogic.isRunning = false;
            System.exit(0); // On ferme le jeu
        } else {
            player.takeXp(monster.getXpOnDeath());
            player.takeGold(monster.getGoldOnDeath());
            
            String winMsg = "VICTOIRE !\n" +
                            "Vous avez vaincu " + monster.getName() + "\n" +
                            "Gain: " + monster.getXpOnDeath() + " XP | " + monster.getGoldOnDeath() + " Or";
            
            JOptionPane.showMessageDialog(null, winMsg);
            player.setPos(player, x, y);
            currentDungeon.clearTile(x, y);
            GameLogic.currentMessage = "Victoire contre " + monster.getName();
        }
    }

    // --- CREATION MONSTRE ---
    private static Monster createRandomMonster() {
        if(dungeonLvl == 1) return new Monster("Slime", 30, 5, 2, 10, 5);
        else if (dungeonLvl == 2) return random.nextBoolean() ? new Monster("Loup", 50, 10, 4, 20, 10) : new Monster("Meute", 70, 15, 3, 30, 15);
        else if (dungeonLvl == 3) return new Monster("Gobelin", 60, 12, 6, 25, 12);
        return new Monster("Slime", 30, 5, 2, 10, 5);
    }

    // --- OBSTACLE ---
    public static void handleObstacle(Player player, int x, int y) {
        int response = JOptionPane.showConfirmDialog(null, 
                "Un rocher bloque le chemin. Voulez-vous le détruire ?\n(Risque de blessure)", 
                "Obstacle", JOptionPane.YES_NO_OPTION);
        
        if(response == JOptionPane.YES_OPTION) {
            player.takeDmg(5);
            JOptionPane.showMessageDialog(null, "CRAC ! Rocher détruit, mais vous perdez 5 PV.");
            currentDungeon.clearTile(x, y);
            player.setPos(player, x, y);
            GameLogic.currentMessage = "Obstacle détruit.";
        } else {
            GameLogic.currentMessage = "Vous faites demi-tour devant le rocher.";
        }
    }

    // --- SORTIE ---
    public static void handleExit(Player player) {
        JOptionPane.showMessageDialog(null, "BRAVO ! Vous avez trouvé la sortie !");
        dungeonLvl++;
        
        if(dungeonLvl == 2) {
        	Story.Act1();
        	startNextLvl(player);
        } else if (dungeonLvl == 3) {
        	Story.Act2();
        	startNextLvl(player);
        } else if (dungeonLvl == 4) {
        	Story.Act3();
        	startNextLvl(player);
        } else if (dungeonLvl == 5) {
        	Story.FinalActBoss();
        	handleBossFight(player);
        } else {
        	Story.End();
        	JOptionPane.showMessageDialog(null, "Merci d'avoir joué !");
        	System.exit(0);
        }
    }
    
    private static void startNextLvl(Player player) {
        player.setPos(player, 0, 0);
        currentDungeon = new Dungeon(dungeonLvl, player);
        GameLogic.currentMessage = "Niveau " + dungeonLvl + " commencé.";
    }
    
    // --- BOSS ---
    public static void handleBossFight(Player player) {
        Monster boss = new Monster("OGRE DU FORT", 300, 30, 10, 1000, 500);
        JOptionPane.showMessageDialog(null, "Vous rencontrez un " + boss.getName() + " ! Battez-vous !");
        
        while(player.isAlive() && boss.isAlive()) {
        	
            String combatStatus = "--- COMBAT ---\n" +
                                  player.getName() + " PV: " + player.getHp() + "/" + player.getMaxHp() + "\n" +
                                  boss.getName() + " PV: " + boss.getHp() + "/" + boss.getMaxHp() + "\n\n" +
                                  "Que voulez-vous faire ?";
            
            String[] options = {"Attaquer", "Inventaire", "Fuir"};
            
            // Affichage de la fenêtre de choix
            int choice = JOptionPane.showOptionDialog(null, combatStatus, "Combat en cours",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);

            int playerDmg = Math.max(1, player.getAttack() - boss.getDefense());
            int monsterDmg = Math.max(1, boss.getAttack() - player.getDefense());
            
            // --- LOGIQUE DES CHOIX ---
            if(choice == 0) { // ATTAQUER
                boss.takeDmg(playerDmg);
                String result = "Vous infligez " + playerDmg + " dégats à " + boss.getName();
                
                if(boss.isAlive()) {
                    player.takeDmg(monsterDmg);
                    result += "\n" + boss.getName() + " riposte et inflige " + monsterDmg + " dégats !";
                }
                JOptionPane.showMessageDialog(null, result);
                
            } else if (choice == 1) { // INVENTAIRE
                 GameLogic.showPlayerInventory(); 
                 
            } else if (choice == 2){ // FUIR
            	JOptionPane.showMessageDialog(null, "Vous ne pouvez pas fuir le combat !");
            } else {
                return;
            }
        }
    }

    // --- SHOP ---
    public static void handleShop(Player player, int x, int y) {
        GameLogic.currentMessage = "Vous entrez dans le magasin.";
        player.setPos(player, x, y);
        
        boolean inShop = true;
        while(inShop) {
            String shopMsg = "Marchand: 'Bienvenue !'\nVotre Or: " + player.getGold() + "\n\nQue voulez-vous ?";
            String[] items = {"Potion (10 Or)", "Hache (100 Or)", "Armure (25 Or)", "Quitter"};
            
            int choice = JOptionPane.showOptionDialog(null, shopMsg, "Magasin",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, items, items[0]);
            
            if (choice == 0) { // Potion
                if(player.getGold() >= 10) {
                    player.addItem(new Potion("Potion", "Soigne 50PV", 10, 50));
                    player.takeGold(-10);
                    JOptionPane.showMessageDialog(null, "Potion achetée !");
                } else JOptionPane.showMessageDialog(null, "Pas assez d'or !");
                
            } else if (choice == 1) { // Hache
                if(player.getGold() >= 100) {
                    player.takeGold(-100);
                    JOptionPane.showMessageDialog(null, "Hache achetée ! (TODO: équiper)");
                } else JOptionPane.showMessageDialog(null, "Pas assez d'or !");
                
            } else if (choice == 2) { // Armure
                if(player.getGold() >= 25) {
                    player.takeGold(-25);
                    JOptionPane.showMessageDialog(null, "Armure achetée !");
                } else JOptionPane.showMessageDialog(null, "Pas assez d'or !");
                
            } else { // Quitter
                inShop = false;
                GameLogic.currentMessage = "Vous quittez le magasin.";
            }
        }
    }
}