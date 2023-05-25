package entities;

import java.util.ArrayList;

import enums.AtractionType;

public class User {

	private String name;
	private double budget;
	private double freeTime;
	private AtractionType preferredAtraction;
	// private LinkedList<Atraction> alreadyTakenAtractions = new
	// LinkedList<Atraction>();

	public User(String name, double budget, double freeTime2, AtractionType preferredAtraction) {
		this.name = name;
		this.budget = budget;
		this.freeTime = freeTime2;
		this.preferredAtraction = preferredAtraction;
	}

	public Promotion createNewPromotionSuggestion(ArrayList<Promotion> promotionArrayList, int[] cont,
			boolean basedOnPreferred, ArrayList<Atraction> alreadyTaken) {
		int i = cont[0];
		boolean ifCanGo, preferred;
		while (i < promotionArrayList.size()) {

			ifCanGo = this.canGo(promotionArrayList.get(i))
					&& canGoAlreadyTaken(promotionArrayList.get(i).getAtractionList(), alreadyTaken);

			if (basedOnPreferred) {
				preferred = promotionArrayList.get(i).getPromotionType() == this.preferredAtraction;
			} else {
				preferred = promotionArrayList.get(i).getPromotionType() != this.preferredAtraction;
			}

			if (ifCanGo && preferred) {
				cont[0] = i;
				return promotionArrayList.get(i);
			}
			i++;

		}
		return null;
	}

	private boolean canGoAlreadyTaken(ArrayList<Atraction> atractionList, ArrayList<Atraction> alreadyTaken) {
		for (Atraction atr : atractionList) {
			if (alreadyTaken.contains(atr)) {
				return false;
			}
		}
		return true;
	}

	public Atraction createNewAtractionSuggestion(ArrayList<Atraction> atractionArray, int[] cont,
			boolean basedOnPreferred, ArrayList<Atraction> alreadyTaken) {
		int i = cont[0];
		boolean ifCanGo, preferred;
		if (!canGoAlreadyTaken(atractionArray, alreadyTaken)) {
			cont[0] ++;
			return null;
		}
		while (i < atractionArray.size()) {
			ifCanGo = this.canGo(atractionArray.get(i));
			if (basedOnPreferred) {
				preferred = atractionArray.get(i).getAtractionType() == this.preferredAtraction;
			} else
				preferred = atractionArray.get(i).getAtractionType() != this.preferredAtraction;

			if (ifCanGo && preferred) {
				cont[0] = i;
				return atractionArray.get(i);
			}
			i++;
		}
		return null;
	}

	public boolean canGo(Atraction atrac) {
		if (atrac.getSlots() <= 0 || Double.compare(this.budget, atrac.getCost()) < 0
				|| Double.compare(this.freeTime, atrac.getEstimatedTime()) < 0) {
			return false;
		}

		return true;
	}

	public boolean canGo(Promotion promo) {

		if (Double.compare(this.budget, promo.getDiscountedTotalCost()) < 0
				|| Double.compare(this.freeTime, promo.getTotalTime()) < 0) {
			return false;
		}

		boolean flagCanGoAtraction = true;

		for (int i = 0; i < promo.getAtractionList().size() && flagCanGoAtraction; i++) {
			flagCanGoAtraction = promo.getAtractionList().get(i).getSlots() > 0;
		}

		return flagCanGoAtraction;
	}

	public void appoint(Atraction atrac) {
		this.budget = (this.budget - atrac.getCost());
		this.freeTime = (this.freeTime - atrac.getEstimatedTime());
	}

	public void appoint(Promotion promo) {
		for (Atraction atrac : promo.getAtractionList()) {
			appoint(atrac);
		}
	}

	@Override
	public String toString() {
		return "nombre=" + name + ", Presupuesto=" + budget + ", Tiempo Libre=" + freeTime + ", Atraccion preferida="
				+ preferredAtraction;
	}

	public double getBudget() {
		return this.budget;
	}

	public String getName() {
		return this.name;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(double time) {
		this.freeTime = time;
	}

	public AtractionType getPreferredAtraccion() {
		return preferredAtraction;
	}

	public void setPreferredAtraccion(AtractionType preferredAtraction) {
		this.preferredAtraction = preferredAtraction;
	}

}
