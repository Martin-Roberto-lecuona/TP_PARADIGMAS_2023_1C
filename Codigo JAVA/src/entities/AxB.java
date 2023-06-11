package entities;

import java.util.ArrayList;

public class AxB extends Promotion{

	private ArrayList<Atraction> free = new ArrayList<Atraction>();


	public AxB(ArrayList<Atraction> atractions, ArrayList<Atraction> free) {
		super(atractions);
		this.free = free;
		calculateTotalCost();
		calculateTotalTime();
		calculateTotalWithDiscount();
	}
	
	@Override
	public String toString() {
		return super.toString() + "Y ademas totalmente GRATIS: " + getFreeString() + "\n" ;
	}

	@Override
	public void calculateTotalWithDiscount() {	
		double val=0;
		for (Atraction f : free) {
			val += f.getTotalCost();
		}
		this.totalCost = this.totalCostWithNoDiscount - val ;
	}
	
	public void calculateTotalTime() {	
		for (Atraction f : free) {
			this.totalTime += f.getTotalTime();
		}
	}
	public void calculateTotalCost() {			
		for (Atraction atr : atractionList) {
			this.totalCostWithNoDiscount += atr.getTotalCost();
		}
		for (Atraction f : free) {
			this.totalCost += f.getTotalCost();
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
		/*if (this.slots != 0) {
			this.slots--;
		}*/
		if(!free.isEmpty()) {
			for (Atraction atraction : this.free) {
				atraction.decreaseSlots();
			}
		}
	}


}
