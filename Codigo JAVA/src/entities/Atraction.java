package entities;

import java.util.ArrayList;
import java.util.Objects;

import enums.AtractionType;

public class Atraction extends Offer implements Comparable<Atraction>  {

	private int slots;
	private String name;

	public Atraction(String name, double cost, double estimatedTime, int maxSlots, AtractionType atractionType) {
		this.name = name;
		this.totalCost = cost;
		this.totalTime = estimatedTime;
		this.slots = maxSlots;
		this.type = atractionType;
	}

	public Atraction(Atraction atr) {
		this.name = atr.getName();
		this.totalCost = atr.getTotalCost();
		this.totalTime = atr.getTotalTime();
		this.slots = atr.getSlots();
		this.type = atr.getType();
	}

	public String getName() {
		return name;
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
		if (Double.compare(a.getTotalCost(), this.totalCost) == 0) {
			return Double.compare(a.getTotalTime(), this.totalTime);
		}
		return Double.compare(a.getTotalCost(), this.totalCost);

	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, slots);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraction other = (Atraction) obj;
		return Objects.equals(name, other.name) && slots == other.slots;
	}

	@Override
	public String toString() {
		return "\nNombre:	" + name + "	-Precio:$" + totalCost + "	-Duración:" + totalTime + "hrs"
				+ "	-Disponibles: " + slots;
	}
	
	
	public static Offer createNewSuggestion(ArrayList<Atraction> offerArray, int[] cont,
			boolean basedOnPreferred, ArrayList<Atraction> alreadyTaken, User user) {
		int i = cont[0];
		boolean ifCanGo, preferred;
		if (!user.canGoAlreadyTaken(offerArray, alreadyTaken)) {
			cont[0]++;
			return null;
		}
		while (i < offerArray.size()) {
			ifCanGo = user.canGo((Atraction) offerArray.get(i));
			if (basedOnPreferred) {
				preferred = offerArray.get(i).getType() == user.getPreferredAtraccion();
			} else
				preferred = offerArray.get(i).getType() != user.getPreferredAtraccion();

			if (ifCanGo && preferred) {
				cont[0] = i;
				return offerArray.get(i);
			}
			i++;
		}
		return null;
	}
	@Override
	public String presentation() {
		return "La Atraccion que le presentamos es: " + this + "\nAcepta la Atraccion 'S' para si 'N' para no ";
	}

	@Override
	public ArrayList<Atraction> getAtractions() {
		// TODO Auto-generated method stub
		ArrayList<Atraction> res = new ArrayList<Atraction>();
		res.add(this);
		return res;
	}
}
