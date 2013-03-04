package neil.epdc;

public class Maze {

  public static void main(String[] args) throws Exception {
    
    Client client = new Client();
    CurrentCell cell = client.newMaze();
    String guid = cell.getMazeGuid();
    
    //We always seem to start at 0,0 with an opening to the east
    Direction facing = Direction.EAST;
    assertStart(cell);
    
    while (!cell.isAtEnd()) {
      System.out.println(cell);
      facing = getDirection(cell, facing);
      cell = client.move(guid, facing);
    }
    
    System.out.println(cell);
    System.out.println("Finished");
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
    assert cell.getNorth() == Path.BLOCKED;
    assert cell.getSouth() == Path.BLOCKED;
    assert cell.getEast() == Path.UNEXPLORED;
    assert cell.getWest() == Path.BLOCKED;
  }

}
