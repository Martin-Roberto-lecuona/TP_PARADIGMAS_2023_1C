package entities;

import java.util.ArrayList;

public class Purchase {
	private static final String EOL = "\n";
	private ArrayList<Offer> offersList = new ArrayList<Offer>();

	private double totalCost = 0;
	private double totalTime = 0;
	private User userPurchasing;

	public Purchase(User u) {
		this.setUserPurchasing(u);
	}

	public ArrayList<Atraction> getAllAtractions() {
		ArrayList<Atraction> res = new ArrayList<Atraction>();
		for (Offer offer : this.offersList) {
			res.addAll(offer.getAtractions());
		}
		return res;
	}

	@Override
	public String toString() {
		String regex = "[\\]\\[]";
		String toString = this.userPurchasing.getName();
		toString = String.format("\t\t\t** %15s %-24s**\n", " Visitante", toString);
		if (!offersList.isEmpty()) {
			toString = toString + "Adquirio lo siguiente:\n" + (this.offersList.toString()).replaceAll(regex, "") + EOL
					+ "\t\t\t\tSU ITINERARIO FINAL SERA DE:\n" + "\t\t\t\t*COSTO TOTAL: $" + this.totalCost + EOL
					+ "\t\t\t\t*TIEMPO TOTAL: " + this.totalTime + "hs \n";
		} else {
			toString = toString + "\n \t\t\t\t No ha realizado compras.\n";
		}
		return toString;
	}

	public void add(Offer off) {
		this.offersList.add(off);
		double totalCost = this.getTotalCost() + off.getTotalCost();
		double totalTime = this.getTotalTime() + off.getTotalTime();
		this.setTotalCost(totalCost);
		this.setTotalTime(totalTime);
	}

	public double getTotalCost() {
		return totalCost;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public void setUserPurchasing(User userPurchasing) {
		this.userPurchasing = userPurchasing;
	}

}
