package Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entities.Atraction;
import entities.AxB;
import entities.Offer;
import entities.Porcentual;
import entities.Absoluta;
import entities.Promotion;
import entities.User;
import enums.AtractionType;

public class PromocionesTest {

	@Test
	void Promotion_AxB_calculeDiscountedPriceCorrectly() {
		ArrayList<Atraction> aventuraPack_Atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction_aventuraPack = new ArrayList<Atraction>();
		Atraction atractionAventura = new Atraction("Parque", 20, 5, 3, AtractionType.AVENTURA);
		Atraction atractionAventura2 = new Atraction("Montana", 10, 50, 2, AtractionType.AVENTURA);
		Atraction atraccionAventura_free = new Atraction("Circuito", 30, 10, 3, AtractionType.AVENTURA);

		aventuraPack_Atractions.add(atractionAventura);
		aventuraPack_Atractions.add(atractionAventura2);
		freeAtraction_aventuraPack.add(atraccionAventura_free);
		AxB promotionAxB_Adventure = new AxB(aventuraPack_Atractions, freeAtraction_aventuraPack);

		Double expectedDiscountedCost = 30.0;
		Double real = promotionAxB_Adventure.getTotalCost();
		Assert.assertEquals(expectedDiscountedCost, real);
	}

	@Test
	void Promotion_calculeTimeCorrectly() {
		ArrayList<Atraction> aventuraPack_Atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction_aventuraPack = new ArrayList<Atraction>();
		Atraction atractionAventura = new Atraction("Parque", 20, 25, 3, AtractionType.AVENTURA);
		Atraction atractionAventura2 = new Atraction("Montana", 10, 25, 2, AtractionType.AVENTURA);
		Atraction atractionDegustacion = new Atraction("Bar", 30, 10, 3, AtractionType.DEGUSTACION);

		aventuraPack_Atractions.add(atractionAventura);
		aventuraPack_Atractions.add(atractionAventura2);
		freeAtraction_aventuraPack.add(atractionDegustacion);
		AxB promotionAxB_Adventure = new AxB(aventuraPack_Atractions, freeAtraction_aventuraPack);

		Double expectedTotalTime = 60.0;
		Double real = promotionAxB_Adventure.getTotalTime();
		Assert.assertEquals(expectedTotalTime, real);
	}

