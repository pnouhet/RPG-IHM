package Story;

import javax.swing.JOptionPane;

import Main.GameLogic;

public class Story {

	public static void Intro()
	{
		JOptionPane.showMessageDialog(null,"--- Prologue : L'Ombre et les Cendres ---\n" +
			"Vous vous réveillez après plusieurs heures... La douleur vous arrache un gémissement.\n" +
			"Votre village, *Bois-Clair*, n'est plus qu'un amas fumant.\n" +
			"Vos parents, vos amis, vos voisins... Plus personne. Seule l'odeur âcre de la destruction subsiste.\n" +
			"Une trace boueuse mène à un tunnel de fortune. Les responsables ? Une horde de **Gobelins**.\n" +
			"Une rage froide vous anime. Vous décidez de vous venger et partez à la recherche de la source de ce mal. \n" +
			"Le premier pas mène au **Donjon du Village Abandonné**.");
	}

	//Donjon Niveau 1 (Village)
	public static void Act1() {
		JOptionPane.showMessageDialog(null,"--- Acte I : Le Chemin de l'Ouest ---\n" +
			"Vous émergez du village, le cœur lourd mais la détermination intacte.\n" +
			"Sur le cadavre d'un gobelin, vous trouvez une carte rudimentaire indiquant une route vers l'ouest.\n" +
			"Elle mentionne les **Plaines de l'Orage**, une zone infestée de bêtes sauvages et de patrouilles d'éclaireurs.\n" +
			"Si les Gobelins s'y aventurent, c'est qu'ils ont une base plus loin. Vous reprenez la route, sachant que les plaines ne seront qu'une étape de plus.\n" +
			"\n**Prochain Donjon : Plaines Dangereuses (Niveau 2)**.\n");
	}

	//Donjon Niveau 2 (Plaines)
	public static void Act2() {
		JOptionPane.showMessageDialog(null,"--- Acte II : La Muraille de Pierre ---\n" +			"Après avoir traversé les plaines et vaincu ses dangers, vous apercevez une sinistre construction à l'horizon : le **Fort des Crocs Cassés**.\n" +			"C'est une forteresse rudimentaire mais solide, manifestement le repaire principal de la horde. Des bannières gobelines flottent sur les tours.\n" +			"Vous vous infiltrez par une ancienne canalisation. Il n'y aura pas de repos ici. Vous devez trouver et éliminer le chef de cette meute.\n" +			"\n**Prochain Donjon : Fort Gobelins (Niveau 3)**.\n");
	}

	//Donjon Niveau 3 (Fort)
	public static void Act3() {
		JOptionPane.showMessageDialog(null,"--- Acte III : La Chambre de l'Ogre ---\n" +
			"Vous avez atteint le centre du Fort. Les gobelins restants s'enfuient en hurlant devant une immense porte de bois renforcée de fer.\n" +
			"Au-delà de cette porte se trouve le responsable, la brute qui a mené la destruction du village.\n" +
			"Une ombre massive bouge derrière la porte. Préparez-vous. C'est l'heure d'honorer la mémoire de Bois-Clair.\n" +
			"\n**Prochain Donjon : Salle du Boss (Niveau 4)**.\n");
	}

	// Combat contre le Boss Final
	public static void FinalActBoss() {
		JOptionPane.showMessageDialog(null, 
			"--- Boss Ogre ---\n" +
			"GNARAAAAAH ! TUE PETIT HUMAIN !");
	}
	
	public static void End() {
		JOptionPane.showMessageDialog(null,"--- Épilogue : Un silence bien mérité ---\n" +
			"L'Ogre s'effondre dans un vacarme assourdissant. Le silence retombe sur le Fort des Crocs Cassés.\n" +
			"Vous avez tenu votre promesse. Le mal qui a détruit votre foyer est éradiqué.\n" +
			"Alors que le soleil se lève, vous quittez le fort. La vengeance est accomplie, mais le chemin à parcourir est long. Un nouveau monde vous attend. \n" +
			"\n***FÉLICITATIONS, " + GameLogic.player.getName() + ". VOUS AVEZ TERMINÉ VOTRE QUÊTE.***");
		//GameLogic.isRunning = false;
	}

}
