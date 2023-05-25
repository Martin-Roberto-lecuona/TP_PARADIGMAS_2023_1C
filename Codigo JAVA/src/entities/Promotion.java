package entities;

import java.util.ArrayList;

import enums.AtractionType;

public abstract class Promotion implements Comparable<Promotion> {

	protected double totalCost = 0;
	protected double totalTime = 0;
	protected double discountedTotalCost;
	protected AtractionType promotionType;

	protected String[] atractionNames;
	protected ArrayList<Atraction> atractionList = new ArrayList<Atraction>();

	public Promotion(ArrayList<Atraction> a) {
		this.atractionList.addAll(a);
		this.setPromotionType(a.get(0).getAtractionType());
		for (Atraction atr : a) {
			this.totalCost += atr.getCost();
			this.totalTime += atr.getEstimatedTime();
		}
	}

	public ArrayList<Atraction> getAtractionList() {
		return this.atractionList;
	}

	public void decreaseSlots() {
		for (Atraction atraction : this.atractionList) {
			atraction.decreaseSlots();
		}
	}

	public void decreaseSlots(ArrayList<Atraction> atractionArray) {
		for (Atraction atraction : this.atractionList) {
			atraction.decreaseSlots();
			if (atraction.getSlots() <= 0) {
				atractionArray.remove(atractionArray.indexOf(atraction));
			}
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
		;
		if (Double.compare(p.discountedTotalCost, this.discountedTotalCost) == 0) {
			return Double.compare(p.totalTime, this.totalTime);
		} else {
			return Double.compare(p.discountedTotalCost, this.discountedTotalCost);
		}
	}

	@Override
	public String toString() {
		String regex = "[\\]\\[]";
		return promotionType + "\n-Precio: $" + totalCost + ", -Duración: " + totalTime + ", -Precio con descuento: $"
				+ discountedTotalCost + "\nIncluye:" + (this.atractionList.toString()).replaceAll(regex, "") + "\n";
	}

	public abstract void calculateTotalWithDiscount();

	public AtractionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(AtractionType promotionType) {
		this.promotionType = promotionType;
	}

}