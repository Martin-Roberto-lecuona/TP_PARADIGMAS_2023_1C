package interfaces;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Atraction;
import entities.Offer;
import entities.Promotion;
import entities.Purchase;
import entities.User;

public abstract class UserInterface {
	private static final String accepts = "S";
	private static final String notAccepts = "N";

	public static void askUserAtraction(ArrayList<Atraction> offerArray, Scanner input, User user,
			Purchase compra, boolean basedOnPreferred) {
		int[] cont = { 0 }; // para poder pasar por referecia tiene que ser un array
		Offer off = Atraction.createNewSuggestion(offerArray, cont, basedOnPreferred,
				compra.getAllAtractions(),user);
		while (off != null) {
			userInputHandler(input, user, compra, off);
			cont[0]++;

			off = Atraction.createNewSuggestion(offerArray, cont, basedOnPreferred,
					compra.getAllAtractions(),user);
		}
	}

	public static void askUserPromotion(ArrayList<Promotion> offerArray, Scanner input, User user,
			Purchase compra, boolean basedOnPreferred) {
		int[] cont = { 0 }; // para poder pasar por referecia tiene que ser un array
		Offer off = Promotion.createNewSuggestion(offerArray, cont, basedOnPreferred,
				compra.getAllAtractions(),user);
		while (off != null) {
			userInputHandler(input, user, compra, off);
			cont[0]++;

			off = Promotion.createNewSuggestion(offerArray, cont, basedOnPreferred,
					compra.getAllAtractions(),user);
		}
	}
	public static void userInputHandler(Scanner input, User user, Purchase compra, Offer atr) {

		String option;
		do {

			System.out.println(atr.presentation());
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



}
