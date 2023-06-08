package Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entities.Atraction;
import entities.Offer;
import entities.User;
import enums.AtractionType;


class AtractionsTest {

	@Test
	void atraction_noMoneyUser() {
		int[] cont = { 0 };
		User uSinPlata1 = new User("Sin Plata1", 0, 20, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractions.add(a1);
		atractions.add(a2);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), uSinPlata1);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_noTimeUser() {
		int[] cont = { 0 };
		User uSinTiempo1 = new User("Sin time", 100, 0, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractions.add(a1);
		atractions.add(a2);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), uSinTiempo1);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_enoughMoney() {
		int[] cont = { 1 };
		User plataJusta = new User("Plata justa", 50, 150, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 5, 3, AtractionType.AVENTURA);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractions.add(a1);
		atractions.add(a2);
		plataJusta.appoint(a1);
		compradas.add(a2);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, plataJusta);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_enoughTime() {
		int[] cont = { 1 };
		User tiempoJusto = new User("Tiempo justo", 150, 50, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractions.add(a1);
		atractions.add(a2);
		tiempoJusto.appoint(a1);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), tiempoJusto);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_noPreference() {
		int[] cont = { 0 };
		User preferenciaInex = new User("Preferencia no Encontrada", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.PAISAJE);
		atractions.add(a1);
		atractions.add(a2);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), preferenciaInex);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_baseCase() {
		int[] cont = { 0 };
		User u = new User("Soy bueno", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 50, 3, AtractionType.DEGUSTACION);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.PAISAJE);
		atractions.add(a1);
		atractions.add(a2);

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), u);
		Assert.assertEquals(a1, real);
	}

	@Test
	void atraction_decraseSlots() {
		int[] cont = { 1 };
		User u1 = new User("Juan", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Parque", 50, 50, 1, AtractionType.DEGUSTACION);
		Atraction a2 = new Atraction("Montana", 50, 50, 2, AtractionType.DEGUSTACION);
		atractions.add(a1);
		atractions.add(a2);
		u1.appoint(a1);
		a1.decreaseSlots();
		compradas.add(a1);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, u1);
		Assert.assertEquals(null, real);
	}
	
	@Test
	void atraction_sameAtraction() {
		int[] cont = { 1 };
		User u1 = new User("Juan", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
		Atraction a1 = new Atraction("Montana", 50, 50, 2, AtractionType.DEGUSTACION);
		Atraction a2 = new Atraction("Montana", 50, 50, 40, AtractionType.DEGUSTACION);
		atractions.add(a1);
		atractions.add(a2);
		u1.appoint(a1);
		a1.decreaseSlots();
		compradas.add(a1);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, u1);
		Assert.assertEquals(null, real);
	}

}



