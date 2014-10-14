package context;

public class MapElement {
	//TODO Map Element aufsetzen! 
	/**(X/Y Position / besetzt oder nicht? / 
	 * Wahrscheinlichkeit ob es besetzt ist /
	 *  Trifft der Sensor das Element?
	 */
	private int x;
	private int y;
	private int x_length;
	private int y_length; 
	private boolean occupied;
	private boolean onBeam;
	
	/**
	 * Initial start and stopt values. 
	 * This attribute shows how many steps it takes from start to stop point on the map. 
	 * The start point can be everywhere. Now we have to calculate the distance from every 
	 * point to a given stop point and set the value as H value. If the field is occupied, the value is -1
	 * H stands for Heuristic
	 * see tutorial at: http://www.youtube.com/watch?v=KNXfSOx4eEE
	 * 
	 */
	private int h_value = -1; 
	
	
	public MapElement(int x, int y,int x_length, int y_length, boolean occupied) {
		this.x = x;
		this.y = y;
		this.x_length = x_length;
		this.y_length = y_length;
		this.occupied = occupied;
	}

	public boolean onBeam() {
		return onBeam;
	}

	public void setOnBeam(boolean onBeam) {
		this.onBeam = onBeam;
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


	public int getX_length() {
		return x_length;
	}


	public void setX_length(int x_length) {
		this.x_length = x_length;
	}


	public int getY_lenght() {
		return y_length;
	}


	public void setY_lenght(int y_lenght) {
		this.y_length = y_lenght;
	}


	public boolean isOccupied() {
		return occupied;
	}


	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getH_value() {
		return h_value;
	}

	public void setH_value(int h_value) {
		this.h_value = h_value;
	}

	
	

}
