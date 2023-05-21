package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Porcentual extends Promotion {

	private double percent = 0;

	public Porcentual(double totalCost, double totalTime, String[] names, double percent) {

		super(totalCost, totalTime, names);
		this.percent = percent;
		calculateTotalWithDiscount();
	}
	public Porcentual(ArrayList<Atraction> atractions, double percent) {

		super(atractions);
		this.percent = percent;
		calculateTotalWithDiscount();
	}

	@Override
	public String toString() {
		return "Porcentual [percent=" + percent + ", totalCost=" + totalCost + ", totalTime=" + totalTime
				+ ", discountedTotalCost=" + discountedTotalCost + ", atractionNames=" + Arrays.toString(atractionNames)
				+ "]";
	}

	@Override
	public void calculateTotalWithDiscount() {
		this.discountedTotalCost = this.totalCost * this.percent;
	}

}
