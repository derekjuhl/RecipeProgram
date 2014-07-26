package com.spconger.Recipe;

import java.util.ArrayList;

/*
 * This class stores a collection of recipes
 * in an ArrayList. It implements the Manage
 * interface and so has methods to manipulate
 * the recipe elements in the list
 */
public class Recipes implements Manage {

	private ArrayList<Item> recipes;
	
	public Recipes(){
		recipes=new ArrayList<Item>();
	}
	
	@Override
	public void addItem(Item i) {
		recipes.add(i);
		
	}

	@Override
	public void editItem(Item i) {
	     for(Item item: recipes){
			if (item.getName().equals(i.getName())){
				recipes.remove(item);
				recipes.add((Ingredient)i);
			}
		}
		
	}

	@Override
	public Item getItem(String name) {
		Item item=null;
		for(Item i: recipes){
			if(i.getName().equals(name)){
				item=i;
			}
		}
		return item;
	}

	@Override
	public ArrayList<Item> getItems() {
		
		return recipes;
	}
	
}
