package thymio;

public class Thymio {
	private int x; // x coordinate of Thymio's current position on the map
	private int y; // y coordinate of Thymio's current position on the map
	private double orientation; // Thymio's current orientation with respect to x axis.
	
	public Thymio() {
		x = y = 0;
		orientation = 0.0;
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

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
}
