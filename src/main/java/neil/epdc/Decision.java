package neil.epdc;

public class Decision {

  private Direction lastDirectionTaken;
  private CurrentCell cell;
  
  public Decision(Direction lastDirectionTaken, CurrentCell cell) {
    this.lastDirectionTaken = lastDirectionTaken;
    this.cell = cell;
  }

  @Override
  public String toString() {
    return "(" + cell.getX() + "," + cell.getY() + ") " + lastDirectionTaken;
  }
  
  public int getX() {
    return cell.getX();
  }
    
  public int getY() {
    return cell.getY();
  }
  
  public Direction getLastDirectionTaken() {
    return lastDirectionTaken;
  }

  public boolean hasUnexploredPaths() {
    return cell.hasUnexploredPaths();
  }
  
}
