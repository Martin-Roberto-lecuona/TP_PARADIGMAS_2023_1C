package entities;

import java.util.ArrayList;

public abstract class Promotion implements Comparable<Promotion> {

	protected double totalCost = 0;
	protected double totalTime = 0;
	protected double discountedTotalCost;

	protected String[] atractionNames;
	protected ArrayList<Atraction> atractionList = new ArrayList<Atraction>();

	public Promotion(double totalCost, double totalTime, String[] atractionNames) {
		this.totalCost = totalCost;
		this.totalTime = totalTime;
		this.atractionNames = atractionNames;
	}
	
	public Promotion(ArrayList<Atraction> a ) {
		this.atractionList.addAll(a);
		for (Atraction atr : a) {
			this.totalCost+=atr.getCost();
			this.totalTime+=atr.getEstimatedTime();
		}
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

	public String[] getAtractionNames() {
		return atractionNames;
	}

	public void setAtractionNames(String[] atractionNames) {
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