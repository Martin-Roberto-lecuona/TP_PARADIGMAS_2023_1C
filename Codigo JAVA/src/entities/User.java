package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

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

	public ArrayList<Promotion> createSuggestion(LinkedList<Promotion> promotionArrayList) {
		ArrayList<Promotion> res = new ArrayList<Promotion>();

		Collections.sort(promotionArrayList);
		for (Promotion promo : promotionArrayList) {
			if (canGo(promo)) {
				res.add(promo);
			}
		}
		return res;
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

	public boolean canGo(Atraction atrac) {
		if (atrac.getSlots() <= 0 
				&& Double.compare(this.budget, atrac.getCost()) < 0
				&& Double.compare(this.freeTime, atrac.getEstimatedTime()) < 0) {
			return false;
		}

		return true;
	}

	public boolean canGo(Promotion promo) {
		boolean flagCanGoAtraction = true;
		for (Iterator<Atraction> iterator = promo.getAtractionList().iterator(); iterator.hasNext()
				&& flagCanGoAtraction;) {
			Atraction atrac = (Atraction) iterator.next();
			flagCanGoAtraction = canGo(atrac);
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

}
