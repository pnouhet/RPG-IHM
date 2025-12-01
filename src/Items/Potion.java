package Items;

import Entities.Player;

public class Potion extends Item{

	private int healAmount;
	
	public Potion(String name, String description, int price, int healAmount) {
		super(name, description, price);
		this.healAmount = healAmount;
	}

	@Override
	public void useItem(Player target) {
		target.heal(healAmount);
		System.out.println(target.getName() + " utilise" + this.name + " et récupère " + healAmount + " PV.");
	}

}
