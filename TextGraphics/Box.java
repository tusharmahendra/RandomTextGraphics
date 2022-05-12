/**
 * A shape of a rectangle that can be drawn in its <code>Screen2D parent</code>.
 * 
 * @author Dr Russell Campbell
 */
public class Box {
  private Screen2D parent;
  private Point2D topLeft, bottomRight;
  private char stroke;
  
  /**
   * Create a rectangle shape.
   * 
   * @param x1 Horizontal position of the top-left corner.
   * @param y1 Vertical position of the top-left corner.
   * @param x2 Horizontal position of the bottom-right corner.
   * @param y2 Vertical position of the bottom-right corner.
   * @param a The character used to draw the rectangle.
   */
  Box(int x1, int y1, int x2, int y2, char a) {
	  topLeft = new Point2D(x1, y1);
	  bottomRight = new Point2D(x2, y2);
	  stroke = a;
  }
  
  /**
   * Set the <code>parent</code> to draw inside.
   * 
   * @param screen
   */
  void setParent(Screen2D screen) {
	  parent = screen;
  }
  
  /**
   * Draws the rectangle shape in the parent.
   */
  void draw() {
	  Point2D topRight = new Point2D(bottomRight.x, topLeft.y);
	  Point2D bottomLeft = new Point2D(topLeft.x, bottomRight.y);
	  
	  parent.drawLine(topLeft, topRight, stroke);
	  parent.drawLine(topRight, bottomRight, stroke);
	  parent.drawLine(bottomRight, bottomLeft, stroke);
	  parent.drawLine(bottomLeft, topLeft, stroke);
  }
}
