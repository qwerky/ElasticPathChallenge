package neil.epdc;

public class CurrentCell {

  private String mazeGuid;
  private String note;
  private boolean atEnd;
  private boolean previouslyVisited;
  private Path north;
  private Path east;
  private Path south;
  private Path west;
  private int x;
  private int y;
  
  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder();
    
    buf.append("(").append(x).append(",").append(y).append(") ");
    buf.append("Exits ");
    if (!north.equals(Path.BLOCKED)) {
      buf.append("n");
    }
    if (!south.equals(Path.BLOCKED)) {
      buf.append("s");
    }
    if (!east.equals(Path.BLOCKED)) {
      buf.append("e");
    }
    if (!west.equals(Path.BLOCKED)) {
      buf.append("w");
    }
    
    buf.append("    ").append(note);
    
    return buf.toString();
  }
  
  public boolean isDirectionAvailable(Direction facing) {
    switch (facing) {
      case NORTH:
        return !north.equals(Path.BLOCKED);
      case SOUTH:
        return !south.equals(Path.BLOCKED);
      case EAST:
        return !east.equals(Path.BLOCKED);
      case WEST:
        return !west.equals(Path.BLOCKED);
      default:
        return false;
    }
  }
  
  public int countExits() {
    int exitCount = 0;
    if (isDirectionAvailable(Direction.NORTH)) {
      exitCount++;
    }
    if (isDirectionAvailable(Direction.SOUTH)) {
      exitCount++;
    }
    if (isDirectionAvailable(Direction.EAST)) {
      exitCount++;
    }
    if (isDirectionAvailable(Direction.WEST)) {
      exitCount++;
    }
    return exitCount;
  }
  
  public String getMazeGuid() {
    return mazeGuid;
  }
  
  public void setMazeGuid(String mazeGuid) {
    this.mazeGuid = mazeGuid;
  }
  
  public String getNote() {
    return note;
  }
  
  public void setNote(String note) {
    this.note = note;
  }
  
  public boolean isAtEnd() {
    return atEnd;
  }
  
  public void setAtEnd(boolean atEnd) {
    this.atEnd = atEnd;
  }
  
  public boolean isPreviouslyVisited() {
    return previouslyVisited;
  }
  
  public void setPreviouslyVisited(boolean previouslyVisited) {
    this.previouslyVisited = previouslyVisited;
  }
  
  public Path getNorth() {
    return north;
  }
  
  public void setNorth(Path north) {
    this.north = north;
  }
  
  public Path getEast() {
    return east;
  }
  
  public void setEast(Path east) {
    this.east = east;
  }
  
  public Path getSouth() {
    return south;
  }
  
  public void setSouth(Path south) {
    this.south = south;
  }
  
  public Path getWest() {
    return west;
  }
  
  public void setWest(Path west) {
    this.west = west;
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
  
}
