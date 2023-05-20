package entities;

import java.util.LinkedList;

import enums.AtractionType;
import interfaces.Appointable;

public class User implements Appointable<Atraction> {
	
	private String name = "";
	private double budget = 0;
	private double freeTime = 0;
	private AtractionType preferredAtraction = null;
	private LinkedList<Atraction> alreadyTakenAtractions = new LinkedList<Atraction>();

	public User(String name, double budget, double freeTime2, AtractionType preferredAtraction) {
		this.name = name;
		this.budget = budget;
		this.freeTime = freeTime2;
		this.preferredAtraction = preferredAtraction;
	}

	public double getBudget() {
		return budget;
	}

	public void getAlreadyTakenAtractions() {
		this.alreadyTakenAtractions.forEach(i -> {
			System.out.println(i.getName());
		});
	}

	public void addTakenAtraction(Atraction atraction) {
		this.alreadyTakenAtractions.add(atraction);
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

	@Override
	public boolean appoint(Atraction a) {
		if (this.canGo(a)) {

			this.budget = (this.budget - a.getCost());
			this.freeTime = (this.freeTime - a.getEstimatedTime());
			this.addTakenAtraction(a);

			return true;
		}
		return true;
	}

	@Override
	public boolean canGo(Atraction a) {
		if (a.getMaxSlots() >= a.getTakenSlots()) {
			return false;
		}

		if (this.budget - a.getCost() < 0) {
			return false;
		}

		if (this.freeTime - a.getEstimatedTime() < 0) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + name + ", Presupuesto=" + budget + ", Tiempo Libre=" + freeTime + ", Atraccion preferida="
				+ preferredAtraction + ", alreadyTakenAtractions=" + alreadyTakenAtractions + "]";
	}

}
