//Copyright (C) 2015 CarbideWolf

//This program is free software: you can redistribute it and/or modify it under the terms of version 3 of the GNU General Public License as published by the Free Software Foundation.
//The program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//See the GNU General Public License for more details.
//You should  have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/

package com.carbidewolf;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame
{
	//The window
	static JFrame frame = new JFrame();
	
	//An array of algorithms
	String[] algorithms = {"Haval", "MD2", "MD4", "MD5", "RipeMD128", "RipeMD160", "Tiger", "SHA160", "SHA256", "SHA384", "SHA512", "Whirlpool"};
	
	//The labels
	JLabel passwordLabel = new JLabel("Password:");
	JLabel algorithmLabel = new JLabel("Algorithm:");
	JLabel hashLabel = new JLabel("Hash:");
	
	//The input and output text boxes
	JPasswordField passwordField = new JPasswordField(20);
	static JTextArea hashField = new JTextArea(4, 20);
	
	//The drop down box
	JComboBox<String> algorithmBox = new JComboBox<String>(algorithms);
	
	//The button
	JButton hashButton = new JButton("Hash");
	
	//Some colours
	Color bgColor = new Color(39, 39, 39);
	Color compColor = new Color(165, 165, 165);
	
	//A container to hold the other components
	Container contentPane = frame.getContentPane();
	
	public Window()
	{
		//Get the resolution of the screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		//Dimension of the window
		Dimension size = new Dimension(480, 240);
		
		//Put the middle of the window in the middle of the screen
		int x = (int) ((dim.getWidth() / 2) - (size.getWidth() / 2));
		int y = (int) ((dim.getHeight() / 2) - (size.getHeight() / 2));
		
		//Window attributes
		frame.setSize(size);
		frame.setLocation(x, y);
		frame.setTitle("Password Hasher by CarbideWolf");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Set the label colours
		passwordLabel.setForeground(Color.white);
		algorithmLabel.setForeground(Color.white);
		hashLabel.setForeground(Color.white);
		
		//Set the background colours of the components
		passwordField.setBackground(compColor);
		algorithmBox.setBackground(compColor);
		hashField.setBackground(compColor);
		hashButton.setBackground(compColor);
		
		//Make the text in the output box go to a new line if it reaches the edge of the box
		hashField.setLineWrap(true);
		
		//Layout manager object
		SpringLayout layout = new SpringLayout();
		
		//layout.putConstraint(part of, component, this far away from, part of, component/container);
		
		//Input box and it's label layout constraints
		layout.putConstraint(SpringLayout.WEST, passwordField, -(contentPane.getWidth() / 6), SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordField, -((contentPane.getHeight() / 5) * 2), SpringLayout.VERTICAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.EAST, passwordLabel, -10, SpringLayout.WEST, passwordField);
		layout.putConstraint(SpringLayout.SOUTH, passwordLabel, -3, SpringLayout.SOUTH, passwordField);
		
		//Drop down box and it's label layout constraints
		layout.putConstraint(SpringLayout.WEST, algorithmBox, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, algorithmBox, -((contentPane.getHeight() / 5) + 4), SpringLayout.VERTICAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.EAST, algorithmLabel, -10, SpringLayout.WEST, algorithmBox);
		layout.putConstraint(SpringLayout.SOUTH, algorithmLabel, -3, SpringLayout.SOUTH, algorithmBox);
		
		//Output box and it's label layout constraints
		layout.putConstraint(SpringLayout.WEST, hashField, (-(contentPane.getWidth() / 6)) + 2, SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, hashField, 10, SpringLayout.VERTICAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.EAST, hashLabel, -10, SpringLayout.WEST, hashField);
		layout.putConstraint(SpringLayout.NORTH, hashLabel, 2, SpringLayout.NORTH, hashField);
		
		//Button layout constraints
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hashButton, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, hashButton, -20, SpringLayout.SOUTH, contentPane);
		
		//Button listener object
		ButtonListener buttonListener = new ButtonListener();
		
		//Add button listener to the button
		hashButton.addActionListener(buttonListener);
		
		//Set the background colour and layout of the component container
		contentPane.setBackground(bgColor);
		contentPane.setLayout(layout);
		
		//When the window opens put the cursor in the input box
		passwordField.requestFocus(true);
		
		//Add the components to the container
		contentPane.add(passwordLabel);
		contentPane.add(algorithmLabel);
		contentPane.add(hashLabel);
		contentPane.add(passwordField);
		contentPane.add(algorithmBox);
		contentPane.add(hashField);
		contentPane.add(hashButton);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == hashButton)
			{
				//Get the contents of the input box
				char[] passwordArray = passwordField.getPassword();
				
				//String to hold the input
				String password = "";
				
				//Populate the string from the character array
				for(int i = 0; i < passwordArray.length; i++)
				{
					password = password + passwordArray[i];
				}
				
				//Integer to tell the selector method which algorithm to use
				int algorithm = algorithmBox.getSelectedIndex();
				
				//Call the selector method
				Main.selector(password, algorithm);
			}
		}
	}
}