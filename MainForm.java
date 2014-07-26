package com.spconger.Recipe;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * This class implements the main form
 * It consists of a a Jframe containing
 * a panel using a border layout. The border panel
 * contains a scrollpanel to show the list of recipes,
 * a grid panel to get the recipe information
 * and a button panel to contain form buttons.
 * The form lets you enter a recipe name,
 * then enter as many ingredients as necessary.
 * There is a button that adds each ingredient
 * and then clears the ingredient text boxes.
 * When you are done entering ingredients,
 * you can enter the directions and then save
 * the recipe. There is also a button to populate
 * the list with recipes entered so far. 
 * The recipes are not saved to file or database
 * and so only exist in memory while the program runs.
 * If you select a recipe from the list
 * it opens a second form. It passes the selected
 * recipe to the second form through the second
 * form's constructor.
 */

public class MainForm {

	//declare all the form objects
	private JFrame frame;
	private JList<String> list;
	private JScrollPane scrollPane;
	private JPanel borderPanel;
	private JPanel gridPanel;
	private JPanel buttonPanel;
	private JLabel recipeNameLabel;
	private JLabel ingredientNameLabel;
	private JLabel caloriesLabel;
	private JLabel fatLabel;
	private JLabel proteinLabel;
	private JLabel directionsLabel;
	private JTextField recipeNameTextField;
	private JTextField ingredientNameTextField;
	private JTextField caloriesTextField;
	private JTextField fatTextField;
	private JTextField proteinTextField;
	private JTextArea directionsTextArea;
	private JButton buttonAddIngredient;
	private JLabel placeLabel;
	private JButton buttonAddRecipe;
	private JButton buttonFillList;
	private JButton buttonExit;
	
	//declare some class level variables
	private Recipes recipes;
	private ArrayList<Item> ingredients;
	

	
	public MainForm(){
		//Initialize the frame and classes
		createJFrame();
		recipes = new Recipes();
		ingredients=new ArrayList<Item>();
	}
	
	private void createJFrame(){
		frame = new JFrame();
		frame.setBounds(200,200,550,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Recipe App");
		frame.add(createBorderPanel());
		frame.setVisible(true);
	}
	
	//create the border panel
	private JPanel createBorderPanel(){
		borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		borderPanel.add(createScrollPane(),BorderLayout.WEST);
		borderPanel.add(createGridPanel(), BorderLayout.EAST);
		borderPanel.add(createButtonPanel(),BorderLayout.SOUTH);
		return borderPanel;
	}
	
	private JScrollPane createScrollPane(){
	//create the scrollpane and add the list to it
		list = new JList<String>();
		//add the selection listener to the list
		list.addListSelectionListener(new SelectionListener());
		scrollPane = new JScrollPane(list);
		return scrollPane;
	}
	
	private JPanel createGridPanel(){
		//create the main grid panel. this is the panel that
		//that allows you to enter the recipe and ingredients
		//it contains the buttonAddIngredient and its listener
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(7,2));
		recipeNameLabel=new JLabel("Enter Recipe Name");
		recipeNameTextField=new JTextField();
		ingredientNameLabel = new JLabel("Enter Ingredient Name");
		ingredientNameTextField = new JTextField();
		caloriesLabel=new JLabel("Calories");
		caloriesTextField = new JTextField();
		fatLabel=new JLabel("Fat Grams");
		fatTextField = new JTextField();
		proteinLabel = new JLabel("Protein Grams");
		proteinTextField=new JTextField();
		buttonAddIngredient = new JButton("Add Ingredient");
		buttonAddIngredient.addActionListener(new IngredientListener());
		placeLabel=new JLabel();
		directionsLabel=new JLabel("Directions");
		directionsTextArea = new JTextArea();
		directionsTextArea.setLineWrap(true);
		
		
		
		gridPanel.add(recipeNameLabel);
		gridPanel.add(recipeNameTextField);
		gridPanel.add(ingredientNameLabel);
		gridPanel.add(ingredientNameTextField);
		gridPanel.add(caloriesLabel);
		gridPanel.add(caloriesTextField);
		gridPanel.add(fatLabel);
		gridPanel.add(fatTextField);
		gridPanel.add(proteinLabel);
		gridPanel.add(proteinTextField);
		gridPanel.add(placeLabel);
		gridPanel.add(buttonAddIngredient);
		gridPanel.add(directionsLabel);
		gridPanel.add(directionsTextArea);
		
		return gridPanel;
		
	}
	
	private JPanel createButtonPanel(){
		//this adds buttons to the button panel
		//and their listeners. It contains a button
		//to add the recipe, a button to fill the list,
		//and a button to exit the program
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonAddRecipe = new JButton("Add Recipe");
		buttonAddRecipe.addActionListener(new AddRecipeListener());
		buttonFillList=new JButton("GetRecipes");
		buttonFillList.addActionListener(new FillListListener());
		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ExitListener());
		buttonPanel.add(buttonAddRecipe);
		buttonPanel.add(buttonFillList);
		buttonPanel.add(buttonExit);
		
		return buttonPanel;
	}
	
	private class ExitListener implements ActionListener{
		//listener for exiting the program
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
		
	}
	
	private class FillListListener implements ActionListener{
		//this method populates the list with the names
		//of the recipes entered
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Item>items=recipes.getItems();
			//set the default model for the list object
			//our list will contain Strings
			DefaultListModel<String> model = new DefaultListModel<String>();
			//loop through the arrayList
			for(Item i : items){
				//add the item names to the list
				model.addElement(i.getName());
			}
			list.setModel(model);
		}
		
	}
	private class AddRecipeListener implements ActionListener{

		//this method adds a recipe to the recipes class with
		//its arrayList of recipes
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Recipe r = new Recipe();
			r.setName(recipeNameTextField.getText());
			r.setDirections(directionsTextArea.getText());
			
			for(Item i: ingredients){
				Ingredient ingredient = (Ingredient)i;
				r.addItem(ingredient);
			}
			
			recipes.addItem(r);
			recipeNameTextField.setText("");
			directionsTextArea.setText("");
			ingredients.clear(); //I need to clear the ingredients
			//for the next recipe
			
		}
		
		
	}
	
	private class IngredientListener implements ActionListener{

		//this method adds ingredients to the ingredientlist
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Ingredient i=new Ingredient();
			i.setName(ingredientNameTextField.getText());
			i.setCalories(Integer.parseInt(caloriesTextField.getText()));
			i.setFat(Integer.parseInt(fatTextField.getText()));
			i.setProtein(Integer.parseInt(proteinTextField.getText()));
			ingredients.add(i);
			ingredientNameTextField.setText("");
			caloriesTextField.setText("");
			fatTextField.setText("");
			proteinTextField.setText("");
			
			
		}
		
	}
	private class SelectionListener implements ListSelectionListener{
		//this listener takes the name from the list
		//when a user selects it, gets the recipe
		//associated with that name and passes that recipe
		//to the RecipeDetailForm through its constructor
		//so it can display it
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (list.getSelectedIndex() != -1) {
		        //No selection, disable fire button.
		           String name=list.getSelectedValue();
		           Recipe r = (Recipe)recipes.getItem(name);
		           RecipeDetailForm detail = new RecipeDetailForm(r);
		           

			
		}
		
	}
	}
}

