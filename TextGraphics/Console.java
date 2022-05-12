import java.util.ArrayList;
import java.util.HashSet;

/**
 * This program will generate some random patterns drawn inside a 2D char array.
 * 
 * @author Dr Russell Campbell
 */
public class Console {
	static int scale = 3;
	static int tilesHeight = Screen2D.HEIGHT / scale;
	static int tilesWidth  = Screen2D.WIDTH / scale;
	static char[] colours = new char[] {' ', '.', '+', '*', '#'};
	// for walking
	static boolean[][] visited = new boolean[tilesWidth][tilesHeight];
	static ArrayList<Point2D> walk = new ArrayList<Point2D>();
	
	/**
	 * Program execution starts here. This method, when complete, outputs two 2D char arrays, one of random tiles, and the other of skewed random walks.
	 * 
	 * @param args The usual command line arguments.
	 */
	public static void main(String[] args) {

		Screen2D window = new Screen2D();
		
		HashSet<Box> tiles = new HashSet<Box>();
		
		// create a checkerboard pattern with 5 x 5 squares
		int colour;
		for (int j = 0; j < tilesHeight; j++) {
			for (int i = 0; i < tilesWidth; i++) {
				colour = Random.rand(i + j * tilesWidth, 5);
				tiles.add( new Box(i*scale, j*scale + (scale-1), i*scale + (scale-1), j*scale, colours[colour]) );
			}
		}
		
		window.clear();
		// an enhanced for loop example
		for (Box box : tiles) {
			box.setParent(window); // the box needs to know which Screen2D to draw on
			box.draw();
		}
		window.display();

		System.out.println();
		
		window.clear();
  
  // add your code here	
		
		Console.randomPattern(window);
		
	}
	
	/**
	 * This will generate a recursive 
	 *
	 * @param window Object created to access Screen2D class's methods and variables.
	 */
	static void randomPattern(Screen2D window) {
		while(notFull() != false) {
			walk.clear();
			Point2D start = getFreePoint();
			recursiveSelfAvoidingWalk(0,start.x,start.y);
			for(Point2D pt: walk) {
				int i = tilesHeight;
				i=i/2;
				int	j = tilesWidth;
				j=j/2;
				pt.translate(-i, -j);
				pt.skew45();
				pt.translate(i, j);
				pt.translate(scale/2, scale/2);
			}
			
			Point2D[] pts = walk.toArray(new Point2D[] {});
			PolyLine myWalk = new PolyLine(pts,'#');
			myWalk.setParent(window);
			myWalk.draw();
			window.display();	
			
		}	
	}
	
	
	
	/**
	 * This will generate one random self-avoiding walk within the boundary of the tile dimensions.
	 * 
	 * @param level This keeps track of the number of recursive calls on the stack (to help with debugging).
	 * @param i The horizontal tile position to start a random walk.
	 * @param j The vertical tile position to start a random walk.
	 */
	static void recursiveSelfAvoidingWalk(int level, int i, int j) {
		visited[i][j] = true;
		walk.add(new Point2D(i*scale, j*scale));
		
		// check the adjacent positions for those that are unvisited
		ArrayList<Point2D> choices = new ArrayList<Point2D>();
		for (byte x = -1; x < 2; x += 2) {
			// don't walk outside the panel boundary
			if (i == 0 && x == -1) continue;
			else if (i == tilesWidth-1 && x == 1) break;

			if (!visited[i+x][j]) {
			  choices.add(new Point2D(i+x, j));
			}
		}

		for (byte y = -1; y < 2; y += 2) {
			if (j == 0 && y == -1) continue;
			else if (j == tilesHeight-1 && y == 1) break;
			
			if (!visited[i][j+y]) {
			  choices.add(new Point2D(i, j+y));
			}
		}
		
		if (!choices.isEmpty()) {
			int nChoices = choices.size();
			int choice = Random.rand(i + j*tilesHeight, nChoices);
			Point2D next = choices.get(choice);
			// recurse
			recursiveSelfAvoidingWalk(level + 1, next.x, next.y);
		}
		// if execution reaches this point, there are no choices left
		// so, stop walking
	}
	
	/**
	 * Tests whether there is an unused tile position.
	 * 
	 * @return A boolean value returning <code>true</code> if there is an unused tile position. 
	 */
	static boolean notFull() {
		for (int i = 0; i < tilesWidth; i++) {
			for (int j = 0; j < tilesHeight; j++) {
				if (visited[i][j] == false)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * This method will search for the bottom-left-most unused tile position and return it as an instance of a <code>Point2D</code>.
	 * 
	 * @return A <code>Point2D</code> instance with the coordinates of an unused tile position.
	 */
	static Point2D getFreePoint() {
		for (int i = 0; i < tilesWidth; i++) {
			for (int j = 0; j < tilesHeight; j++) {
				if (visited[i][j] == false)
					return new Point2D(i, j);
			}
		}
		return null;
	}
}
