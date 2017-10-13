package etbcor.bluetoothgame.util;

import static java.lang.Math.*;

public class Point {

	private float x, y;
	
	public Point(float a, float b) {
		this.x = a;
		this.y = b;
	}
	
	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float distanceToPoint(Point p) {
		return (float)sqrt(((p.getX() - this.x)*(p.getX() - this.x))+((p.getY() - this.y)*(p.getY() - this.y)));
	}
	
}