/*
 * Class that allows the user to create a set of quiz cards
 * and save them as a .txt file that can be read by the QuizCardPlayer class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class QuizCardBuilder {

	private JFrame frame;
	private JTextArea qArea;
	private JTextArea aArea;
	private ArrayList<QuizCard> list = new ArrayList<QuizCard>();

	// This method sets up the GUI and "runs" the class
	public void go(){
		frame = new JFrame("Quiz Card Builder");
		JPanel panel = new JPanel();

		JLabel qLabel = new JLabel("Question:");
		JLabel aLabel = new JLabel("Answer:");

		qArea = new JTextArea(10, 20);
		aArea = new JTextArea(10, 20);

		JScrollPane qScroller = new JScrollPane(qArea);
		JScrollPane aScroller = new JScrollPane(aArea);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem saveItem = new JMenuItem("Save");

		JButton button = new JButton("Next Card");

		Font font = new Font("Gill Sans", Font.BOLD, 24);

		newItem.addActionListener(new newListener());
		saveItem.addActionListener(new saveListener());
		button.addActionListener(new buttonListener());

		fileMenu.add(newItem);
		fileMenu.add(saveItem);
		menuBar.add(fileMenu);

		qArea.setLineWrap(true);
		aArea.setLineWrap(true);
		qArea.setFont(font);
		aArea.setFont(font);
		qArea.setWrapStyleWord(true);
		aArea.setWrapStyleWord(true);

		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panel.add(qLabel);
		panel.add(qScroller);
		panel.add(aLabel);
		panel.add(aScroller);
		panel.add(button);

		frame.setJMenuBar(menuBar);
		frame.setResizable(false);
		frame.getContentPane().add(BorderLayout.CENTER, panel);;
		frame.setSize(520, 730);
		frame.setVisible(true);

	}

	// Inner class for the save menu option
	class saveListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// JFileChooser lets the user browse through its computer to select a path
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showSaveDialog(frame);
			File file = fileChooser.getSelectedFile();

			saveCardSet(file);
		}
	}

	// Inner class for the new menu option
	class newListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			list.clear();
			clearCard();
		}
	}

	// Inner class for the next card button
	class buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//
			String q = qArea.getText();
			String a = aArea.getText();
			list.add(new QuizCard(q, a));
			clearCard();
		}
	}

	// This method just empties both text fields and sets the cursor to the question field
	public void clearCard(){
		qArea.setText("");
		aArea.setText("");
		qArea.requestFocus();
	}

	// This method saves the current set of quiz cards into a .txt file
	public void saveCardSet(File file){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(QuizCard card: list){
				bw.write(card.getQuestion() + "/");
				bw.write(card.getAnswer() + "\n");
			}
			bw.close();
		}
		catch(Exception e){
			System.out.println("Could not save file.");
			e.printStackTrace();
		}
	}

}
