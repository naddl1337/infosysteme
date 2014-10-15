package main;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import thymio.Thymio;
import context.Map;

public class MainController extends JFrame {
	private static final long serialVersionUID = 1L;
	// private ObserverThread observer; // Brauchen wir erst nÃ¤chste Woche
	/** Die Karte auf der alle Hindernisse eingezeichnet werden*/
	private Map myMap;
	/** Der Thymio mit x und y Koordinate und einer Richtung (später eventuell noch Geschwindigkeit) */
	private Thymio myThymio;

	/**Die x-Ausdehnung der Karte*/
	public static final int MAPSIZE_X = 20;
	/**Die y-Ausdehnung der Karte*/
	public static final int MAPSIZE_Y = 20;
	/**Die Länge und Breite eines quadratischen Feldes auf der Karte*/
	public static final double LENGTH = 10.0;

	// BigDecimal errechnet aus csv Errechnet ins Polynom eingesetzt den
	// dazugehörigen Sensorwert
	private final BigDecimal df_cm1 = new BigDecimal("4.725e+03");
	private final BigDecimal df_cm2 = new BigDecimal("2.202e+2");
	private final BigDecimal df_cm3 = new BigDecimal("-1.897e+02");
	private final BigDecimal df_cm4 = new BigDecimal("2.197e+01");
	private final BigDecimal df_cm5 = new BigDecimal("-7.857e-01");
	private static List<BigDecimal> polynom_funkt_getSensor = new ArrayList<BigDecimal>();

	// BigDecimal errechnet aus csv Errechnet ins Polynom eingesetzt den
	// dazugehörigen Sensorwert
	private static final BigDecimal df_sensor1 = new BigDecimal("-1.829e-01");
	private static final BigDecimal df_sensor2 = new BigDecimal("2.833e-02");
	private static final BigDecimal df_sensor3 = new BigDecimal("-1.935e-05");
	private static final BigDecimal df_sensor4 = new BigDecimal("4.701e-09");
	private static final BigDecimal df_sensor5 = new BigDecimal("-3.937e-13");
	private static List<BigDecimal> polynom_funkt_getCM = new ArrayList<BigDecimal>();

	/**
	 * Im MainController werden Thymio und Karte initialisiert
	 * Außerdem wird hier die Polynomliste initialisiert
	 */
	public MainController() {
		super();
		this.setMinimumSize(new Dimension(400, 400));
		this.pack();

		// Map erstellen und Thymio aufsetzen
		myThymio = new Thymio();
		myMap = new Map(MAPSIZE_X, MAPSIZE_Y, LENGTH);

		initPolynomWertListe();
		getSensorValue();
		getCentimeterValue();
	}

	/**
	 * Hier wird dem Thymio (im Moment noch Random) eine Position auf der Karte 
	 * und eine Richtung in die er schaut zugewiesen
	 */
	public void init() {
		myThymio.setX(0);
		myThymio.setY(0);
		myThymio.setOrientation(0);
	}

	public void run() {
//		/** die Richtung in die der Thymio schaut*/
//		double orientation = myThymio.getOrientation();
//
//		/** Die Steigung die die Linie hat die den mittlersten Sensor repräsentiert*/
//		double m = Math.tan(Math.toRadians(orientation));
//
//		System.out.println("o: " + orientation);
//		System.out.println("m: " + m);
//
//		/** die x Position von dem Thymio*/
//		double y = myThymio.getY();
//		/** die y Position von dem Thymio*/
//		double x = myThymio.getX();
//		/** die Stelle an der die Linie die durch die Steigung m beschrieben wird die y-Achse schneiden würde*/
//		double t = y - m * x;
//		
//		int[] points = calculateEndpointForBresenham(orientation, m, t);
//		
//		//set H_Value of every Mapfield
//		myMap.h_Value(points[0],points[1]); 
//		
//		System.out.println("x: " + x + "   y: " + y + "   t: " + t +" x2: " + points[0] + " y2: "
//				+ points[1]+"\n");
//		myMap.followBeam((int)x, (int)y, points[0], points[1]);
//		
//		myMap.printMap();
		myMap.followBeam(myThymio.getX(), myThymio.getY(), MAPSIZE_X-1, MAPSIZE_Y-1);
		myMap.printMap();
	}

	/**
	 * Hier wird der Endpunkt für den Bresenham Algorithmus berechnet.
	 * Dazu werden alle Sonderfälle einzeln betrachtet.
	 * Initial wird immer x= "Obere X Grenze der Karte" und y="Normale Geradenformel (y=mx+t)" gesetzt.
	 * Nachdem alle Fälle abgearbeitet wurden, werden die errechneten Werte noch zu Integern gerundet.
	 * @1.Fall Thymio schaut nach links und der Punkt an dem die y-Achse von dem Sensor getroffen wird liegt innerhalb der Kartenausdehnung, dann ist y = t und x = 0.
	 * @2.Fall Wenn der Thymio nach links unten (5. und 6. Oktant) oder rechts unten (7. und 8. Oktant) schaut, dann ist x= -t/m und y = 0.
	 * @3.Fall Wenn der Thymio nach rechts oben (3. und 4. Oktant) oder rechts oben (1. und 2. Oktant) schaut, dann ist x= "(Y-Ausdehnung-1 - t)/m und y = "Y-Ausdehnung - 1".
	 * @4.Fall Wenn der Thymio genau nach links schaut, dann ist x= 0 und y= "Y Position vom Thymio".
	 * @5.Fall Wenn der Thymio genau nach oben schaut, dann ist x= "X Position vom Thymio" und y= Obere Grenze der Karte.
	 * @6.Fall Wenn der Thymio genau nach unten schaut, dann ist x= "X Position vom Thymio" und y= 0.
	 * 
	 * @param orientation Die Orientierung des Thymio
	 * @param m die Steigung der Linie die den mittleren Sensor repräsentiert
	 * @param t der y-Achsenabschnitt
	 * @return die x und y Koordinaten der Endpunktes
	 */
	/*private int[] calculateEndpointForBresenham(double orientation, double m, double t) {
		double y2d = m * (MAPSIZE_X - 1) + t;
		double x2d = (MAPSIZE_X - 1);
		if(t >= 0 && t <= (MAPSIZE_Y-1) && orientation < 270 && orientation > 90) {
			y2d = t;
			x2d = 0;
		} else if((orientation > 180 && orientation < 270) || y2d < 0) {
			x2d = (-t/m);
			y2d = 0;
		} else if((orientation < 180 && orientation > 90) || y2d > (MAPSIZE_Y - 1)) {
			x2d = ((MAPSIZE_Y-1)-t)/m;
			y2d = (MAPSIZE_Y-1);
		} else if(orientation == 180) {
			x2d = 0;
			y2d = myThymio.getY();
		} else if(orientation == 90) {
			x2d = myThymio.getX();
			y2d = MAPSIZE_Y-1;
		} else if(orientation == 270) {
			x2d = myThymio.getX();
			y2d = 0;
		}
		
		int y2 = (int) (y2d + 0.5);
		int x2 = (int) (x2d + 0.5);
		int[] res = new int[2];
		res[0] = x2;		
		res[1] = y2;
		return res;
	}*/

