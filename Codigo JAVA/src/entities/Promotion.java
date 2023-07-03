package entities;

import java.util.ArrayList;

public abstract class Promotion extends Offer implements Comparable<Promotion> {

	protected double totalCostWithNoDiscount=0;

	protected String[] atractionNames;
	protected ArrayList<Atraction> atractionList = new ArrayList<Atraction>();

	public Promotion(ArrayList<Atraction> a) {
		this.atractionList.addAll(a);
		this.setType(a.get(0).getType());
		for (Atraction atr : a) {
			this.totalCostWithNoDiscount += atr.getTotalCost();
			this.totalTime += atr.getTotalTime();
		}
	}

	public void decreaseSlots() {
		for (Offer atraction : this.atractionList) {
			atraction.decreaseSlots();
		}
	}

	public double getTotalCost() {
		return totalCost;
	}

	public double getTotalTime() {
		return totalTime;
	}

	@Override
	public int compareTo(Promotion p) {
		if (Double.compare(p.totalCost, this.totalCost) == 0) {
			return Double.compare(p.totalTime, this.totalTime);
		} else {
			return Double.compare(p.totalCost, this.totalCost);
		}
	}

	@Override
	public String toString() {
		String regex = "[\\]\\[]";
		return "[" + this.type + "]\nAtracciones incluidas:" + (this.atractionList.toString()).replaceAll(regex, "")
				+ "\n\n-Duración total: " + totalTime + "hs" + "\n-Precio original: $" + totalCostWithNoDiscount
				+ "\n-Precio con descuento: $" + totalCost + "\n";
	}

	public abstract void calculateTotalWithDiscount();

	public static Offer createNewSuggestion(ArrayList<Promotion> offerArrayList, int[] cont, boolean basedOnPreferred,
			ArrayList<Atraction> alreadyTaken, User user) {
		int i = cont[0];
		boolean ifCanGo, preferred;
		while (i < offerArrayList.size()) {

			ifCanGo = user.canGoTo(offerArrayList.get(i))
					&& user.isThisAtractionListalreadyTaken(offerArrayList.get(i).getAtractions(), alreadyTaken);

			if (basedOnPreferred) {
				preferred = offerArrayList.get(i).getType() == user.getPreferredAtraccion();
			} else {
				preferred = offerArrayList.get(i).getType() != user.getPreferredAtraccion();
			}

			if (ifCanGo && preferred) {
				cont[0] = i;
				return offerArrayList.get(i);
			}
			i++;

		}
		return null;
	}

	public String selfPresentationToString() {
		return "La PROMOCIÓN que le presentamos es: " + this
				+ "\n¿Acepta la Promoción? Ingrese 'S' para aceptar, 'N' para rechazar:";
	}

	@Override
	public ArrayList<Atraction> getAtractions() {
		return this.atractionList;
	}

}