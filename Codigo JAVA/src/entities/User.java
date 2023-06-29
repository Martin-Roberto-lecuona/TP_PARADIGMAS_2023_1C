package entities;

import java.util.ArrayList;

import enums.AtractionType;

public class User {

	private String name;
	private double budget;
	private double freeTime;
	private AtractionType preferredAtraction;

	public User(String name, double budget, double freeTime2, AtractionType preferredAtraction) {
		this.name = name;
		this.budget = budget;
		this.freeTime = freeTime2;
		this.preferredAtraction = preferredAtraction;
	}

	public boolean alreadyTaken(Atraction atr, ArrayList<Atraction> alreadyTaken) {

		return alreadyTaken.contains(atr);

	}

	public boolean alreadyTakenAtrList(ArrayList<Atraction> atractionList, ArrayList<Atraction> alreadyTaken) {
		for (Atraction atr : atractionList) {
			if (alreadyTaken.contains(atr)) {
				return false;
			}
		}
		return true;
	}

	public boolean canGo(Atraction atr) {
		if (atr.getSlots() <= 0 || Double.compare(this.budget, atr.getTotalCost()) < 0
				|| Double.compare(this.freeTime, atr.getTotalTime()) < 0) {
			return false;
		}

		return true;
	}

	public boolean canGo(Promotion promo) {

		if (Double.compare(this.budget, promo.getTotalCost()) < 0
				|| Double.compare(this.freeTime, promo.getTotalTime()) < 0) {
			return false;
		}

		boolean flagCanGoAtraction = true;

		for (int i = 0; i < promo.getAtractions().size() && flagCanGoAtraction; i++) {
			flagCanGoAtraction = promo.getAtractions().get(i).getSlots() > 0;
		}

		return flagCanGoAtraction;
	}

	public void appoint(Offer atr) {
		this.budget = (this.budget - atr.getTotalCost());
		this.freeTime = (this.freeTime - atr.getTotalTime());
	}

	public void appoint(Promotion promo) {
		for (Atraction atrac : promo.getAtractions()) {
			appoint(atrac);
		}
	}

	@Override
	public String toString() {
		return "Nombre=" + name + ", Presupuesto=" + budget + ", Tiempo Libre=" + freeTime + ", Atraccion preferida="
				+ preferredAtraction;
	}

	public String getName() {
		return this.name;
	}

	public AtractionType getPreferredAtraccion() {
		return preferredAtraction;
	}

	public void setPreferredAtraccion(AtractionType preferredAtraction) {
		this.preferredAtraction = preferredAtraction;
	}

}
