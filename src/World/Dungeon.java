package World;

import java.util.Random;

import Entities.Player;

public class Dungeon {

	private Tile[][] map;
	private final int size = 6;
	private int level;
	private Player player;
	
	public Dungeon(int level, Player player)
	{
		this.map = new Tile[size][size];
		this.level = level;
		this.player = player;
		generateDungeon();
	}
	
	public void generateDungeon()
	{
		Random rand = new Random();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				
				int chance = rand.nextInt(100);
				
				if(chance < 15) {
					map[x][y] = Tile.MONSTER;
				} else if (chance < 25) {
					map[x][y] = Tile.OBSTACLE;
				} else if (chance < 30) {
					map[x][y] = Tile.SHOP;
				} else {
					map[x][y] = Tile.EMPTY;
				}
			}
		}
		
		//Definir le point de depart et sortie du donjon
		map[0][0] = Tile.EMPTY;
		map[size - 1][size - 1] = Tile.EXIT;
	}
	
	public Tile getTileAt(int x, int y)
	{
		return map[x][y];
	}
	
	public void clearTile(int x, int y)
	{
		map[x][y] = Tile.EMPTY;
	}
	
	/*public void displayMap()
	{
		System.out.println("---Carte du Donjon, Nv " + this.level + "---");
		for(int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if(x == player.getX() && y == player.getY()) {
					System.out.print("[P]");
				} else {
					switch (map[x][y]) {
						case EMPTY: System.out.print("[ ]");
						break;
						case MONSTER: System.out.print("[M]");
						break;
						case OBSTACLE: System.out.print("[O]");
						break;
						case SHOP: System.out.print("[S]");
						break;
						case EXIT: System.out.print("[E]");
						break;
					}
				}
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}*/
	
	public String getMapString() {
	    StringBuilder sb = new StringBuilder();

	    if (level == 1) {
		    sb.append("--- Village Abandonné ---\n");
	    } else if (level  == 2) {
	    	sb.append("--- Plaines Dangereuses ---\n");
	    } else if (level == 3) {
	    	sb.append("--- Fort Gobelins ---\n");
	    } else if (level == 4) {
	    	sb.append("--- Salle du boss ---\n");
	    }

	    for(int x = 0; x < size; x++) {
	        for (int y = 0; y < size; y++) {
	            
	            if(x == player.getX() && y == player.getY()) {
	                sb.append("[P]");
	            } else {
	                switch (map[x][y]) {
	                    case EMPTY:    sb.append("[ ]"); break;
	                    case MONSTER:  sb.append("[M]"); break;
	                    case OBSTACLE: sb.append("[O]"); break;
	                    case SHOP:     sb.append("[S]"); break;
	                    case EXIT:     sb.append("[E]"); break;
	                    default:       sb.append("[?]"); break;
	                }
	            }
	        }
	        sb.append("\n");
	    }
	    
	    sb.append("-------------------------");

	    return sb.toString();
	}
}
