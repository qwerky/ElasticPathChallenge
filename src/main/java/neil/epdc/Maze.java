package neil.epdc;

import java.util.ArrayDeque;
import java.util.Deque;

public class Maze {

  private static Deque<Decision> decisions = new ArrayDeque<Decision>();
  
  public static void main(String[] args) throws Exception {
    
    long start = System.currentTimeMillis();
    long moves = 0;
    
    Client client = new Client();
    CurrentCell cell = client.newMaze();
    String guid = cell.getMazeGuid();
    System.out.println("Starting new maze " + guid);
    
    Direction facing = Direction.EAST;
    facing = getDirection(cell, facing);
    assertStart(cell);
    
    while (!cell.isAtEnd()) {
      System.out.print(cell);
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
      }
      moves++;
    }
    
    System.out.println(cell);
    
    long end = System.currentTimeMillis();
    long elapsed = (end-start)/1000;
    System.out.println("Finished in " + moves + " moves and took " + elapsed + " seconds");
    
  }

  /**
   * Returns true if we are currently looking at a dead end.
   * @param cell
   * @param facing
   * @return
   */
  private static boolean isDeadEnd(CurrentCell cell, Direction facing) {
    return cell.countExits() == 1 && !cell.isDirectionAvailable(facing);
  }

  /**
   * Examines the current cell. If it is a junction and
   * a decision needs to be made then it adds a decision
   * to the stack.
   * @param cell
   * @param facing
   */
  private static void checkForDecisions(CurrentCell cell, Direction facing) {
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
  private static Direction getDirection(CurrentCell cell, Direction facing) {
    facing = facing.left();
    while (!cell.isDirectionAvailable(facing)) {
      facing = facing.right();
    }
    return facing;
  }
  

  private static void assertStart(CurrentCell cell) {
    assert cell != null;
    assert cell.getX() == 0;
    assert cell.getY() == 0;
  }

}
