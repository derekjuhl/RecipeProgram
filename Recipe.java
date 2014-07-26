package com.spconger.Recipe;

import java.util.ArrayList;
/*
 * This class defines a recipe. A recipe contains
 * a name, a list of ingredients and 
 * a string of directions. The class inherits from 
 * Item, an abstract class. This is where the name
 * property comes from. It also implements the
 * interface Manage which contains methods for 
 * manipulating the items in the ingredient list
 */

public class Recipe extends Item implements Manage{
	private String directions;
	private ArrayList<Item> ingredients;
	
	public Recipe(){
		ingredients = new ArrayList<Item>();
	}
	
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	

	@Override
	public void addItem(Item i) {
		ingredients.add(i);
		
	}
	@Override
	public void editItem(Item i) {
		
		for(Item item: ingredients){
			if (item.getName().equals(i.getName())){
				ingredients.remove(item);
				ingredients.add((Ingredient)i);
			}
		}
		
	}
	@Override
	public Item getItem(String name) {
		Item item=null;
		for(Item i: ingredients){
			if(i.getName().equals(name)){
				item=i;
			}
		}
		return item;
	}
	@Override
	public ArrayList<Item> getItems() {
		
		return ingredients;
	}
}
