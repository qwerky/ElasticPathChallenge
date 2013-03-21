package neil.epdc;

import java.io.IOException;

public class MazeRunner {

  public static void main(String[] args) throws IOException {

    long start = System.currentTimeMillis();
    Maze maze = new Maze();

    while (!maze.isSolved()) {
      maze.step();
    }

    long end = System.currentTimeMillis();
    long elapsed = (end - start) / 1000;
    maze.printCell();
    System.out.println("Finished in " + maze.getMoves() + " moves and took " + elapsed + " seconds");

  }

}
