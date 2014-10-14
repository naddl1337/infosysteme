package math;

import java.util.Random;

public class NormalDistribution {
	private double mean;
	private double standardDev;
	private Random randGen;
	
	public NormalDistribution(double m, double sd) {
		mean = m;
		standardDev = sd;
		randGen = new Random();
	}
	
	public double drawFromDistribution() {
		double val = 0;
		
		for (int i = 0; i < 12; i++) {
			val += randGen.nextGaussian()*standardDev;
		}
		
		return 0.5*val + mean;
	}
}