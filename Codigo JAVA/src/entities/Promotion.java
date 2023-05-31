package entities;

import java.util.ArrayList;

public abstract class Promotion extends Offer implements Comparable<Promotion> {

	protected double totalCostWithNoDiscount;

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

	public ArrayList<Atraction> getAtractionList() {
		return this.atractionList;
	}

	public void decreaseSlots() {
		for (Offer atraction : this.atractionList) {
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

	public double getTotalCostWithNoDiscount() {
		return totalCostWithNoDiscount;
	}

	@Override
	public int compareTo(Promotion p) {
		;
		if (Double.compare(p.totalCost, this.totalCost) == 0) {
			return Double.compare(p.totalTime, this.totalTime);
		} else {
			return Double.compare(p.totalCost, this.totalCost);
		}
	}

	@Override
	public String toString() {
		String regex = "[\\]\\[]";
		return this.type + "\n-Precio: $" + totalCostWithNoDiscount + ", -Duración: " + totalTime
				+ ", -Precio con descuento: $" + totalCost + "\nIncluye:"
				+ (this.atractionList.toString()).replaceAll(regex, "") + "\n";
	}

	public abstract void calculateTotalWithDiscount();

	public static Offer createNewSuggestion(ArrayList<Promotion> offerArrayList, int[] cont, boolean basedOnPreferred,
			ArrayList<Atraction> alreadyTaken, User user) {
		int i = cont[0];
		boolean ifCanGo, preferred;
		while (i < offerArrayList.size()) {

			ifCanGo = user.canGo(offerArrayList.get(i))
					&& user.canGoAlreadyTakenAtr(offerArrayList.get(i).getAtractionList(), alreadyTaken);

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

	public String presentation() {
		return "La Promocion que le presentamos es: " + this + "\nAcepta la Promo 'S' para si 'N' para no ";
	}

	@Override
	public ArrayList<Atraction> getAtractions() {
		return this.atractionList;
	}

}