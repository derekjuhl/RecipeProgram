package com.spconger.Recipe;

import java.awt.GridLayout;
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

/*
 * This form takes in a Recipe object
 * through its constructor and 
 * displays its contents
 */

public class RecipeDetailForm {
	
	Recipe recipe;
	
	private JFrame frame;
	private JPanel panel;
	private JLabel recipeLabel;
	private JScrollPane scrollPane;
	private JList<String> list;
	private JTextArea directionsTextArea;
	private JButton buttonClose;
	
	
	public RecipeDetailForm(Recipe r){
		recipe=r;
		createFrame();
	}
	
	private void createFrame(){
		frame = new JFrame();
		frame.setBounds(200,200,250,500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(createPanel());
		frame.setVisible(true);
	}
	
	private JPanel createPanel(){
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		
		recipeLabel=new JLabel(recipe.getName());
		list=new JList<String>();
		scrollPane = new JScrollPane(list);
		fillScrollPane();
		directionsTextArea=new JTextArea();
		directionsTextArea.setLineWrap(true);
		directionsTextArea.setText(recipe.getDirections());
		buttonClose= new JButton("Close");
		buttonClose.addActionListener (new CloseListener());
		panel.add(recipeLabel);
		panel.add(scrollPane);
		panel.add(directionsTextArea);
		panel.add(buttonClose);
		return panel;
		
		
	}
	
	private void fillScrollPane(){
		ArrayList<Item>items=recipe.getItems();
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

	private class CloseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			
		}
		
	}
}
