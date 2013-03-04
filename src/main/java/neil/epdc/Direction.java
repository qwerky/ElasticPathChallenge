package neil.epdc;

public enum Direction {

  NORTH,
  SOUTH,
  EAST,
  WEST;
  
  public Direction left() {
    switch (this) {
      case NORTH:
        return WEST;
      case EAST:
        return NORTH;
      case SOUTH:
        return EAST;
      case WEST:
        return SOUTH;
      default:
        throw new IllegalStateException("Impossible direction: " + this);
    }
  }
  
  public Direction right() {
    switch (this) {
      case NORTH:
        return EAST;
      case EAST:
        return SOUTH;
      case SOUTH:
        return WEST;
      case WEST:
        return NORTH;
      default:
        throw new IllegalStateException("Impossible direction: " + this);
    }
  }
}
