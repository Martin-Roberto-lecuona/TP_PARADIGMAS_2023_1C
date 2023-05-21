package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class AxB extends Promotion{

	private Atraction free;

	public AxB(ArrayList<Atraction> atractions, Atraction free) {
		super(atractions);
		this.free = free;
		calculateTotalTime();
	}
	
	@Override
	public String toString() {
		return "AxB [free=" + free + ", totalCost=" + totalCost + ", totalTime=" + totalTime + ", discountedTotalCost="
				+ discountedTotalCost + ", atractionNames=" + Arrays.toString(atractionNames) + ", atractionList="
				+ atractionList + "]";
	}

	@Override
	public void calculateTotalWithDiscount() {		
	}
	
	public void calculateTotalTime() {		
		this.totalTime += free.getEstimatedTime();
	}
}
