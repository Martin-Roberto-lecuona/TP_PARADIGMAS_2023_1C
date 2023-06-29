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
		User usuarioSinPlata = new User("user_no_money", 0, 20, AtractionType.AVENTURA);

		ArrayList<Atraction> atractionsAvailable = new ArrayList<Atraction>();
		Atraction atraccionAventura = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractionsAvailable.add(atraccionAventura);

		Offer real = Atraction.createNewSuggestion(atractionsAvailable, cont, true, new ArrayList<Atraction>(),
				usuarioSinPlata);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_noTimeUser() {
		int[] cont = { 0 };
		User usuarioSinTiempo = new User("user_no_time", 100, 0, AtractionType.AVENTURA);

		ArrayList<Atraction> atractionsAvailable = new ArrayList<Atraction>();
		Atraction atraccionAventura = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractionsAvailable.add(atraccionAventura);

		Offer real = Atraction.createNewSuggestion(atractionsAvailable, cont, true, new ArrayList<Atraction>(),
				usuarioSinTiempo);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_UserEnoughMoney() {
		int[] cont = { 1 };
		User usuarioPlataJusta = new User("user_enough_money", 50, 150, AtractionType.AVENTURA);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionAventura_toTake = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction atraccionAventuraAnother = new Atraction("Montana", 2, 50, 2, AtractionType.AVENTURA);
		atractions.add(atraccionAventura_toTake);
		atractions.add(atraccionAventuraAnother);

		ArrayList<Atraction> atractionsTaken = new ArrayList<Atraction>();
		Offer firstOffer_toTake = Atraction.createNewSuggestion(atractions, cont, true, atractionsTaken,
				usuarioPlataJusta);
		usuarioPlataJusta.appoint(firstOffer_toTake);
		atractionsTaken.addAll(firstOffer_toTake.getAtractions());

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, atractionsTaken, usuarioPlataJusta);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_UserEnoughTime() {
		int[] cont = { 1 };
		User usuarioTiempoJusto = new User("user_enough_time", 150, 50, AtractionType.AVENTURA);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionAventura_toTake = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction atraccionAventuraAnother = new Atraction("Montana", 50, 2, 2, AtractionType.AVENTURA);
		atractions.add(atraccionAventura_toTake);
		atractions.add(atraccionAventuraAnother);

		Offer firstOffer_toTake = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(),
				usuarioTiempoJusto);
		ArrayList<Atraction> atractionsTaken = new ArrayList<Atraction>();
		usuarioTiempoJusto.appoint(firstOffer_toTake);
		atractionsTaken.addAll(firstOffer_toTake.getAtractions());

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, atractionsTaken, usuarioTiempoJusto);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_withoutSpots() {
		int[] cont = { 1 };
		User usuario = new User("user_BaseCase", 1000, 2000, AtractionType.DEGUSTACION);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionAventura = new Atraction("Parque", 50, 50, 0, AtractionType.AVENTURA);
		Atraction atraccionPaisaje = new Atraction("Montana", 50, 2, 0, AtractionType.PAISAJE);
		atractions.add(atraccionAventura);
		atractions.add(atraccionPaisaje);

		Offer real = Atraction.createNewSuggestion(atractions, cont, false, new ArrayList<Atraction>(), usuario);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_noPreferenceFound() {
		int[] cont = { 0 };
		User usuarioSinAtrPreferida = new User("user_preference_not_found", 100, 200, AtractionType.DEGUSTACION);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionAventura = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction atraccionPaisaje = new Atraction("Montana", 50, 50, 2, AtractionType.PAISAJE);
		atractions.add(atraccionAventura);
		atractions.add(atraccionPaisaje);

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(),
				usuarioSinAtrPreferida);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_preferenceFound() {
		int[] cont = { 0 };
		User user = new User("user_preference_found", 100, 200, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionDegustacion = new Atraction("Parque", 50, 50, 3, AtractionType.DEGUSTACION);
		Atraction atraccionPaisaje = new Atraction("Montana", 50, 50, 2, AtractionType.PAISAJE);
		atractions.add(atraccionDegustacion);
		atractions.add(atraccionPaisaje);

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), user);
		Assert.assertEquals(atraccionDegustacion, real);
	}

	@Test
	void atraction_SimulateDecraseSlots() {
		Atraction atraccionDegustacion = new Atraction("Parque", 2, 2, 1, AtractionType.DEGUSTACION);
		atraccionDegustacion.decreaseSlots();

		Integer expected = 0;
		Integer real = atraccionDegustacion.getSlots();

		Assert.assertEquals(expected, real);
	}

	@Test
	void atraction_SimulateDecraseSlots_AlreadyZero() {
		Atraction atraccionDegustacion = new Atraction("Parque", 2, 2, 0, AtractionType.DEGUSTACION);
		atraccionDegustacion.decreaseSlots();

		Integer expected = 0;
		Integer real = atraccionDegustacion.getSlots();

		Assert.assertEquals(expected, real);
	}

	@Test
	void atraction_sameAtraction() {
		int[] cont = { 1 };
		User usuario = new User("user_BaseCase", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionDegustacion = new Atraction("Montana", 50, 50, 2, AtractionType.DEGUSTACION);
		Atraction atraccionDegustacion_Repetido = new Atraction("Montana", 50, 50, 40, AtractionType.DEGUSTACION);
		atractions.add(atraccionDegustacion);
		atractions.add(atraccionDegustacion_Repetido);

		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
		Offer offerToTake = Atraction.createNewSuggestion(atractions, cont, true, compradas, usuario);
		usuario.appoint(offerToTake);
		atraccionDegustacion.decreaseSlots();
		compradas.add(atraccionDegustacion);

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, usuario);
		Assert.assertEquals(null, real);
	}

}