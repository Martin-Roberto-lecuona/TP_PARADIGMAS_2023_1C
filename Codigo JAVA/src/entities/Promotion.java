package entities;

import java.util.ArrayList;

public abstract class Promotion implements Comparable<Promotion> {

	protected double totalCost = 0;
	protected double totalTime = 0;
	protected double discountedTotalCost = 0;
	protected ArrayList<String> atractionNames = new ArrayList<String>();

	public Promotion(double totalCost, double totalTime, ArrayList<String> atractionNames) {
		this.totalCost = totalCost;
		this.totalTime = totalTime;
		this.atractionNames = atractionNames;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public double getDiscountedTotalCost() {
		return discountedTotalCost;
	}

	public void setDiscountedTotalCost(double discountedTotalCost) {
		this.discountedTotalCost = discountedTotalCost;
	}

	public ArrayList<String> getAtractionNames() {
		return atractionNames;
	}

	public void setAtractionNames(ArrayList<String> atractionNames) {
		this.atractionNames = atractionNames;
	}

	@Override
	public int compareTo(Promotion p) {
		if (Double.compare(this.discountedTotalCost, p.discountedTotalCost) == 0) {
			return Double.compare(this.totalTime, p.totalTime);
		} else {
			return Double.compare(this.discountedTotalCost, p.discountedTotalCost);
		}

	}

	public abstract void calculateTotalWithDiscount();

}