package Entities;

public class Monster extends Entity{

	private int xpOnDeath;
	private int goldOnDeath;
	
	public Monster(String name, int maxHp, int attack, int defense, int xpOnDeath, int goldOnDeath)
	{
		super(name, maxHp, attack, defense);
		this.xpOnDeath = xpOnDeath;
		this.goldOnDeath = goldOnDeath;
	}
	
	public int getXpOnDeath()
	{
		return xpOnDeath;
	}
	public int getGoldOnDeath()
	{
		return goldOnDeath;
	}
}
