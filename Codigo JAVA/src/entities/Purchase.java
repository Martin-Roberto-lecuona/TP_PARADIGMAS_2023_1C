package entities;

import java.util.ArrayList;

public class Purchase {
	private static final String EOL = "\n";
	private static final String TAB = "\t";
	private ArrayList<Offer> offersList = new ArrayList<Offer>();

	private double totalCost = 0;
	private User userPurchasing;

	public Purchase(User u) {
		this.setUserPurchasing(u);
	}

	public ArrayList<Offer> getoffersList() {
		return offersList;
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
		String res = this.userPurchasing.getName() + EOL;
		if (!offersList.isEmpty()) {
			res = res + "Adquirio lo siguiente: " + (this.offersList.toString()).replaceAll(regex, "")
					+ EOL;
		}
		else {
			res = res + TAB + "No ha hecho compras" + EOL;
		}
		return res;
	}

	public void add(Offer off) {
		this.offersList.add(off);
		this.setTotalCost(this.getTotalCost() + off.getTotalCost());
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public void setUserPurchasing(User userPurchasing) {
		this.userPurchasing = userPurchasing;
	}

}
