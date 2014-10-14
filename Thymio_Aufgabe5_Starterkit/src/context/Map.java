package context;

import java.util.ArrayList;
import java.util.Random;

public class Map {
	
	/** die Anzahl der Elemente auf der Karte in x Richtung.*/
	private int sizeX;
	/** die Anzahl der Elemente auf der Karte in x Richtung.*/
	private int sizeY;
	
	/** ein Feld auf der Karte */
	private MapElement [][] element; // Array of MapElement representing the environment
	private double edgelength; // each element in this maps covers edgelength^2 square units.
	
	/** X Position des Thymio*/
	private int thyx;
	/** Y Position des Thymio*/
	private int thyy;
	
	/** Anzahl der belegten Karten Felder*/
	public static final int N = 20; // number of occupied elements
	
	
	/**
	 * Instructor. Setzt alle Werte initial.
	 * @param x X Ausdehnung der Karte
	 * @param y Y Ausdehnung der Karte
	 * @param l L‰nge der Kante eines Feldes
	 */
	public Map(int x, int y, double l) {
		//Zuweisung der Variablen
		sizeX = x;
		sizeY = y;
		edgelength = l;
		element = new MapElement[x][y];
		
		initMap();
	}
	
	/**
	 * Initialisierung der Karte. Zuerst werden alle Felder mit 0 initialisiert.
	 * Anschlieﬂend werden N beliebige Felder ausgew‰hlt die belegt werden sollen.
	 * Die Entsprechenden Felder werden als belegt gekennzeichnet.
	 */
	private void initMap() {
		Random r = new Random();
		ArrayList<Integer> occupiedElements = new ArrayList<Integer>();
		int numOfElements = sizeX * sizeY;
		// initialize each element of the map
		for (int i = 0; i < numOfElements; i++) {
			occupiedElements.add(0);
		}
		// collect N distinct random numbers between 0 and the max number of
		// MapElements in this Map
		// find MapElement corresponding to each of the numbers and set its
		// state to occupied
		for (int i = 0; i < N; i++) {
			int occupied = r.nextInt(numOfElements);	
			if(occupiedElements.get(occupied)!=1)
			occupiedElements.set(occupied, 1);
			else {
				i--;
			}
		}

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				int dinge = occupiedElements.get(y + x * sizeY);
				if (dinge == 1)
					element[x][y] = new MapElement(x, y,10, 10,true);
				else {
					element[x][y] = new MapElement(x, y,10, 10,false);
				}
			}
		}
	}
	
	/**
	 * Hier wird die Karte als Console Ausgabe ausgegeben.
	 */
	public void printMap() {
		String str;
		System.out.print("   "); for (int x = 0; x < sizeX; x++) {
			str = String.valueOf(x); while(str.length()<2){str = "0" + str;}str=str+"  ";
			System.out.print(str);
		}
		System.out.print("\n");
		for (int y = sizeY-1; y >= 0; y--) {
			str = String.valueOf(y); while(str.length()<2){str = "0" + str;}str=str+" ";
			System.out.print(str);
			for (int x = 0; x < sizeX; x++) {
				MapElement e = element[x][y];
				
				if(thyy == y && thyx == x) {
					System.out.print("T");
				} else {
					System.out.print(e.isOccupied() ? "X" : "O");					
				}
				System.out.print(e.onBeam() ? "B" : "-");
				System.out.print("  ");
			}
			
			System.out.print("\n");
		}
	}
	
	/**
	 * 
	 * Bresenham Algorithmus. Hier wird der Sensor (Beam) auf der Karte eingezeichnet.
	 * @param x X Wert des Start Punktes (Aktueller Standpunkt des Thymio)
	 * @param y Y Wert des Start Punktes (Aktueller Standpunkt des Thymio)
	 * @param x2 X Wert des Ziel Punktes
	 * @param y2 Y Wert des Ziel Punktes
	 */
	public void followBeam( int x, int y, int x2, int y2 ) {
		thyx = x;
		thyy = y;
		
	    int w = x2 - x ;
	    int h = y2 - y ;
	    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
	    if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
	    if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
	    if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
	    int longest = Math.abs(w) ;
	    int shortest = Math.abs(h) ;
	    if (!(longest>shortest)) {
	        longest = Math.abs(h) ;
	        shortest = Math.abs(w) ;
	        if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
	        dx2 = 0 ;            
	    }
	    int numerator = longest >> 1 ;
	    for (int i=0;i<=longest;i++) {
			element[x][y].setOnBeam(true);
	        numerator += shortest ;
	        if (!(numerator<longest)) {
	            numerator -= longest ;
	            x += dx1 ;
	            y += dy1 ;
	        } else {
	            x += dx2 ;
	            y += dy2 ;
	        }
	    }
	}
	
	
	public void h_Value(int stopPosThymio_X, int stopPosThymio_Y){
//		element
	}
	
}
