package neil.epdc;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Maze {

  private Deque<Decision> decisions = new ArrayDeque<Decision>();
  private int moves = 0;
  private Client client;
  private CurrentCell cell;
  private Direction facing;
  
  /**
   * Constructor.
   * @throws IOException
   */
  public Maze() throws IOException {
    client = new Client();
    cell = client.newMaze();
    facing = Direction.EAST;
    facing = getDirection();
    assertStart();
  }

  
  /**
   * Takes the next step in solving the maze.
   * @throws IOException
   */
  public void step() throws IOException {
    if (isDeadEnd()) {
      Decision decision;
      do {
        decision = decisions.pop();
        System.out.println("Dead end, backtracking to " + decision);
      } while (!decision.hasUnexploredPaths());
      cell = client.backtrack(cell.getMazeGuid(), decision.getX(), decision.getY());
      facing = decision.getLastDirectionTaken().aboutTurn();
    } else {
      facing = getDirection();
      checkForDecisions();
      System.out.println("Moving " + facing);
      cell = client.move(cell.getMazeGuid(), facing);
    }
    moves++;
  }


  /**
   * Returns true if the maze is solved.
   * @return
   */
  public boolean isSolved() {
    return cell.isAtEnd();
  }
  
  /**
   * Returns the number of moves takes so far in the maze.
   * @return
   */
  public int getMoves() {
    return moves;
  }
  
  /**
   * Returns the current cell.
   * @return
   */
  public CurrentCell getCell() {
    return cell;
  }
  
  /**
   * Prints the details of the current cell to the console.
   */
  public void printCell() {
    System.out.println(cell);
  }
  
  /**
   * Returns true if we are currently looking at a dead end.
   * @return
   */
  private boolean isDeadEnd() {
    return cell.countExits() == 1 && !cell.isDirectionAvailable(facing);
  }

  /**
   * Examines the current cell. If it is a junction and
   * a decision needs to be made then it adds a decision
   * to the stack.
   */
  private void checkForDecisions() {
    if (cell.countExits() > 2) {
      decisions.push(new Decision(facing, cell));
      cell.setExplored(facing);
    }
  }

  /**
   * Return the Direction to move, using a turn
   * left first strategy.
   * @return
   */
  public Direction getDirection() {
    facing = facing.left();
    while (!cell.isDirectionAvailable(facing)) {
      facing = facing.right();
    }
    return facing;
  }
  

  private void assertStart() {
    assert cell != null;
    assert cell.getX() == 0;
    assert cell.getY() == 0;
  }

}
