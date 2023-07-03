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
		User noMoneyUser = new User("user_no_money", 0, 20, AtractionType.AVENTURA);

		ArrayList<Atraction> atractionsAvailable = new ArrayList<Atraction>();
		Atraction adventureAttraction = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractionsAvailable.add(adventureAttraction);

		Offer real = Atraction.createNewSuggestion(atractionsAvailable, cont, true, new ArrayList<Atraction>(),
				noMoneyUser);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_noTimeUser() {
		int[] cont = { 0 };
		User noTimeUser = new User("user_no_time", 100, 0, AtractionType.AVENTURA);

		ArrayList<Atraction> atractionsAvailable = new ArrayList<Atraction>();
		Atraction adventureAttraction = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractionsAvailable.add(adventureAttraction);

		Offer real = Atraction.createNewSuggestion(atractionsAvailable, cont, true, new ArrayList<Atraction>(),
				noTimeUser);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_UserEnoughMoney() {
		int[] cont = { 1 };
		User enoughMoneyUser = new User("user_enough_money", 50, 150, AtractionType.AVENTURA);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction adventureAttraction_toTake = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction anotherAdventureAttraction = new Atraction("Montana", 2, 50, 2, AtractionType.AVENTURA);
		atractions.add(adventureAttraction_toTake);
		atractions.add(anotherAdventureAttraction);

		ArrayList<Atraction> atractionsTaken = new ArrayList<Atraction>();
		Offer firstOffer_toTake = Atraction.createNewSuggestion(atractions, cont, true, atractionsTaken,
				enoughMoneyUser);
		enoughMoneyUser.acquire(firstOffer_toTake);
		atractionsTaken.addAll(firstOffer_toTake.getAtractions());

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, atractionsTaken, enoughMoneyUser);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_UserEnoughTime() {
		int[] cont = { 1 };
		User enoughTimeUser = new User("user_enough_time", 150, 50, AtractionType.AVENTURA);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction adventureAttraction_toTake = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction anotherAdventureAttraction = new Atraction("Montana", 50, 2, 2, AtractionType.AVENTURA);
		atractions.add(adventureAttraction_toTake);
		atractions.add(anotherAdventureAttraction);

		Offer firstOffer_toTake = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(),
				enoughTimeUser);
		ArrayList<Atraction> atractionsTaken = new ArrayList<Atraction>();
		enoughTimeUser.acquire(firstOffer_toTake);
		atractionsTaken.addAll(firstOffer_toTake.getAtractions());

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, atractionsTaken, enoughTimeUser);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_withoutSpots() {
		int[] cont = { 1 };
		User usuario = new User("user_BaseCase", 1000, 2000, AtractionType.DEGUSTACION);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction adventureAttraction = new Atraction("Parque", 50, 50, 0, AtractionType.AVENTURA);
		Atraction atraccionPaisaje = new Atraction("Montana", 50, 2, 0, AtractionType.PAISAJE);
		atractions.add(adventureAttraction);
		atractions.add(atraccionPaisaje);

		Offer real = Atraction.createNewSuggestion(atractions, cont, false, new ArrayList<Atraction>(), usuario);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_noPreferenceFound() {
		int[] cont = { 0 };
		User notFoundPrefence = new User("user_preference_not_found", 100, 200, AtractionType.DEGUSTACION);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction adventureAttraction = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction landscapeAttraction = new Atraction("Montana", 50, 50, 2, AtractionType.PAISAJE);
		atractions.add(adventureAttraction);
		atractions.add(landscapeAttraction);

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(),
				notFoundPrefence);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_preferenceFound() {
		int[] cont = { 0 };
		User user = new User("user_preference_found", 100, 200, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionDegustacion = new Atraction("Parque", 50, 50, 3, AtractionType.DEGUSTACION);
		Atraction landscapeAttraction = new Atraction("Montana", 50, 50, 2, AtractionType.PAISAJE);
		atractions.add(atraccionDegustacion);
		atractions.add(landscapeAttraction);

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), user);
		Assert.assertEquals(atraccionDegustacion, real);
	}

	@Test
	void atraction_SimulateDecraseSlots() {
		Atraction tastingAttraction = new Atraction("Parque", 2, 2, 1, AtractionType.DEGUSTACION);
		tastingAttraction.decreaseSlots();

		Integer expected = 0;
		Integer real = tastingAttraction.getSlots();

		Assert.assertEquals(expected, real);
	}

	@Test
	void atraction_SimulateDecraseSlots_AlreadyZero() {
		Atraction tastingAttraction = new Atraction("Parque", 2, 2, 0, AtractionType.DEGUSTACION);
		tastingAttraction.decreaseSlots();

		Integer expected = 0;
		Integer real = tastingAttraction.getSlots();

		Assert.assertEquals(expected, real);
	}

	@Test
	void atraction_sameAtraction() {
		int[] cont = { 1 };
		User usuario = new User("user_BaseCase", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction tastingAttraction = new Atraction("Montana", 50, 50, 2, AtractionType.DEGUSTACION);
		Atraction reapeatedTastingAttractionn = new Atraction("Montana", 50, 50, 40, AtractionType.DEGUSTACION);
		atractions.add(tastingAttraction);
		atractions.add(reapeatedTastingAttractionn);

		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
		Offer offerToTake = Atraction.createNewSuggestion(atractions, cont, true, compradas, usuario);
		usuario.acquire(offerToTake);
		tastingAttraction.decreaseSlots();
		compradas.add(tastingAttraction);

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, usuario);
		Assert.assertEquals(null, real);
	}
}