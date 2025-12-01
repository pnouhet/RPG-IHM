package Items;

import Entities.Player;

public abstract class Item {

	protected String name;
	protected String description;
	protected int price;
	
	public Item(String name, String description, int price)
	{
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public String getItemName()
	{
		return name;
	}
	
	public String getItemDesc()
	{
		return description;
	}
	
	public int getItemPrice()
	{
		return price;
	}
	
	public abstract void useItem(Player target);
}
