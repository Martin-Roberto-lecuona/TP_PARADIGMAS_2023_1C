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
		return super.toString() + " free: " + free.getName();
	}

	@Override
	public void calculateTotalWithDiscount() {		
	}
	
	public void calculateTotalTime() {		
		this.totalTime += free.getEstimatedTime();
	}
}
