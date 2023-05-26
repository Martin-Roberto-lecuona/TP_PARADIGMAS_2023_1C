package interfaces;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Atraction;
import entities.Promotion;
import entities.Purchase;
import entities.User;

public abstract class UserInterface {
	private static final String accepts = "S";
	private static final String notAccepts = "N";

	public static void askUserForAtractions(ArrayList<Atraction> atractionArray, Scanner input, User user,
			Purchase compra, boolean basedOnPreferred) {
		int[] cont = { 0 }; // para poder pasar por referecia tiene que ser un array
		Atraction atr = user.createNewAtractionSuggestion(atractionArray, cont, basedOnPreferred,
				compra.getAllAtractions());
		while (atr != null) {
			userInputHandler(input, user, compra, atr);
			cont[0]++;

			atr = user.createNewAtractionSuggestion(atractionArray, cont, basedOnPreferred, compra.getAllAtractions());
		}
	}

	public static void askUserForPromotions(ArrayList<Promotion> promotionArrayList, Scanner input, User user,
			Purchase compra, boolean basedOnPreferred) {
		int[] cont = { 0 };// para poder pasar por referecia tiene que ser un array

		Promotion promo = user.createNewPromotionSuggestion(promotionArrayList, cont, basedOnPreferred,
				compra.getAllAtractions());

		while (promo != null) {

			userInputHandler(input, user, compra, promo);
			cont[0]++;
			promo = user.createNewPromotionSuggestion(promotionArrayList, cont, basedOnPreferred,
					compra.getAllAtractions());
		}
	}

	public static void userInputHandler(Scanner input, User user, Purchase compra, Atraction atr) {

		String option;
		do {

			System.out.println("La Atraccion que le presentamos es: " + atr);
			System.out.println("Acepta la Atraccion 'S' para si 'N' para no ");
			option = input.next();
			option = option.toUpperCase(); 
			
			if (option.equals(accepts)) {
				user.appoint(atr);
				atr.decreaseSlots();
				compra.add(atr);
			}
			else if (!option.equals(notAccepts)) {
				System.out.println("La opcion no es correcta");
			}
		} while (!option.equals(notAccepts) && !option.equals(accepts));
	}

	public static void userInputHandler(Scanner input, User user, Purchase compra, Promotion promo) {
		String option;
		do {

			System.out.println("La Promocion que le presentamos es: " + promo);
			System.out.println("Acepta la Promo 'S' para si 'N' para no ");
			option = input.next();
			option = option.toUpperCase(); 

			if (option.equals(accepts)) {
				user.appoint(promo);
				promo.decreaseSlotsToAllAtractionsInPromo();
				decreaseSlotsToAllAtractionsInPromo(promo);
				compra.add(promo);
			}
			else if (!option.equals(notAccepts)) {
				System.out.println("La opcion no es correcta");
			}
		} while (!option.equals(notAccepts) && !option.equals(accepts));
	}

	private static void decreaseSlotsToAllAtractionsInPromo(Promotion promo) {
		for (Atraction atr : promo.getAtractionList()) {
			atr.decreaseSlots();
		}
	}

}
