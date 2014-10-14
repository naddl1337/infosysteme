package logger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DataPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Double> datavals;
	private double minValue, maxValue;
	public DataPanel() {
		super();
		datavals = new ArrayList<Double>();
		
		maxValue = Double.NEGATIVE_INFINITY;
		minValue = Double.POSITIVE_INFINITY;
		
		this.setMinimumSize(new Dimension(600,100));
	}
	
	public void add(double val) {
		datavals.add(val);
		
		if (val < minValue) minValue = val;
		if (val > maxValue) maxValue = val;
		
		System.out.println(val > maxValue);
	}
	
	public void paint(Graphics g) {
		double ext = Math.abs(maxValue - minValue) + 1; // avoid division by zero.
		double pixelExt = this.getHeight();
		int i = 0;
		
		System.out.println("data panel paint: " + datavals.size() + " min: " + minValue + " max: " + maxValue);
		
		g.setColor(Color.WHITE);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.RED);
		
		while ((i < this.getWidth()) && (i < datavals.size())) {
			double y = datavals.get(i)/ext*pixelExt;
			System.out.println("y: " + y);
			g.drawLine(i, (int)y, i, (int)(y+1));
			i ++;
		}
	}
}
