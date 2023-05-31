package entities;

import java.util.ArrayList;
import java.util.Objects;

import enums.AtractionType;

public abstract class Offer{
	
	protected double totalCost;
	protected double totalTime;
	protected AtractionType type;
	
	
	
	
	public double getTotalCost() {
		return totalCost;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public AtractionType getType() {
		return type;
	}
	public void setType(AtractionType type) {
		this.type = type;
	}
	
	public abstract void decreaseSlots();
	
	public abstract String presentation();

	@Override
	public int hashCode() {
		return Objects.hash(totalCost, totalTime, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		return Double.doubleToLongBits(totalCost) == Double.doubleToLongBits(other.totalCost)
				&& Double.doubleToLongBits(totalTime) == Double.doubleToLongBits(other.totalTime) && type == other.type;
	}

	public abstract ArrayList<Atraction> getAtractions();
	
}
