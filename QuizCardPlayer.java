/*
 * This class allows the user to play a set of quiz cards created by
 * the QuizCardBuilder class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class QuizCardPlayer {

	private JFrame frame = new JFrame("QuizCard Player");
	private ArrayList<QuizCard> list = new ArrayList<QuizCard>();
	private JButton next;
	private JTextArea text;
	private int index = 0;

	// This method set up the GUI and "runs" the class
	public void go(){
		JPanel panel = new JPanel();
		text = new JTextArea(12, 30);
		JButton button = new JButton("Show Answer");
		next = new JButton("Start");
		JScrollPane scroller = new JScrollPane(text);
		Font font = new Font("Gill Sans", Font.PLAIN, 24);

		text.setLineWrap(true);
		text.setFont(font);
		text.setEditable(false);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem item = new JMenuItem("Load");

		item.addActionListener(new loadListener());
		button.addActionListener(new buttonListener());
		next.addActionListener(new nextListener());

		menuBar.add(menu);
		menu.add(item);

		panel.add(scroller);
		panel.add(button);
		panel.add(next);

		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(620, 430);
		frame.setVisible(true);
	}

	// Inner class for the load menu option
	class loadListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(frame);
			loadCards(fc.getSelectedFile());
		}
	}

	// Inner class for the show answer button
	class buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			try{
				text.append("\n\n\nThe Answer Is:\n\n" + list.get(index-1).getAnswer());
			}
			catch(IndexOutOfBoundsException e){
				text.setText("You need to load a QuizCard Set First");
			}
		}
	}

	// Inner class for the next button
	class nextListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// This prevents the user from using the player without loading a quiz card set
			if (next.getText().equals("Start")){
				try{
					text.setText(list.get(index).getQuestion());
					next.setText("Next");
					index++;
				}
				catch(IndexOutOfBoundsException e){
					text.setText("You need to load a QuizCard Set First");
				}
			}
			else {
				if (index < list.size()){
					text.setText(list.get(index).getQuestion());
					index++;
				}
				else{
					index = 0;
					text.setText(list.get(index).getQuestion());
					index++;
				}
			}
		}
	}

	// This method loads a set of quiz cards saved as a .txt file by the builder class
	public void loadCards(File file){
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while(line != null){
				String[] aq = line.split("/");
				QuizCard card = new QuizCard(aq[0], aq[1]);
				list.add(card);
				line = br.readLine();
			}
			br.close();
			next.setText("Start");
			text.setText("Card Set Loaded \n\nPress Start to Play");
		}
		catch(Exception e){
			System.out.println("File could no be loaded.");
			e.printStackTrace();
		}
	}

}
