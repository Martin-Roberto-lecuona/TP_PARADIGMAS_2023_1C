package entities;
import java.util.LinkedList;

import enums.AtractionType;
import interfaces.Appointable;

public class User implements Appointable<Atraction> {
	
	private double budget = 0;
	private double freeTime = 0;
	private AtractionType preferredAtraccion = null;
	private LinkedList<Atraction> alreadyTakenAtractions = new LinkedList<Atraction>();

	public User(double budget, int freeTime, AtractionType preferredAtraccion) {
		this.budget = budget;
		this.freeTime = freeTime;
		this.preferredAtraccion = preferredAtraccion;
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
		return preferredAtraccion;
	}

	public void setPreferredAtraccion(AtractionType preferredAtraccion) {
		this.preferredAtraccion = preferredAtraccion;
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

}
