package observer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import logger.Logger;
import main.MainController;

public class ObserverThread extends Thread implements KeyListener {
	public static final double MIN_ACC_X = 1.0;
	public static final double MIN_ACC_Y = 1.0;
	private boolean breaked;
	private double ax;
	private double ay;
	private double vx;
	private double vy;
	private double sx;
	private double sy;
	private MainController controller;
	private Logger myLogger;
	
	public double getSX() {
		return sx;
	}
	
	public double getSY() {
		return sy;
	}
	
	public ObserverThread(MainController c) {	
		breaked = false;
		c.addKeyListener(this);
		ax = 0.0;
		ay = 0.0;
		
		vx = 0.0;
		vy = 0.0;
		
		sx = 0.0;//c.getWidth()/2;
		sy = 0.0;//c.getHeight()/2;
		
		controller = c;
		myLogger = new Logger();
	}
	
	public void shutdown() {
		myLogger.setVisible(false);
		myLogger.dispose();
	}
	
	public void run() {
		try {
			while (!breaked) {
				Thread.sleep(1000);
				System.out.println("(sx: " + sx + "," + "sy: " + sy +") (vx: " + vx + "," + "vy: " + vy + ")");

				sx += vx;
				sy += vy;

				controller.repaint();
				myLogger.addData(sx, sy);
				myLogger.repaint();
			}
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			System.out.println("left");
			ax -= ObserverThread.MIN_ACC_X;
			break;

		case KeyEvent.VK_RIGHT:
			System.out.println("right");
			ax += ObserverThread.MIN_ACC_X;
			break;

		case KeyEvent.VK_UP:
			System.out.println("up");
			ay += ObserverThread.MIN_ACC_Y;
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("down");
			ay -= ObserverThread.MIN_ACC_Y;
			break;

		default:
			break;
		}

		System.out.println("ax: " + ax + "/" + "ay: " + ay);

		vx += ax;
		vy += ay;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			System.out.println("left");
			ax = 0;
			break;

		case KeyEvent.VK_RIGHT:
			System.out.println("right");
			ax = 0;
			break;

		case KeyEvent.VK_UP:
			System.out.println("up");
			ay = 0;
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("down");
			ay = 0;
			break;

		case KeyEvent.VK_X:
			System.out.println("exit");
			breaked = true;
			break;
			
		default:
			break;
		}

		System.out.println("ax: " + ax + "/" + "ay: " + ay);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
