package entities;

import java.util.ArrayList;
import java.util.List;

public class Porcentual extends Promotion {

	private double percent = 0;

	public Porcentual(double totalCost, double totalTime, List<String> names, double percent) {

		super(totalCost, totalTime, names);
		this.percent = parsedValues;
		calculateTotalWithDiscount();
	}

	@Override
	public void calculateTotalWithDiscount() {
		this.discountedTotalCost = this.totalCost * this.percent;
	}

}
