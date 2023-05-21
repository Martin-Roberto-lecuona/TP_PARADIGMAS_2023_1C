package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Absoluta extends Promotion {
	private double absolute = 0;

	public Absoluta(double totalCost, double totalTime, String[] atractionNames, double absolute) {

		super(totalCost, totalTime, atractionNames);
		this.absolute = absolute;
		calculateTotalWithDiscount();
	}
	public Absoluta(ArrayList<Atraction> atractions , double absolute) {

		super(atractions);
		this.absolute = absolute;
		calculateTotalWithDiscount();
	}

	@Override
	public String toString() {
		return "Absoluta [absolute=" + absolute + ", totalCost=" + totalCost + ", totalTime=" + totalTime
				+ ", discountedTotalCost=" + discountedTotalCost + ", atractionNames=" + Arrays.toString(atractionNames)
				+ "]";
	}

	@Override
	public void calculateTotalWithDiscount() {
		this.discountedTotalCost = this.totalCost * this.absolute;
	}
}
