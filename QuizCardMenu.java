// This class just creates a menu that runs either the QuizCardBuilder class or the QuizCardPlayer class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizCardMenu{

  public static void main (String[] args){
    QuizCardMenu qcm = new QuizCardMenu();
    qcm.go();
  }

  // This method sets up the GUI
  public void go(){
    JFrame frame = new JFrame("Quiz Card Menu");
    JPanel panel = new JPanel();
    JButton builder = new JButton("Quiz Card Builder");
    JButton player = new JButton("Quiz Card PLayer");
    JLabel title = new JLabel("Quiz Cards");

    Font font = new Font("Gill Sans", Font.BOLD, 36);

    title.setFont(font);

    builder.addActionListener(new builderListener());
    player.addActionListener(new playerListener());

    panel.add(title);
    panel.add(builder);
    panel.add(player);

    frame.getContentPane().add(BorderLayout.CENTER, panel);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 150);
    frame.setVisible(true);
  }

  // Inner class for Quiz Card Builder Button
  public class builderListener implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      QuizCardBuilder qcb = new QuizCardBuilder();
      qcb.go();
    }
  }

  // Inner class for Quiz Card Player Button
  public class playerListener implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      QuizCardPlayer qcp = new QuizCardPlayer();
      qcp.go();
    }
  }

}
