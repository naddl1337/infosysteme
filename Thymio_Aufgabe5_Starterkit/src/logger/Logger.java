package logger;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Logger extends JFrame {
	private static final long serialVersionUID = 1L;
	private DataPanel xValPanel;
	private DataPanel yValPanel;
	
	public Logger() {
		super();
		
		xValPanel = new DataPanel();
		yValPanel = new DataPanel();
		
		this.add(xValPanel);
		this.add(yValPanel);
		
		this.setMaximumSize(new Dimension(600, 200));
		this.pack();
		this.setVisible(true);
	}
	
	public void addData(double xval, double yval) {
		xValPanel.add(xval);
		yValPanel.add(yval);
	}
}
