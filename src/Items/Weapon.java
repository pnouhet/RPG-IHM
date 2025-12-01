package Items;

import Entities.Player;

public class Weapon extends Item {

	private int attackBonus;
	
	public Weapon(String name, String description, int price, int attackBonus) {
		super(name, description, price);
		this.attackBonus = attackBonus;
	}

	@Override
	public void useItem(Player target) {
		target.equipWeapon(this);
		System.out.println(target.getName() + " équipe " + this.name + ".");
	}

	public int getAttackBonus() {
		return attackBonus;
	}

}
