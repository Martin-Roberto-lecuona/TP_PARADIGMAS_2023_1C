package entities;

import java.util.Objects;

import enums.AtractionType;

public class Atraction implements Comparable<Atraction> {

	private double cost;
	private double estimatedTime;
	private int slots;
	private AtractionType atractionType;
	private String name;

	public Atraction(String name, double cost, double estimatedTime, int maxSlots, AtractionType atractionType) {
		this.name = name;
		this.cost = cost;
		this.estimatedTime = estimatedTime;
		this.slots = maxSlots;
		this.atractionType = atractionType;
	}

	public Atraction(Atraction atr) {
		this.name = atr.getName();
		this.cost = atr.getCost();
		this.estimatedTime = atr.getEstimatedTime();
		this.slots = atr.getSlots();
		this.atractionType = atr.getAtractionType();
	}

	public double getCost() {
		return cost;
	}

	public AtractionType getAtractionType() {
		return atractionType;
	}

	public void setAtractionType(AtractionType atractionType) {
		this.atractionType = atractionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public int getSlots() {
		return this.slots;
	}

	public void decreaseSlots() {
		if (this.slots != 0) {
			this.slots--;
		}
	}

	@Override
	public int compareTo(Atraction a) {
		if (Double.compare(this.cost, a.getCost()) == 0) {
			return Double.compare(this.estimatedTime, a.getEstimatedTime());
		}
		return Double.compare(this.cost, a.getCost());

	}

	@Override
	public int hashCode() {
		return Objects.hash(atractionType, cost, estimatedTime, slots, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraction other = (Atraction) obj;
		return atractionType == other.atractionType
				&& Double.doubleToLongBits(cost) == Double.doubleToLongBits(other.cost)
				&& Double.doubleToLongBits(estimatedTime) == Double.doubleToLongBits(other.estimatedTime)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "\nNombre:" + name + " -Precio: $" + cost + "-Duración: " + estimatedTime + " hrs";
	}

}
