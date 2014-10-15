package context;

public class MapElement {
	/*(X/Y Position / besetzt oder nicht? / 
	 * Wahrscheinlichkeit ob es besetzt ist /
	 *  Trifft der Sensor das Element?
	 */
	
	 /** x Position des Feldes*/
	 private int x;
	 /** y Position des Feldes*/
	private int y;
	 /** x Ausdehnung des Feldes*/
	private int x_length;
	 /** y Ausdehnung des Feldes*/
	private int y_length; 
	 /** true wenn das Feld besetzt ist, sonst false*/
	private boolean occupied;
	private boolean onBeam;
	
	/**
	 * der Constructor. Hier werden die Daten des Feldes initial gesetzt.
	 * @param x Position des Feldes
	 * @param y Position des Feldes
	 * @param x_length x Ausdehnung des Feldes
	 * @param y_length y Ausdehnung des Feldes
	 * @param occupied true wenn das Feld besetzt ist, sonst false
	 */
	public MapElement(int x, int y,int x_length, int y_length, boolean occupied) {
		this.x = x;
		this.y = y;
		this.x_length = x_length;
		this.y_length = y_length;
		this.occupied = occupied;
	}
	
	/**
	 * gibt zurück, ob das Feld auf dem Strahl liegt oder nicht
	 * @return Boolean, die true ist wenn das Feld auf dem Strahl liegt. Sonst false.
	 */
	public boolean onBeam() {
		return onBeam;
	}

	/**
	 * Hier wird festgelegt ob das Feld auf dem Strahl liegt oder nicht
	 * @param onBeam Boolean, mit welcher festgelegt wird ob das Feld auf dem Strahl liegt
	 */
	public void setOnBeam(boolean onBeam) {
		this.onBeam = onBeam;
	}
	
	/**
	 * gibt den X Wert des Feldes zurück
	 * @return der X Wert des Feldes
	 */
	public int getX() {
		return x;
	}

	/**
	 * setzt das Feld auf einen anderen X Wert.
	 * @param x der Wert auf den das Feld gesetzt werden soll
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * gibt den Y Wert des Feldes zurück
	 * @return der Y Wert des Feldes
	 */
	public int getY() {
		return y;
	}

	/**
	 * setzt das Feld auf einen anderen Y Wert.
	 * @param y der Wert auf den das Feld gesetzt werden soll
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gibt die X Länge eines Feldes zurück
	 * @return die X Länge eines Feldes
	 */
	public int getX_length() {
		return x_length;
	}

	/**
	 * setzt die Länge eines Feldes in X Richtung.
	 * @param x_lenght die Länge in cm.
	 */
	public void setX_length(int x_length) {
		this.x_length = x_length;
	}

	/**
	 * Gibt die Y Länge eines Feldes zurück
	 * @return die Y Länge eines Feldes
	 */
	public int getY_lenght() {
		return y_length;
	}

	/**
	 * setzt die Länge eines Feldes in Y Richtung.
	 * @param y_lenght die Länge in cm.
	 */
	public void setY_lenght(int y_lenght) {
		this.y_length = y_lenght;
	}

	/**
	 * Liefert eine Boolean, ist diese true ist das Feld besetzt, sonst ist es frei
	 * @return Ist das Feld besetzt oder nicht?
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Setzt ein Feld auf besetzt oder frei
	 * @param occupied eine Boolean, bei true wird das Feld besetzt, sonst nicht
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}
