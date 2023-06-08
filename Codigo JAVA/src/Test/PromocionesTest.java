package Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entities.Atraction;
import entities.AxB;
import entities.Offer;
import entities.Promotion;
import entities.User;
import enums.AtractionType;


public class PromocionesTest {

	@Test
	void Promotion_noMoneyUser() {
		int[] cont = { 0 };
		User u = new User("Sin Plata1", 0, 20, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtractions = new ArrayList<Atraction>();
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		Atraction a1 = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction a3 = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);
		atractions.add(a1);
		atractions.add(a2);
		freeAtractions.add(a3);
		AxB promoAxB = new AxB(atractions,freeAtractions);
		promotions.add(promoAxB);
		Offer real = Promotion.createNewSuggestion(promotions, cont, true, new ArrayList<Atraction>(), u);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_noTimeUser() {
		int[] cont = { 0 };
		User withoutTime = new User("No time", 100, 0, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtractions = new ArrayList<Atraction>();
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		Atraction a1 = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction a3 = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);
		atractions.add(a1);
		atractions.add(a2); 
		freeAtractions.add(a3);
		AxB promoAxB = new AxB(atractions,freeAtractions);
		promotions.add(promoAxB);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), withoutTime);
		Assert.assertEquals(null, real);
	}
	
	

	@Test
	void Promotion_enoughMoney() {
		int[] cont = { 1 };
		User enoughMoney = new User("Plata justa", 50, 150, AtractionType.AVENTURA);
		ArrayList<Atraction> atraction1 = new ArrayList<Atraction>();
		ArrayList<Atraction> atraction2 = new ArrayList<Atraction>();
		ArrayList<Promotion> bought1 = new ArrayList<Promotion>();
		ArrayList<Atraction> freeAtraction1 = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction2 = new ArrayList<Atraction>();
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		Atraction a1 = new Atraction("Parque", 50, 5, 3, AtractionType.AVENTURA);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction a3 = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);
		Atraction a4 = new Atraction("Lago", 50, 5, 3, AtractionType.AVENTURA);
		Atraction a5 = new Atraction("Laguna", 50, 50, 2, AtractionType.PAISAJE);
		Atraction a6 = new Atraction("Laguito", 10, 10, 3, AtractionType.DEGUSTACION);
		
		atraction1.add(a1);
		atraction1.add(a2);
		
		atraction2.add(a4);
		atraction2.add(a5);
		
		freeAtraction1.add(a3);
		freeAtraction2.add(a6);
		
		AxB promoAxB1 = new AxB(atraction1,freeAtraction1);
		AxB promoAxB2 = new AxB(atraction2,freeAtraction2);
		
		promotions.add(promoAxB1);
		promotions.add(promoAxB2);

		enoughMoney.appoint(promoAxB1);
		//decrese slots
		
		bought1.add(promoAxB1);
		
		Offer real = Promotion.createNewSuggestion(promotions, cont, true, freeAtraction2, enoughMoney);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_enoughTime() {
		int[] cont = { 1 };
		User enoughTime = new User("Tiempo justo", 110, 50, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction a3 = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);
		
		atractions.add(a1);
		atractions.add(a2);
		freeAtractions.add(a3);
		
		AxB promoAxB = new AxB(atractions,freeAtractions);
		enoughTime.appoint(promoAxB);
		
		Offer real = Atraction.createNewSuggestion(atractions, cont, true,atractions, enoughTime);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_noPreference() {
		int[] cont = { 0 };
		User notFoundPreference = new User("Preferencia no Encontrada", 1000, 2000, AtractionType.PAISAJE);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction a3 = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);
		
		atractions.add(a1);
		atractions.add(a2);
		freeAtractions.add(a3);
		
		Offer real = Atraction.createNewSuggestion(atractions, cont, true,atractions, notFoundPreference);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_baseCase() {
		int[] cont = { 0 };
		User normalUser = new User("Soy bueno", 1000, 2000, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction a3 = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		atractions.add(a1);
		atractions.add(a2);
		freeAtractions.add(a3);
		AxB promoAxB = new AxB(atractions,freeAtractions);
		promotions.add(promoAxB);
		Offer real = Atraction.createNewSuggestion(promotions, cont, true,atractions, normalUser);
		Assert.assertEquals(promoAxB, real);
	}
//
//	@Test
//	void Promotion_decraseSlots() {
//		int[] cont = { 1 };
//		User u1 = new User("Juan", 1000, 2000, AtractionType.DEGUSTACION);
//		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
//		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
//		Atraction a1 = new Atraction("Parque", 50, 50, 1, AtractionType.DEGUSTACION);
//		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.DEGUSTACION);
//		atractions.add(a1);
//		atractions.add(a2);
//		u1.appoint(a1);
//		a1.decreaseSlots();
//		compradas.add(a1);
//		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, u1);
//		Assert.assertEquals(null, real);
//	}
//	
//	@Test
//	void Promotion_sameAtraction() {
//		int[] cont = { 1 };
//		User u1 = new User("Juan", 1000, 2000, AtractionType.DEGUSTACION);
//		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
//		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
//		Atraction a1 = new Atraction("Montana", 50, 50, 2, AtractionType.DEGUSTACION);
//		Atraction a2 = new Atraction("Montana", 50, 50, 40, AtractionType.DEGUSTACION);
//		atractions.add(a1);
//		atractions.add(a2);
//		u1.appoint(a1);
//		a1.decreaseSlots();
//		compradas.add(a1);
//		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, u1);
//		Assert.assertEquals(null, real);
//	}
	

}
