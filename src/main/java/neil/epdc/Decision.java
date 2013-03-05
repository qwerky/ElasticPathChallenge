package neil.epdc;

public class Decision {

  private int x;
  private int y;
  private Direction lastDirectionTaken;
  
  public Decision(int x, int y, Direction lastDirectionTaken) {
    this.x = x;
    this.y = y;
    this.lastDirectionTaken = lastDirectionTaken;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ") " + lastDirectionTaken;
  }
  
  public int getX() {
    return x;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public int getY() {
    return y;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public Direction getLastDirectionTaken() {
    return lastDirectionTaken;
  }
  
  public void setLastDirectionTaken(Direction lastDirectionTaken) {
    this.lastDirectionTaken = lastDirectionTaken;
  }
  
  
}
