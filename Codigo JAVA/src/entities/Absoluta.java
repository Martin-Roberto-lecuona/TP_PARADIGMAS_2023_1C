package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Absoluta extends Promotion {
	private double absolute = 0;

	public Absoluta(ArrayList<Atraction> atractions , double absolute) {

		super(atractions);
		this.absolute = absolute;
		calculateTotalWithDiscount();
	}

	
	@Override
	public void calculateTotalWithDiscount() {
		this.discountedTotalCost = this.totalCost * this.absolute;
	}
}
