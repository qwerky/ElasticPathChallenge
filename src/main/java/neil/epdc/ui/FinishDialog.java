package neil.epdc.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import neil.epdc.CurrentCell;

public class FinishDialog extends JDialog implements ActionListener {
  
  public FinishDialog(JFrame frame, CurrentCell cell, int moves) {
    super(frame, "Exit found", true);
    setSize(400, 300);

    add(new JLabel("The exit of the maze has been found in " + moves + " moves. The note says;"), BorderLayout.NORTH);
    
    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setText(cell.getNote());
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane, BorderLayout.CENTER);
    
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(this);
    add(closeButton, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e) {
    dispose();
  }

}
