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
		User user = new User("user", 120, 120, AtractionType.AVENTURA);
		Purchase compra = new Purchase(user);

		ArrayList<Atraction> packAtractions = new ArrayList<Atraction>();
		Atraction atractionPaisaje_inPack = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction atractionAventura_inPack = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		packAtractions.add(atractionPaisaje_inPack);
		packAtractions.add(atractionAventura_inPack);
		Porcentual porcentualPromotion = new Porcentual(packAtractions, 0.35);
		compra.add(porcentualPromotion);

		Atraction atractionDegustacion = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);
		compra.add(atractionDegustacion);
		Atraction atractionPaisajeOther = new Atraction("Circuito", 10, 10, 3, AtractionType.PAISAJE);
		compra.add(atractionPaisajeOther);
		
		ArrayList<Offer> expected = new ArrayList<Offer>();

		expected.add(atractionPaisaje_inPack);
		expected.add(atractionAventura_inPack);
		expected.add(atractionDegustacion);
		expected.add(atractionPaisajeOther);

		Assert.assertEquals(expected, compra.getAllAtractions());
	}

	@Test
	public void addNullVal() {
		User user = new User("user", 120, 120, AtractionType.AVENTURA);
		Purchase compra = new Purchase(user);
		ArrayList<Offer> expected = new ArrayList<Offer>();
		Assert.assertEquals(expected, compra.getAllAtractions());
	}

	@Test
	public void checkTimeAndCostInPurchase() {
		User user = new User("user", 120, 120, AtractionType.AVENTURA);
		Purchase compra = new Purchase(user);

		ArrayList<Atraction> packAtractions = new ArrayList<Atraction>();
		Atraction atractionPaisaje_inPack = new Atraction("Parque", 10, 5, 3, AtractionType.PAISAJE);
		Atraction atractionAventura_inPack = new Atraction("Montana", 20, 10, 2, AtractionType.AVENTURA);
		packAtractions.add(atractionPaisaje_inPack);
		packAtractions.add(atractionAventura_inPack);
		Porcentual porcentualPromotion = new Porcentual(packAtractions, 0.5);
		compra.add(porcentualPromotion);

		Atraction atractionDegustacion = new Atraction("Cine", 30, 15, 3, AtractionType.DEGUSTACION);
		compra.add(atractionDegustacion);
		Atraction atractionPaisajeOther = new Atraction("Circuito", 40, 20, 3, AtractionType.PAISAJE);
		compra.add(atractionPaisajeOther);

		double costoTotal = (10 + 20) / 2 + 30 + 40;
		double tiempoTotal = 5 + 10 + 15 + 20;

		Assert.assertEquals(costoTotal, compra.getTotalCost(), 0.1);
		Assert.assertEquals(tiempoTotal, compra.getTotalTime(), 0.1);
	}
}
