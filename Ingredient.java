package com.spconger.Recipe;

/*
 * This class defines the type Ingredient.
 * It inherits from Item and so has a 
 * name property as well. By inheriting from
 * Item I can use it in the generic methods
 * of the Manage interface
 */
public class Ingredient extends Item{
	private int calories;
	private int fat;
	private int protein;
	
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getFat() {
		return fat;
	}
	public void setFat(int fat) {
		this.fat = fat;
	}
	public int getProtein() {
		return protein;
	}
	public void setProtein(int protein) {
		this.protein = protein;
	}
}
