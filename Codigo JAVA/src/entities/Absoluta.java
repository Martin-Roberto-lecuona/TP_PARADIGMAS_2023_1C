package entities;

import java.util.ArrayList;

public class Absoluta extends Promotion {
	
	private double absolute = 0;

	public Absoluta(ArrayList<Atraction> atractions, double absolute) {

		super(atractions);
		this.absolute = absolute;
		calculateTotalWithDiscount();
	}

	@Override
	public void calculateTotalWithDiscount() {
		this.totalCost = this.totalCostWithNoDiscount - this.absolute;
	}
}
