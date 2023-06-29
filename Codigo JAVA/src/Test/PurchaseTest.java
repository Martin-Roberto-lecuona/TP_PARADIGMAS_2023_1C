package Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import entities.Atraction;
import entities.Offer;
import entities.Porcentual;
import entities.Purchase;
import entities.User;
import enums.AtractionType;

public class PurchaseTest {

	@Test
	public void showAllAtractionsInPromoAndInAtraction() {
		User u1 = new User("u1", 120, 120, AtractionType.AVENTURA);
		Purchase compra = new Purchase(u1);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction a3 = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);
		Atraction a4 = new Atraction("ZZZ", 10, 10, 3, AtractionType.PAISAJE);
		atractions.add(a1);
		atractions.add(a2);
		Porcentual promo = new Porcentual(atractions, 0.35);

		compra.add(promo);
		compra.add(a3);
		compra.add(a4);

		ArrayList<Offer> expected = new ArrayList<Offer>();

		expected.add(a1);
		expected.add(a2);
		expected.add(a3);
		expected.add(a4);

		Assert.assertEquals(expected, compra.getAllAtractions());
	}

	@Test
	public void addNullVal() {
		User u1 = new User("u1", 120, 120, AtractionType.AVENTURA);
		Purchase compra = new Purchase(u1);
		ArrayList<Offer> expected = new ArrayList<Offer>();
		Assert.assertEquals(expected, compra.getAllAtractions());
	}

	@Test
	public void checkTimeAndCostInPurchase() {
		User u1 = new User("u1", 120, 120, AtractionType.AVENTURA);
		Purchase compra = new Purchase(u1);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("aaa", 10, 5, 3, AtractionType.PAISAJE);
		Atraction a2 = new Atraction("bbb", 20, 10, 2, AtractionType.AVENTURA);
		Atraction a3 = new Atraction("ccc", 30, 15, 3, AtractionType.DEGUSTACION);
		Atraction a4 = new Atraction("ZZZ", 40, 20, 3, AtractionType.PAISAJE);
		atractions.add(a1);
		atractions.add(a2);
		Porcentual promo = new Porcentual(atractions, 0.5);

		compra.add(promo);
		compra.add(a3);
		compra.add(a4);

		double costoTotal = (10 + 20) / 2 + 30 + 40;
		double tiempoTotal = 5 + 10 + 15 + 20;

		Assert.assertEquals(costoTotal, compra.getTotalCost(), 0.1);
		Assert.assertEquals(tiempoTotal, compra.getTotalTime(), 0.1);
	}

}
