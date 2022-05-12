import java.util.Arrays;

/**
 * We could have many different instances of 2D char arrays that could display different graphics we design. This class encapsulates (hides) the confusion of working with the 2D char array directly elsewhere in our program. 
 * 
 * @author Dr Russell Campbell
 *
 */
public class Screen2D {
	public final static int WIDTH = 80;
	public final static int HEIGHT = 30;
	private char fill = '`';
	private char[][] panel;

	Screen2D() {
		panel = new char[HEIGHT][WIDTH];
		clear();
	}
	
	/**
	 * This will fill the 2D char array with backtick <code>`</code> characters (usually under the tilde ~ in the top-left corner of a keyboard).
	 */
	void clear() {
		// every character in the screen is set to a default char
		for (int i = 0; i < panel.length; i++) {
			Arrays.fill(panel[i], fill);
		}
	}

	/**
	 * This method swaps the order of the <code>x</code> and <code>y</code> values for us (so we don't have to swap them everywhere else in our code). 
	 * 
	 * @param x The horizontal position inside the 2D char array.
	 * @param y The vertical position inside the 2D char array.
	 * @return
	 */
	char get(int x, int y) {
		return panel[y][x];
	}

	/**
	 * Similar to the <code>get</code> method, but assign a char value to an <code>x</code> and <code>y</code> position in the 2D char array.
	 * 
	 * @param x
	 * @param y
	 * @param c
	 */
	void set(int x, int y, char c) {
		// check if the point is outside the screen boundaries
		if (0 <= x && x < WIDTH && 0 <= y && y < HEIGHT)
			panel[y][x] = c;
	}
	
	/**
	 * Actually print the 2D char array to output.
	 */
	void display() {
		StringBuilder output = new StringBuilder("");
		for (int y = HEIGHT - 1; y >= 0; y--) {
			for (int x = 0; x < WIDTH; x++) {
				output.append(get(x, y));
			}
			output.append('\n');
		}
		System.out.print(output);
	}
	
	/**
	 * Avoids using floating-point arithmetic for a faster drawing of a line. Feel free to ask me about the math and why it works.
	 * 
	 * @param first The start position of the straight line.
	 * @param second The end position of the straight line.
	 * @param stroke The character used to draw the line.
	 */
	void drawLine(Point2D first, Point2D second, char stroke) {

		// Bresenham's Line Algorithm
		int x1 = first.x;
		int y1 = first.y;
		int x2 = second.x;
		int y2 = second.y;
		byte stepx, stepy;

		int dx = x2 - x1;
		int dy = y2 - y1;

		// Simplify keeping track of distance by removing direction (sign)
		// from the amount of changes in position for each step.
		// Let the direction be taken care of with step variables.
		if (dy < 0) { dy = -dy; stepy = -1; } else { stepy = 1; }
		if (dx < 0) { dx = -dx; stepx = -1; } else { stepx = 1; }
		dy <<= 1; // dy is now 2*dy
		dx <<= 1; // dx is now 2*dx
		set(x1, y1, stroke);

		// the algorithm is simplified by ensuring slope m is always -1 < m < 1
		if (dx > dy) {
			int error = dy - (dx >> 1);
			while (x1 != x2) {
				x1 += stepx;
				error += dy;
				if (error >= 0) {
					y1 += stepy;
					error -= dx;
				}
				set(x1, y1, stroke);
			}
		} else { // but this means we may have to swap roles for dy and dx
			int error = dx - (dy >> 1);
			while (y1 != y2) {
				y1 += stepy;
				error += dx;
				if (error >= 0) {
					x1 += stepx;
					error -= dy;
				}
				set(x1, y1, stroke);
			}
		}
	} // end of drawLine method
}
