package neil.epdc.ui;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import neil.epdc.CurrentCell;
import neil.epdc.Maze;

public class MazeUI {

  private Maze maze;
  
  private Tile[][] tiles = new Tile[100][100];  
  private JFrame frame;
  private SpringLayout layout = new SpringLayout();
  
  /**
   * Main method.
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    MazeUI ui = new MazeUI();
    ui.launch();
  }
  
  /**
   * Constructor.
   * @throws IOException
   */
  public MazeUI() throws IOException {
    maze = new Maze();
  }
  
  
  public void launch() throws IOException {
   
    frame = new JFrame("Maze");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setSize(600, 600);
    frame.getContentPane().setLayout(layout);
    frame.setVisible(true);
    
    do {
      addTile(maze.getCell());
      maze.step();
    } while (!maze.isSolved());
    
    finish();
  }


  private void addTile(CurrentCell cell) {
    if (tiles[cell.getX()][cell.getY()] == null) {
      Tile tile = new Tile(cell);
      layout.putConstraint(SpringLayout.NORTH, tile, cell.getY()*8, SpringLayout.NORTH, frame);
      layout.putConstraint(SpringLayout.WEST, tile, cell.getX()*8, SpringLayout.WEST, frame);
      tiles[cell.getX()][cell.getY()] = tile;
      frame.getContentPane().add(tile);
      frame.validate();
      frame.repaint();
    } else {
      System.out.println("Cell not added, already drawn: " + cell);
    }
  }
  
  private void finish() {
    FinishDialog dialog = new FinishDialog(frame, maze.getCell(), maze.getMoves());
    dialog.setVisible(true);
    frame.dispose();
  }

}
