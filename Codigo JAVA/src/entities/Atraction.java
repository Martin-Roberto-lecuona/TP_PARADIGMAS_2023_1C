package entities;

import java.util.Objects;

import enums.AtractionType;
import interfaces.Appointable;

public class Atraction implements Appointable<User>, Comparable<Atraction> {

	private double cost = 0;
	private double estimatedTime = 0;
	private int maxSlots = 0;
	private int takenSlots = 0;
	private AtractionType atractionType = null;
	private String name = null;

	public Atraction(String name, double cost, double estimatedTime, int maxSlots, AtractionType atractionType) {
		this.name = name;
		this.cost = cost;
		this.estimatedTime = estimatedTime;
		this.maxSlots = maxSlots;
		this.atractionType = atractionType;
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

	public int getMaxSlots() {
		return maxSlots;
	}

	public void setMaxSlots(int maxSlots) {
		this.maxSlots = maxSlots;
	}

	public int getTakenSlots() {
		return takenSlots;
	}

	public void setTakenSlots(int takenSlots) {
		this.takenSlots = takenSlots;
	}

	@Override
	public boolean canGo(User user) {
		if (this.takenSlots >= this.maxSlots) {
			return false;
		}

		if (user.getBudget() - this.cost < 0) {
			return false;
		}
		if (user.getFreeTime() - this.estimatedTime < 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean appoint(User user) {
		if (this.canGo(user)) {
			user.setBudget(user.getBudget() - this.cost);
			user.setFreeTime(user.getFreeTime() - this.estimatedTime);
			user.addTakenAtraction(this);
			return true;
		}
		return true;
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
		return Objects.hash(atractionType, cost, estimatedTime, maxSlots, name, takenSlots);
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
				&& maxSlots == other.maxSlots && Objects.equals(name, other.name) && takenSlots == other.takenSlots;
	}
	
	
	public boolean equalsName(String name)
	{
		return this.name == name;
	}

	@Override
	public String toString() {
		return "Atracción\nNombre: [" + name + "]\n-Precio: $" + cost + "\n-Duración: " + estimatedTime + "horas";
	}
	
	
	

	
	

}
