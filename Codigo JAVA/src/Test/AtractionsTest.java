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
		User usuarioSinPlata = new User("Sin Plata1", 0, 20, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionPaisaje = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction atraccionAventura = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractions.add(atraccionPaisaje);
		atractions.add(atraccionAventura);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), usuarioSinPlata);
		Assert.assertEquals(null, real);
	}
	@Test
	void atraction_noTimeUser() {
		int[] cont = { 0 };
		User usuarioSinTiempo = new User("Sin time", 100, 0, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionPaisaje = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction atraccionAventura = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractions.add(atraccionPaisaje);
		atractions.add(atraccionAventura);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), usuarioSinTiempo);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_enoughMoney() {
		int[] cont = { 1 };
		User usuarioPlataJusta = new User("Plata justa", 50, 150, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionAventura = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction atraccionAventura2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractions.add(atraccionAventura);
		atractions.add(atraccionAventura2);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), usuarioPlataJusta);
		usuarioPlataJusta.appoint(real);
		real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), usuarioPlataJusta);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_enoughTime() {
		int[] cont = { 1 };
		User usuarioTiempoJusto = new User("Tiempo justo", 150, 50, AtractionType.AVENTURA);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionAventura = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction atraccionAventura2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		atractions.add(atraccionAventura);
		atractions.add(atraccionAventura2);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), usuarioTiempoJusto);
		usuarioTiempoJusto.appoint(real);
		real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), usuarioTiempoJusto);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_noPreference() {
		int[] cont = { 0 };
		User usuarioSinAtrPreferida = new User("Preferencia no Encontrada", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionAventura = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction atraccionPaisaje = new Atraction("Montana", 50, 50, 2, AtractionType.PAISAJE);
		atractions.add(atraccionAventura);
		atractions.add(atraccionPaisaje);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), usuarioSinAtrPreferida);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_baseCase() {
		int[] cont = { 0 };
		User user =  new User("Soy bueno", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		Atraction atraccionDegustacion = new Atraction("Parque", 50, 50, 3, AtractionType.DEGUSTACION);
		Atraction atraccionPaisaje = new Atraction("Montana", 50, 50, 2, AtractionType.PAISAJE);
		atractions.add(atraccionDegustacion);
		atractions.add(atraccionPaisaje);

		Offer real = Atraction.createNewSuggestion(atractions, cont, true, new ArrayList<Atraction>(), user);
		Assert.assertEquals(atraccionDegustacion, real);
	}

	@Test
	void atraction_decraseSlots() {
		int[] cont = { 1 };
		User usuario = new User("Juan", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
		Atraction atraccionDegustacion = new Atraction("Parque", 50, 50, 1, AtractionType.DEGUSTACION);
		Atraction atraccionDegustacion2 = new Atraction("Montana", 50, 50, 2, AtractionType.DEGUSTACION);
		atractions.add(atraccionDegustacion);
		atractions.add(atraccionDegustacion2);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, usuario);
		usuario.appoint(atraccionDegustacion);
		atraccionDegustacion.decreaseSlots();
		compradas.add(atraccionDegustacion2);
		real = Atraction.createNewSuggestion(atractions, cont, true, compradas, usuario);
		Assert.assertEquals(null, real);
	}

	@Test
	void atraction_sameAtraction() {
		int[] cont = { 1 };
		User usuario = new User("Juan", 1000, 2000, AtractionType.DEGUSTACION);
		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> compradas = new ArrayList<Atraction>();
		Atraction atraccionDegustacion = new Atraction("Montana", 50, 50, 2, AtractionType.DEGUSTACION);
		Atraction atraccionDegustacionRepetido = new Atraction("Montana", 50, 50, 40, AtractionType.DEGUSTACION);
		atractions.add(atraccionDegustacion);
		atractions.add(atraccionDegustacionRepetido);
		Offer real = Atraction.createNewSuggestion(atractions, cont, true, compradas, usuario);
		usuario.appoint(real);
		atraccionDegustacion.decreaseSlots();
		compradas.add(atraccionDegustacion);
		real = Atraction.createNewSuggestion(atractions, cont, true, compradas, usuario);
		Assert.assertEquals(null, real);
	}

}
