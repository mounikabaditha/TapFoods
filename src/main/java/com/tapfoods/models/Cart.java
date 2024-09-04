package com.tapfoods.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private Map<Integer,CartItem > items;
	
	public Cart()
	{
		this.items=new HashMap<>();	
	}
	public void addItems(CartItem item)
	{
		int itemid=item.getItemid();
		
		if(items.containsKey(itemid))
		{
			CartItem existingItem=items.get(itemid);
			existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
		}
		else {
			
			items.put(itemid,item);
		}
	}

	public void updateItem(int itemid,int quantity)
	{
		if(items.containsKey(itemid))
		{
			if(quantity<=0)
			{
				items.remove(itemid);
			}
			else {
				items.get(itemid).setQuantity(quantity);
			}
		}
	}
	
	public void remove(int itemid)
	{
		items.remove(itemid);
	}
	public Map<Integer,CartItem> getItems()
	{
		return items;
	}
	public void clear()
	{
		items.clear();
	}

}
