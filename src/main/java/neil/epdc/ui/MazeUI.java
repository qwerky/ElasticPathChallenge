package neil.epdc.ui;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import neil.epdc.Client;
import neil.epdc.CurrentCell;
import neil.epdc.Decision;
import neil.epdc.Direction;

public class MazeUI {

  private Tile[][] tiles = new Tile[100][100];
  private Client client = new Client();
  private String guid;
  private Direction facing = Direction.EAST;
  private Deque<Decision> decisions = new ArrayDeque<Decision>();
  private CurrentCell cell;
  
  private JFrame frame;
  private SpringLayout layout = new SpringLayout();
  
  private int moves = 0;
  
  public static void main(String[] args) throws IOException {
    MazeUI ui = new MazeUI();
    ui.launch();
  }
  
  
  public void launch() throws IOException {
   
    frame = new JFrame("Maze");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setSize(600, 600);
    frame.getContentPane().setLayout(layout);
    
    cell = client.newMaze();
    guid = cell.getMazeGuid();    
    addTile(cell);
    
    facing = getDirection(cell, facing);
    
    frame.setVisible(true);
    
    while (!cell.isAtEnd()) {
      try {
        if (isDeadEnd(cell, facing)) {
          Decision decision = decisions.pop();
          System.out.println("Dead end, backtracking to " + decision);
          cell = client.backtrack(guid, decision.getX(), decision.getY());
          facing = decision.getLastDirectionTaken().aboutTurn();
        } else {
          facing = getDirection(cell, facing);
          checkForDecisions(cell, facing);
          System.out.println("Moving " + facing);
          cell = client.move(guid, facing);
          addTile(cell);
        }
        moves++;
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
    
    finish();
  }


  public void addTile(CurrentCell cell) {
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
    FinishDialog dialog = new FinishDialog(frame, cell, moves);
    dialog.setVisible(true);
    frame.dispose();
  }
  
  /**
   * Returns true if we are currently looking at a dead end.
   * @param cell
   * @param facing
   * @return
   */
  private boolean isDeadEnd(CurrentCell cell, Direction facing) {
    return cell.countExits() == 1 && !cell.isDirectionAvailable(facing);
  }

  /**
   * Examines the current cell. If it is a junction and
   * a decision needs to be made then it adds a decision
   * to the stack.
   * @param cell
   * @param facing
   */
  private void checkForDecisions(CurrentCell cell, Direction facing) {
    if (cell.countExits() > 2) {
      decisions.push(new Decision(cell.getX(), cell.getY(), facing));
    }
  }

  /**
   * Return the Direction to move, using a turn
   * left first strategy.
   * @param cell
   * @param facing
   * @return
   */
  private Direction getDirection(CurrentCell cell, Direction facing) {
    facing = facing.left();
    while (!cell.isDirectionAvailable(facing)) {
      facing = facing.right();
    }
    return facing;
  }
}
