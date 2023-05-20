package entities;

import java.util.ArrayList;

public class Absoluta extends Promotion {
	private double absolute = 0;

	public Absoluta(double totalCost, double totalTime, ArrayList<String> atractionNames, double percent) {

		super(totalCost, totalTime, atractionNames);
		this.absolute = absolute;
		calculateTotalWithDiscount();
	}

	@Override
	public void calculateTotalWithDiscount() {
		this.discountedTotalCost = this.totalCost * this.absolute;
	}
}
