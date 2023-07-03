package entities;

import java.util.ArrayList;
import enums.AtractionType;

public abstract class Offer {

	protected double totalCost = 0;
	protected double totalTime = 0;
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

	public abstract String selfPresentationToString();

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
