package com.spconger.Recipe;

/*
 * This an abstract class. It's purpose
 * is mostly to be a placeholder for 
 * Recipe and Ingredient. It allows me to 
 * use generic methods and apply the 
 * Interface. Remember you can always
 * substitute a child for the parent
 * and as an abstract class, you will
 * always need to use the child.
 */

public abstract class Item {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
