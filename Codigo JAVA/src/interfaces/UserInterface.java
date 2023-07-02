package interfaces;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Atraction;
import entities.FileParser;
import entities.Offer;
import entities.Promotion;
import entities.Purchase;
import entities.User;

// Las veces que esta int[] cont = { 0 }; para poder pasar por referecia tiene que ser un array

public abstract class UserInterface {

	private static final String SEPARATOR = "- -- - -- - -- - -- - -- - -- - -- - -- - -- -- -- - -- - -- - -- - -- - -- - -- - -- - -- - --";
	private static final String accepts = "S";
	private static final String notAccepts = "N";

	public static void askUserAtraction(ArrayList<Atraction> offerArray, Scanner input, User user, Purchase compra,
			boolean basedOnPreferred) {

		int[] cont = { 0 };
		Offer off = Atraction.createNewSuggestion(offerArray, cont, basedOnPreferred, compra.getAllAtractions(), user);

		while (off != null) {
			userInputHandler(input, user, compra, off);
			cont[0]++;

			off = Atraction.createNewSuggestion(offerArray, cont, basedOnPreferred, compra.getAllAtractions(), user);
		}
	}

	public static void askUserPromotion(ArrayList<Promotion> offerArray, Scanner input, User user, Purchase compra,
			boolean basedOnPreferred) {
		int[] cont = { 0 };
		Offer off = Promotion.createNewSuggestion(offerArray, cont, basedOnPreferred, compra.getAllAtractions(), user);
		while (off != null) {
			userInputHandler(input, user, compra, off);
			cont[0]++;

			off = Promotion.createNewSuggestion(offerArray, cont, basedOnPreferred, compra.getAllAtractions(), user);
		}
	}

	public static void userInputHandler(Scanner input, User user, Purchase compra, Offer atr) {
		String option;
		do {

			System.out.println(atr.presentation());
			option = input.next();
			option = option.toUpperCase();

			if (option.equals(accepts)) {
				System.out.println("ACEPTADA!");
				user.appoint(atr);
				atr.decreaseSlots();
				compra.add(atr);
			} else if (!option.equals(notAccepts)) {
				System.out.println("La opcion ingresada no es correcta, por favor intente nuevamente.");
			}
			System.out.println();
		} while (!option.equals(notAccepts) && !option.equals(accepts));
	}

	public static void showTicketPurchase(FileParser purchaseFile, Purchase compra) {
		System.out.println(SEPARATOR);
		System.out.println(String.format("\t\t\t*****  \t%25s \t*****", "TURISMO JUEGO DE TRONOS"));
		System.out.println(compra);
		System.out.println(SEPARATOR);
		purchaseFile.appendToFile(compra, true);
	}

	public static void welcome(FileParser purchaseFile) {
		purchaseFile.appendToFile("Compras del dia: " + java.time.LocalDate.now(), false);
		System.out.println("\t\t\t\t¡Bienvenido/a a Juego de Tronos!\n");
		
	}

	public static void eachUser(User user) {
		System.out.println(SEPARATOR);
		System.out.println("Nombre del visitante: " + user.getName() + ".\n");
		
	}

}
