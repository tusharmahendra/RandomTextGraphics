/**
 * A coordinate point in the 2D plane. You need to write the doc comments.
 * 
 * @author Tushar Mahendra
 */
public class Point2D {
  public int x = 0;
  public int y = 0;
  /**
   * 
   * @param a draws the value of x coordinate.
   * @param b draws the value of y coordinate.
   */
  
  Point2D(int a, int b) {
	  x = a;
	  y = b;
  }
  
  void skew45() {
	x+=y;  
  }
  
  void translate(int a, int b) {
	  x+=a;
	  y+=b;
  }
}
