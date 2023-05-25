package entities;

import java.util.ArrayList;

public class Purchase {
	private static final String EOL = "\n";
	private static final String TAB = "\t";
	private ArrayList<Promotion> promotions = new ArrayList<Promotion>();

	public ArrayList<Promotion> getPromotions() {
		return promotions;
	}

	private ArrayList<Atraction> atractions = new ArrayList<Atraction>();

	public ArrayList<Atraction> getAtractions() {
		return atractions;
	}

	private double totalCost = 0;
	private User userPurchasing;

	public Purchase(User u) {
		this.setUserPurchasing(u);
	}

	@Override
	public String toString() {
		String regex = "[\\]\\[]";
		String res = this.userPurchasing.getName() + EOL;
		if (!promotions.isEmpty()) {
			res = res + "Adquirio las siguientes promociones: " + (this.promotions.toString()).replaceAll(regex, "")
					+ EOL;
		}
		if (!atractions.isEmpty()) {
			res = res + TAB + "Adquirio las siguientes Atracciones: "
					+ (this.atractions.toString()).replaceAll(regex, "") + EOL;
		}
		if (promotions.isEmpty() && atractions.isEmpty()) {
			res = res + TAB + "No ha hecho compras" + EOL;
		}
		return res;
	}

	public void add(Promotion pr) {
		this.promotions.add(pr);
		this.setTotalCost(this.getTotalCost() + pr.totalCost);
	}

	public void add(Atraction atr) {
		this.atractions.add(atr);
		this.setTotalCost(this.getTotalCost() + atr.getCost());
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public User getUserPurchasing() {
		return userPurchasing;
	}

	public void setUserPurchasing(User userPurchasing) {
		this.userPurchasing = userPurchasing;
	}

}
