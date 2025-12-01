package Entities;

public abstract class Entity {

	public String name;
	protected int hp;
	protected int maxHp;
	protected int attack;
	protected int defense;
	
	//Constructeur
	public Entity(String name, int maxHp, int attack, int defense)
	{
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attack = attack;
		this.defense = defense;
	}
	
	//Methode pour verifier si l'entité est vivante
	public boolean isAlive()
	{
		return this.hp > 0;
	}
	
	public void takeDmg(int dmg)
	{
		this.hp -= dmg;
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHp()
	{
		return hp;
	}
	
	public int getHpValue()
	{
		return hp;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public int getDefense()
	{
		return defense;
	}
	
	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
}
