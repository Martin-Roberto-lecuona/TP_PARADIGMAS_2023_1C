package entities;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import enums.AtractionType;

public class Atraction extends Offer implements Comparable<Atraction> {

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
		if (Double.compare(this.totalCost, a.getTotalCost()) == 0) {
			return Double.compare(this.totalTime, a.getTotalTime());
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
		return String.format(Locale.ENGLISH,
				"\n-Nombre: %-20s \t-Precio:$%-7.2f \t-Duración:%4.2fhrs \t-Disponibles: %-3d", name, totalCost,
				totalTime, slots);
		// return "\n-Nombre: " + name + "\t-Precio:$" + totalCost + "\t-Duración:" +
		// totalTime + "hrs" + "\t-Disponibles: " + slots;
	}

	public static Offer createNewSuggestion(ArrayList<Atraction> offerArray, int[] cont, boolean basedOnPreferred,
			ArrayList<Atraction> alreadyTakenArray, User user) {
		boolean show;

		while (cont[0] < offerArray.size()) {

			if (basedOnPreferred) {
				show = offerArray.get(cont[0]).getType() == user.getPreferredAtraccion();
			} else {

				show = offerArray.get(cont[0]).getType() != user.getPreferredAtraccion();
			}

			boolean alreadyTakenBoolean = !user.alreadyTaken(offerArray.get(cont[0]), alreadyTakenArray);

			if (user.canGo((Atraction) offerArray.get(cont[0])) && alreadyTakenBoolean && show) {
				cont[0]++;
				return offerArray.get(cont[0] - 1);
			}

			cont[0]++;

		}
		return null;
	}

	@Override
	public String presentation() {
		return "La ATRACCIÓN que le presentamos es: " + this
				+ "\n\n¿Acepta la Atracción? Ingrese 'S' para aceptar, 'N' para rechazar:";
	}

	@Override
	public ArrayList<Atraction> getAtractions() {
		// TODO Auto-generated method stub
		ArrayList<Atraction> res = new ArrayList<Atraction>();
		res.add(this);
		return res;
	}
}
