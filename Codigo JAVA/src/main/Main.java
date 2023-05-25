package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import entities.Atraction;
import entities.FileParser;
import entities.Promotion;
import entities.Purchase;
import entities.User;

public class Main {

	private static final String pathAtraction = "casos de prueba/in/atractions.in";
	private static final String pathPromotions = "casos de prueba/in/promotions.in";
	private static final String pathUsers = "casos de prueba/in/users.in";
	private static final String pathPurchases = "casos de prueba/out/purchases.out";
	private static final String accepts = "SsyY";

	public static void main(String[] args) {

		FileParser atractionFile = new FileParser(pathAtraction);
		FileParser promotionsFile = new FileParser(pathPromotions);
		FileParser userFile = new FileParser(pathUsers);
		FileParser purchaseFile = new FileParser(pathPurchases);

		ArrayList<Atraction> atractionArray = atractionFile.importArtactionsFromFile();
		Collections.sort(atractionArray);
		ArrayList<Promotion> promotionArrayList = promotionsFile.importPromotionsFromFile(atractionArray);
		Collections.sort(promotionArrayList);
		ArrayList<User> usersArrayList = userFile.importUsersFromFile();

		purchaseFile.appendToFile("Compras del dia: " + java.time.LocalDate.now(), false);

		Scanner input = new Scanner(System.in);
		for (User user : usersArrayList) {
			Purchase compra = new Purchase(user);
			System.out.println(user.getName());

			askUserForPromotions(promotionArrayList, input, user, compra, true);
			askUserForPromotions(promotionArrayList, input, user, compra, false);
			
			askUserForAtractions(atractionArray, input, user, compra, true);
			askUserForAtractions(atractionArray, input, user, compra, false);

			System.out.println("-----------------------------------");
			System.out.println(compra);
			purchaseFile.appendToFile(compra, true);
		}
		input.close();
	}

	private static void askUserForAtractions(ArrayList<Atraction> atractionArray, Scanner input, User user,
			Purchase compra, boolean basedOnPreference) {
		int[] cont = {0}; // para poder pasar por referecia tiene que ser un array 
		Atraction atr = user.createNewAtractionSuggestion(atractionArray, cont,basedOnPreference);
		while (atr != null) {
			System.out.println("La Atraccion que le presentamos es: " + atr);
			System.out.println("Acepta la Atraccion 'S' para si 'N' para no ");
			String option = input.next();

			if (accepts.contains(option)) {
				user.appoint(atr);
				atr.decreaseSlots();
				compra.add(atr);
			}

			atr = user.createNewAtractionSuggestion(atractionArray, cont, basedOnPreference);
		}
	}

	private static void askUserForPromotions(ArrayList<Promotion> promotionArrayList, Scanner input, User user,
			Purchase compra, boolean basedOnPreference) {
		int[] cont = {0};// para poder pasar por referecia tiene que ser un array 
		Promotion promo = user.createNewPromotionSuggestion(promotionArrayList, cont,basedOnPreference);
		System.out.println("......................");
		while (promo != null) {
			System.out.println("cont Antes: " + cont[0]);
			System.out.println("La Promocion que le presentamos es: " + promo);
			System.out.println("Acepta la Promo 'S' para si 'N' para no ");
			String option = input.next();

			if (accepts.contains(option)) {
				user.appoint(promo);
				decreaseSlotsToAllAtractionsInPromo(promo);
				compra.add(promo);
			}
			else 
				cont[0]++;
			System.out.println("cont Despues: " + cont[0]);
			promo = user.createNewPromotionSuggestion(promotionArrayList, cont ,basedOnPreference);
			System.out.println("cont Despues de funcion: " + cont[0]);
			
		}
	}

	private static void decreaseSlotsToAllAtractionsInPromo(Promotion promo) {
		for (Atraction atr : promo.getAtractionList()) {
			atr.decreaseSlots();
		}
	}

}
