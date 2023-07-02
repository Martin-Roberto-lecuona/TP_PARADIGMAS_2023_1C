package entities;

import java.util.ArrayList;

public class AxB extends Promotion {

	private ArrayList<Atraction> free = new ArrayList<Atraction>();

	public AxB(ArrayList<Atraction> atractions, ArrayList<Atraction> free) {
		super(atractions);
		this.free = free;
		calculateTotalWithDiscount();
		calculateTotalCost();
		calculateTotalTime();
	}

	@Override
	public String toString() {
		return super.toString() + "Y ademas totalmente GRATIS: " + getFreeString() + "\n";
	}

	@Override
	public void calculateTotalWithDiscount() {
		this.totalCost = this.totalCostWithNoDiscount;
	}

	public void calculateTotalTime() {
		for (Atraction f : free) {
			this.totalTime += f.getTotalTime();
		}
	}

	public void calculateTotalCost() {
		for (Atraction f : free) {
			this.totalCostWithNoDiscount += f.getTotalCost();
		}
	}

	public String getFreeString() {
		String res = "";
		for (Atraction f : free) {
			res = res + "" + f;
		}
		return res;
	}

	@Override
	public void decreaseSlots() {
		super.decreaseSlots();
		if (!free.isEmpty()) {
			for (Atraction atraction : this.free) {
				atraction.decreaseSlots();
			}
		}
	}

}
