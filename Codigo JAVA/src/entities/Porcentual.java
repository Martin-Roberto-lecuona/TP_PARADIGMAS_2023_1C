package entities;

import java.util.ArrayList;

public class Porcentual extends Promotion {

	private double percent = 0;

	public Porcentual(double totalCost, double totalTime, ArrayList<String> atractionNames, double percent) {

		super(totalCost, totalTime, atractionNames);
		this.percent = percent;
		calculateTotalWithDiscount();
	}

	@Override
	public void calculateTotalWithDiscount() {
		this.discountedTotalCost = this.totalCost * this.percent;
	}

}
