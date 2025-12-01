package Entities;

import java.util.ArrayList;

import Items.Weapon;
import Items.Item;
import Items.Potion;

public class Player extends Entity{

	public enum PlayerClass { CHEVALIER, ARCHER, MAGE, BARBARE }
	
	public PlayerClass playerClass;
	public ArrayList<Item> inventory = new ArrayList<>();
	private Weapon currentWeapon;
	private int x, y;
	private int xp;
	private int gold;
	private int mana;
	//private int maxHp;
	
	//Constructeur
	public Player(String name, PlayerClass playerClass) {
		// maxHp, attack, defense
		super(name, 100, 10, 5);
		this.playerClass = playerClass;
		this.xp = 0;
		this.gold = 0;
		this.x = 0;
		this.y = 0;
		this.currentWeapon = new Weapon("Mains Nues", "Vos poings", 0, 0);
		
		//Stats différentes en fonction de la classe
		if(playerClass == PlayerClass.ARCHER) {
			this.setMaxHp(90);
			this.hp = 90;
			this.attack = 11;
			this.defense = 4;
			this.currentWeapon = new Weapon("Arc", "Une bel Arc en bois", 100, 3);
		} else if (playerClass == PlayerClass.BARBARE) {
			this.setMaxHp(110);
			this.hp = 110;
			this.attack = 12;
			this.defense = 3;
			this.currentWeapon = new Weapon("Hache", "Une Hache bien tranchante", 100, 5);
		} else if (playerClass == PlayerClass.CHEVALIER) {
			this.setMaxHp(100);
			this.defense = 9;
			this.currentWeapon = new Weapon("Épée", "Une belle Épée ancienne", 150, 2);
		} else if (playerClass == PlayerClass.MAGE) {
			this.setMaxHp(100);
			this.attack = 8;
			this.defense = 7;
			this.mana = 50;
			this.currentWeapon = new Weapon("Baton Magique", "Un Baton Magique duquel émane une étrange lumière pourpre", 200, 3);
		}
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	public void setPos(Player player, int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getGold()
	{
		return gold;
	}
	
	public int getXp()
	{
		return xp;
	}
	
	public void takeXp(int xp)
	{
		this.xp += xp;
	}
	
	public void takeGold(int gold)
	{
		this.gold += gold;
	}
	
	public PlayerClass getPlayerClass()
	{
		return playerClass;
	}
	
	public int getMana()
	{
		return mana;
	}
	
	//Methode utiliser l'Inventaire
	public void useItem(int invIndex)
	{
		if(invIndex >= 0 && invIndex < inventory.size()) {
			Item itemToUse = inventory.get(invIndex);
			
			itemToUse.useItem(this);
			
			if(itemToUse instanceof Potion) {
				inventory.remove(invIndex);
			}
		}
	}
	
	//Methode pour soigner le joueur, utiliser une potion
	public void heal(int healAmount)
	{
		int newHpValue = this.hp + healAmount;
		this.hp = newHpValue;
		
		if(this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}
	}
	
	//Methode pour equiper une arme
	public void equipWeapon(Weapon newWeapon)
	{
		this.currentWeapon = newWeapon;
	}
	
	//Methode pour calculer l'attaque totale en fonction de l'arme equiper par le joueur
	public int getAttack()
	{
		int baseAttack = super.getAttack();
		int totalAttack = baseAttack + this.currentWeapon.getAttackBonus();
		return totalAttack;
	}

	public Weapon getCurrentWeapon() {
		return this.currentWeapon;
	}

	public ArrayList<Item> getInv() {
		return this.inventory;
	}

	public void addItem(Item item) {
		this.inventory.add(item);
	}
}
