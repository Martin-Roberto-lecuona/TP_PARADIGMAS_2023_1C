package entities;

import java.util.ArrayList;

public class Porcentual extends Promotion {

	private double percent = 0;

	public Porcentual(ArrayList<Atraction> atractions, double percent) {

		super(atractions);
		this.percent = percent;
		calculateTotalWithDiscount();
	}

	@Override
	public void calculateTotalWithDiscount() {
		this.discountedTotalCost = this.totalCost - (this.totalCost * this.percent);
	}

}