	@Test
	void Promotion_noMoneyUser() {
		int[] cont = { 0 };
		User userNoMoney = new User("user_no_money", 0, 20, AtractionType.AVENTURA);
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();

		Atraction atractionAventura = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction atractionPaisaje = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction atractionDegustacion = new Atraction("Bar", 10, 10, 3, AtractionType.DEGUSTACION);

		ArrayList<Atraction> packAtractions = new ArrayList<Atraction>();
		packAtractions.add(atractionAventura);
		packAtractions.add(atractionPaisaje);
		ArrayList<Atraction> freeAxBAtraction_pack = new ArrayList<Atraction>();
		freeAxBAtraction_pack.add(atractionDegustacion);

		AxB promotionAxB = new AxB(packAtractions, freeAxBAtraction_pack);
		Porcentual promotionPorcentaje = new Porcentual(packAtractions, 0.5);
		Absoluta promotionAbsoluta = new Absoluta(packAtractions, 20);

		promotions.add(promotionAxB);
		promotions.add(promotionPorcentaje);
		promotions.add(promotionAbsoluta);

		Offer real = Promotion.createNewSuggestion(promotions, cont, true, new ArrayList<Atraction>(), userNoMoney);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_noTimeUser() {
		int[] cont = { 0 };
		User userWithoutTime = new User("user_no_time", 100, 0, AtractionType.AVENTURA);
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();

		ArrayList<Atraction> packAtractions = new ArrayList<Atraction>();
		Atraction atractionPaisaje = new Atraction("Parque", 50, 5, 3, AtractionType.PAISAJE);
		Atraction atractionAventura = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction atractionDegustacion = new Atraction("Bar", 10, 10, 3, AtractionType.DEGUSTACION);
		packAtractions.add(atractionAventura);
		packAtractions.add(atractionPaisaje);
		ArrayList<Atraction> freeAxBAtractions = new ArrayList<Atraction>();
		freeAxBAtractions.add(atractionDegustacion);

		AxB promotionAxB = new AxB(packAtractions, freeAxBAtractions);
		Porcentual promotionPorcentaje = new Porcentual(packAtractions, 0.5);
		Absoluta promotionAbsoluta = new Absoluta(packAtractions, 20);

		promotions.add(promotionAxB);
		promotions.add(promotionPorcentaje);
		promotions.add(promotionAbsoluta);

		Offer real = Promotion.createNewSuggestion(promotions, cont, true, new ArrayList<Atraction>(), userWithoutTime);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_UserEnoughMoney() {
		int[] cont = { 0 };
		User userEnoughMoney = new User("user_enough_money", 30, 150, AtractionType.AVENTURA);
		ArrayList<Atraction> compradas = new ArrayList<Atraction>();

		ArrayList<Atraction> aventuraPack_Atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction_aventuraPack = new ArrayList<Atraction>();
		Atraction atractionAventura = new Atraction("Parque", 20, 5, 3, AtractionType.AVENTURA);
		Atraction atractionAventura2 = new Atraction("Montana", 10, 50, 2, AtractionType.AVENTURA);
		Atraction atraccionAventura_free = new Atraction("Circuito", 30, 10, 3, AtractionType.AVENTURA);

		aventuraPack_Atractions.add(atractionAventura);
		aventuraPack_Atractions.add(atractionAventura2);
		freeAtraction_aventuraPack.add(atraccionAventura_free);
		AxB promotionAxB_Adventure = new AxB(aventuraPack_Atractions, freeAtraction_aventuraPack);

		ArrayList<Atraction> porcentualPack_Atractions = new ArrayList<Atraction>();
		Atraction atractionPaisaje = new Atraction("Lago", 50, 5, 3, AtractionType.PAISAJE);
		Atraction atractionDegustacion = new Atraction("Laguna", 50, 50, 2, AtractionType.DEGUSTACION);

		porcentualPack_Atractions.add(atractionPaisaje);
		porcentualPack_Atractions.add(atractionDegustacion);

		Porcentual promotionPorcentaje = new Porcentual(porcentualPack_Atractions, 0.5);

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(promotionPorcentaje);
		promotions.add(promotionAxB_Adventure);

		Offer offerToTake = Promotion.createNewSuggestion(promotions, cont, true, compradas, userEnoughMoney);
		userEnoughMoney.acquire(offerToTake);
		compradas.addAll(offerToTake.getAtractions());

		Offer real = Promotion.createNewSuggestion(promotions, cont, false, compradas, userEnoughMoney);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_UserEnoughTime() {
		int[] cont = { 0 };
		User userEnoughTime = new User("user_enough_time", 500, 60, AtractionType.AVENTURA);
		ArrayList<Atraction> compradas = new ArrayList<Atraction>();

		ArrayList<Atraction> aventuraPack_Atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction_aventuraPack = new ArrayList<Atraction>();
		Atraction atractionAventura = new Atraction("Parque", 20, 25, 3, AtractionType.AVENTURA);
		Atraction atractionAventura2 = new Atraction("Montana", 10, 25, 2, AtractionType.AVENTURA);
		Atraction atractionDegustacion = new Atraction("Bar", 30, 10, 3, AtractionType.DEGUSTACION);

		aventuraPack_Atractions.add(atractionAventura);
		aventuraPack_Atractions.add(atractionAventura2);
		freeAtraction_aventuraPack.add(atractionDegustacion);
		AxB promotionAxB_Adventure = new AxB(aventuraPack_Atractions, freeAtraction_aventuraPack);

		ArrayList<Atraction> absolutaPack_Atractions = new ArrayList<Atraction>();
		Atraction atractionPaisaje = new Atraction("Lago", 50, 5, 3, AtractionType.PAISAJE);

		absolutaPack_Atractions.add(atractionPaisaje);
		Absoluta promotionAbsoluta = new Absoluta(absolutaPack_Atractions, 10);

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(promotionAbsoluta);
		promotions.add(promotionAxB_Adventure);

		Offer offerToTake = Promotion.createNewSuggestion(promotions, cont, true, compradas, userEnoughTime);
		userEnoughTime.acquire(offerToTake);
		compradas.addAll(offerToTake.getAtractions());

		Offer real = Promotion.createNewSuggestion(promotions, cont, false, compradas, userEnoughTime);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_withoutSlotsInAtraction() {
		int[] cont = { 0 };
		User usuario = new User("user_BaseCase", 1000, 2000, AtractionType.DEGUSTACION);

		ArrayList<Atraction> pack_Atractions = new ArrayList<Atraction>();
		Atraction atractionAventura = new Atraction("Parque", 20, 25, 0, AtractionType.AVENTURA);
		Atraction atractionDegustacion = new Atraction("Bar", 30, 10, 10, AtractionType.DEGUSTACION);

		pack_Atractions.add(atractionAventura);
		pack_Atractions.add(atractionDegustacion);
		Absoluta promotionAbsoluta = new Absoluta(pack_Atractions, 10);

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(promotionAbsoluta);

		Offer real = Promotion.createNewSuggestion(promotions, cont, false, new ArrayList<Atraction>(), usuario);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_offerByPreference_FirstMaxPrice() {
		int[] cont = { 0 };
		User normalUser = new User("base_case_user", 1000, 2000, AtractionType.AVENTURA);
		ArrayList<Atraction> packAtractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction_pack = new ArrayList<Atraction>();

		Atraction atractionAventura = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction atractionAventura2 = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction atractionFree_degustacion = new Atraction("Cine", 10, 10, 3, AtractionType.DEGUSTACION);

		packAtractions.add(atractionAventura);
		packAtractions.add(atractionAventura2);
		freeAtraction_pack.add(atractionFree_degustacion);

		AxB promoAxB = new AxB(packAtractions, freeAtraction_pack);
		Porcentual promoPorcentaje = new Porcentual(packAtractions, 0.5);
		ArrayList<Promotion> allPromotions = new ArrayList<Promotion>();
		allPromotions.add(promoAxB);
		allPromotions.add(promoPorcentaje);

		Offer real = Promotion.createNewSuggestion(allPromotions, cont, true, new ArrayList<Atraction>(), normalUser);
		Assert.assertEquals(promoAxB, real);
	}

	@Test
	void Promotion_offerByPreference_FirstMinTime() {
		int[] cont = { 0 };
		User normalUser = new User("base_case_user", 1000, 2000, AtractionType.AVENTURA);

		ArrayList<Atraction> aventuraPack_Atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction_aventuraPack = new ArrayList<Atraction>();
		Atraction atractionAventura_inPack = new Atraction("Parque", 20, 5, 3, AtractionType.AVENTURA);
		Atraction atractionAventuraAnother_inPack = new Atraction("Montana", 10, 2, 2, AtractionType.AVENTURA);
		Atraction atractionFree_inPack = new Atraction("Bar", 30, 3, 3, AtractionType.DEGUSTACION);
		aventuraPack_Atractions.add(atractionAventura_inPack);
		aventuraPack_Atractions.add(atractionAventuraAnother_inPack);
		freeAtraction_aventuraPack.add(atractionFree_inPack);
		AxB promotionAxB = new AxB(aventuraPack_Atractions, freeAtraction_aventuraPack);

		ArrayList<Atraction> packAtractions = new ArrayList<Atraction>();
		Atraction atractionPaisaje = new Atraction("Circuito", 20, 5, 3, AtractionType.AVENTURA);
		Atraction atractionDegustacion = new Atraction("Resto", 20, 10, 3, AtractionType.DEGUSTACION);
		packAtractions.add(atractionPaisaje);
		packAtractions.add(atractionDegustacion);
		Absoluta promotionPorcentaje = new Absoluta(packAtractions, 10);

		ArrayList<Promotion> allPromotions = new ArrayList<Promotion>();
		allPromotions.add(promotionAxB);
		allPromotions.add(promotionPorcentaje);

		Offer real = Promotion.createNewSuggestion(allPromotions, cont, true, new ArrayList<Atraction>(), normalUser);
		Assert.assertEquals(promotionAxB, real);
	}

	@Test
	void Promotion_noPreferenceFound() {
		int[] cont = { 0 };
		User userNotFoundPreference = new User("user_preference_not_found", 1000, 2000, AtractionType.PAISAJE);

		ArrayList<Atraction> aventuraPack_Atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction_aventuraPack = new ArrayList<Atraction>();
		Atraction atractionAventura_inPack = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction atractionAventuraAnother_inPack = new Atraction("Montana", 50, 50, 2, AtractionType.AVENTURA);
		Atraction atractionFree_inPack = new Atraction("Bar", 10, 10, 3, AtractionType.DEGUSTACION);

		aventuraPack_Atractions.add(atractionAventura_inPack);
		aventuraPack_Atractions.add(atractionAventuraAnother_inPack);
		freeAtraction_aventuraPack.add(atractionFree_inPack);

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		AxB promotionAxB_Adventure = new AxB(aventuraPack_Atractions, freeAtraction_aventuraPack);
		promotions.add(promotionAxB_Adventure);

		Offer real = Promotion.createNewSuggestion(promotions, cont, true, new ArrayList<Atraction>(),
				userNotFoundPreference);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_notOfferIfAlreadyBought() {
		int[] cont = { 0 };
		User normalUser = new User("base_case_user", 1000, 2000, AtractionType.AVENTURA);
		ArrayList<Atraction> compradas = new ArrayList<Atraction>();

		Atraction adventureAttraction = new Atraction("Parque", 50, 50, 3, AtractionType.AVENTURA);
		Atraction landscapeAttraction = new Atraction("Montana", 50, 50, 1, AtractionType.PAISAJE);
		Atraction tastingAttraction = new Atraction("Bar", 10, 10, 3, AtractionType.DEGUSTACION);

		ArrayList<Atraction> aventuraPack_Atractions = new ArrayList<Atraction>();
		aventuraPack_Atractions.add(adventureAttraction);
		aventuraPack_Atractions.add(landscapeAttraction);

		ArrayList<Atraction> absolutaPack_Atractions = new ArrayList<Atraction>();
		absolutaPack_Atractions.add(tastingAttraction);
		absolutaPack_Atractions.add(landscapeAttraction);

		Porcentual promotionPorcentaje = new Porcentual(aventuraPack_Atractions, 0.5);
		Absoluta promotionAbsoluta = new Absoluta(absolutaPack_Atractions, 10);
		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(promotionPorcentaje);
		promotions.add(promotionAbsoluta);

		Offer offerToTake = Promotion.createNewSuggestion(promotions, cont, true, compradas, normalUser);
		normalUser.acquire(offerToTake);
		offerToTake.decreaseSlots();
		compradas.addAll(offerToTake.getAtractions());

		Offer real = Promotion.createNewSuggestion(promotions, cont, false, new ArrayList<Atraction>(), normalUser);
		Assert.assertEquals(null, real);
	}

	@Test
	void Promotion_noOfferIfSoldOut() {
		int[] cont = { 0 };
		User user_FirstToGo = new User("user_first_to_go", 1000, 2000, AtractionType.PAISAJE);

		ArrayList<Atraction> aventuraPack_Atractions = new ArrayList<Atraction>();
		ArrayList<Atraction> freeAtraction_aventuraPack = new ArrayList<Atraction>();
		Atraction atractionAventura = new Atraction("Parque", 20, 25, 3, AtractionType.AVENTURA);
		Atraction atractionPaisaje = new Atraction("Montana", 10, 25, 1, AtractionType.PAISAJE);
		Atraction atractionDegustacion = new Atraction("Bar", 30, 10, 3, AtractionType.DEGUSTACION);

		aventuraPack_Atractions.add(atractionAventura);
		aventuraPack_Atractions.add(atractionPaisaje);
		freeAtraction_aventuraPack.add(atractionDegustacion);
		AxB promotionAxB_Adventure = new AxB(aventuraPack_Atractions, freeAtraction_aventuraPack);

		ArrayList<Atraction> absolutaPack_Atractions = new ArrayList<Atraction>();
		absolutaPack_Atractions.add(atractionDegustacion);
		absolutaPack_Atractions.add(atractionAventura);
		Absoluta promotionAbsoluta = new Absoluta(absolutaPack_Atractions, 10);

		ArrayList<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(promotionAxB_Adventure);
		promotions.add(promotionAbsoluta);

		Offer offerToFirstUser = Promotion.createNewSuggestion(promotions, cont, false, new ArrayList<Atraction>(),
				user_FirstToGo);
		user_FirstToGo.acquire(offerToFirstUser);
		offerToFirstUser.decreaseSlots();

		User user_SecondToGo = new User("user_second_to_go", 2000, 1000, AtractionType.PAISAJE);
		Offer real = Promotion.createNewSuggestion(promotions, cont, false, new ArrayList<Atraction>(),
				user_SecondToGo);
		Assert.assertEquals(promotionAbsoluta, real);
	}
}