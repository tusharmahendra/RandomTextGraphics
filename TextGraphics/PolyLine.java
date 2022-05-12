/*
* University of the Fraser Valley
* COMP 155 (AB2)
* Name:  Tushar Mahendra
* Student Number:  300156940
* Assignment 2:  Generating random text graphics
*/
public class PolyLine {
	private Screen2D parent;
	private Point2D[] points;
	private char stroke;
	
	PolyLine(Point2D[] pts, char a){
		points = pts;
		stroke = a;
	}
	
		void setParent(Screen2D screen) {
			parent = screen;
			
		}
		
		void draw() {
			for(int i = 0; i < points.length-1; i++) {
				parent.drawLine(points[i], points[(i+1)], stroke);
				
			}
		}
} 