package com.spconger.Recipe;
import java.util.ArrayList;


public interface Manage {
	void addItem(Item i);
	void editItem(Item i);
	Item getItem(String name);
	ArrayList<Item> getItems();

}
