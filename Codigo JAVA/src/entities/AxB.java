package entities;

import java.util.ArrayList;

public class AxB extends Promotion{

	private ArrayList<Atraction> free = new ArrayList<Atraction>();

	

	public AxB(ArrayList<Atraction> atractions, ArrayList<Atraction> free) {
		super(atractions);
		this.free = free;
		calculateTotalCost();
		calculateTotalTime();
	}
	
	@Override
	public String toString() {
		return super.toString() + " free: " + getFreeString();
	}

	@Override
	public void calculateTotalWithDiscount() {	
		this.discountedTotalCost = this.totalCost;
	}
	
	public void calculateTotalTime() {	
		for (Atraction f : free) {
			this.totalTime += f.getEstimatedTime();
		}
	}
	public void calculateTotalCost() {			
		for (Atraction f : free) {
			this.totalCost += f.getCost();
		}
	}
	
	public ArrayList<Atraction> getFree() {
		return free;
	}
	public String getFreeString() {
		String res = "";
		for (Atraction f : free) {
			res = res + f.getName();
		}
		return res;
	}
}