	public static void main(String[] args) {
		MainController mc = new MainController();
		mc.init();
		mc.run();
	}
	
	/**
	 * Initialisiere Listen mit Polynomwerten
	 */
	private void initPolynomWertListe() {
		// BigDecimal für Sensor-wert in liste schreiben
		polynom_funkt_getSensor.add(df_cm1);
		polynom_funkt_getSensor.add(df_cm2);
		polynom_funkt_getSensor.add(df_cm3);
		polynom_funkt_getSensor.add(df_cm4);
		polynom_funkt_getSensor.add(df_cm5);
		// BigDecimal für CM-wert in liste schreiben
		polynom_funkt_getCM.add(df_sensor1);
		polynom_funkt_getCM.add(df_sensor2);
		polynom_funkt_getCM.add(df_sensor3);
		polynom_funkt_getCM.add(df_sensor4);
		polynom_funkt_getCM.add(df_sensor5);
	}

	/** 
	 * Hier wird der cm Wert übergeben um den dazugehörigen Sensorwert zu erhalten
	 * Zu berechnende Funktion: BigDecimal cm_wert = df_sensor1*cm + df_sensor1*cm + df_sensor1*cm + df_sensor1*cm + df_sensor1*cm;
	 * Beispiel: wir befinden uns 5cm von einem Gegenstand entfernt. Was wird der Sensor messen? 
	 * Um den Sensorwert zu berechnen, benutzen wir die Funktion getSensorValue()
	 */
	public int getSensorValue() {
		BigDecimal centimeterwert = new BigDecimal("10");
		int senorValue = Integer.valueOf(getDistanceInSensorvalue(
				centimeterwert, polynom_funkt_getSensor).intValue());
		System.out.println("Sensor Wert: " + centimeterwert + " -> "+ senorValue);
		return senorValue;
	}

	/** 
	 * Hier wird der cm Wert übergeben um den dazugehörigen Sensorwert zu erhalten
	 * Funktion: BigDecimal sensor_wert(5) = df_cm1*sensor + df_cm2*sensor + df_cm3*sensor + df_cm4*sensor + df_cm5*sensor;
	 * Beispiel: wir befinden uns 40000 von einem Gegenstand entfernt. Was ist das in cm ausgedrückt? 
	 * Um die cm zu berechnen, benutzen wir die Funktion getCentimeterValue()
	 */
	public int getCentimeterValue() {
		BigDecimal sensorWert = new BigDecimal("4000");
		int centimeterValue = Integer.valueOf(getDistanceInSensorvalue(
				sensorWert, polynom_funkt_getCM).intValue());
		System.out.println("Centimeter Wert: " + sensorWert + " -> "+ centimeterValue);
		return centimeterValue;
	}
	
	/**
	 * @param value
	 * @param hoch
	 * @return
	 * Bsp: value= 5, hoch= 2, return= 25 -> 5^2= 25 
	 */
	private static BigDecimal makePotenzenV(BigDecimal value, double hoch) {
		BigDecimal res = value;
		BigDecimal multiply = value;
		if (hoch == 0)
			return new BigDecimal("1");
		for (int i = 0; i < hoch - 1; i++) {
			multiply = res.multiply(multiply);
		}
		return multiply;
	}

	/**
	 * Beispiele für die zwei berechneten Funktionen:
	 *Funktion: BigDecimal sensor_wert(5) = df_cm1*sensor + df_cm2*sensor + df_cm3*sensor + df_cm4*sensor + df_cm5*sensor;
	 *Funktion: BigDecimal cm_wert = df_sensor1*cm + df_sensor1*cm + df_sensor1*cm + df_sensor1*cm + df_sensor1*cm;
	 */
	public static BigDecimal getDistanceInSensorvalue(BigDecimal value,
			List<BigDecimal> list_of_BigDeziValues) {
		// initial für Sensorberechnung
		BigDecimal result = new BigDecimal("0");
		BigDecimal multiply = new BigDecimal("0");
		for (int item_at = 0; item_at < list_of_BigDeziValues.size(); item_at++) {
			multiply = list_of_BigDeziValues.get(item_at).multiply(
					makePotenzenV(value, item_at));
			result = result.add(multiply);
		}
		int res = Integer.valueOf(result.intValue());
		return result;
	}

}
