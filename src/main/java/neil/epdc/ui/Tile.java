package neil.epdc.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import neil.epdc.CurrentCell;
import neil.epdc.Direction;

public class Tile extends JComponent {

  private int size = 8;
  private CurrentCell cell;

  public Tile(CurrentCell cell) {
    this.cell = cell;
    setToolTipText(cell.toString());
  }
  
  
  
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(size, size);
  }



  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, size, size);
    
    g.setColor(Color.BLACK);    
    if (!cell.isDirectionAvailable(Direction.NORTH)) {
      g.drawLine(0, 0, size, 0);
    }
    if (!cell.isDirectionAvailable(Direction.SOUTH)) {
      g.drawLine(0, size-1, size, size-1);
    }
    if (!cell.isDirectionAvailable(Direction.EAST)) {
      g.drawLine(size-1, 0, size-1, size);
    }
    if (!cell.isDirectionAvailable(Direction.WEST)) {
      g.drawLine(0, 0, 0, size);
    }
  }
  
  public CurrentCell getCell() {
    return cell;
  }
  
}
