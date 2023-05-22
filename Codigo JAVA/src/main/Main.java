package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import entities.Atraction;
import entities.Promotion;
import entities.User;
import myFiles.MyFiles;

public class Main {

	private static final String pathAtraction = "casos de prueba/in/atractions.in";
	private static final String pathPromotions = "casos de prueba/in/promotions.in";
	private static final String pathUsers = "casos de prueba/in/users.in";
	private static final String accepts = "S";

	public static void main(String[] args) {

		MyFiles atractionFile = new MyFiles(pathAtraction);
		MyFiles promotionsFile = new MyFiles(pathPromotions);
		MyFiles userFile = new MyFiles(pathUsers);

		ArrayList<Atraction> atractionArray = atractionFile.importArtactionsFromFile();
		LinkedList<Promotion> promotionArrayList = promotionsFile.importPromotionsFromFile(atractionArray);
		ArrayList<User> usersArrayList = userFile.importUsersFromFile();
		// clases tipo wrappers para estos arrays??
		// ListAtraccion(atractionArray)
		// ListPromotion(promotionArrayList)
		Scanner input = new Scanner(System.in);
		for (User user : usersArrayList) {
			System.out.println(user.getName());
			ArrayList<Promotion> sugerenciaPromo = user.createSuggestion(promotionArrayList);
			for (Promotion promo : sugerenciaPromo) {
				// search promo.listaAtracciones
				System.out.println(promo);
				System.out.println("Acepta la Promo 'S' para si 'N' para no ");
				String option = input.next();

				if (option.contains(accepts)) {
					// promo.deletePromotionWithSameAtraction(sugerenciaPromo);
					for (Atraction atr : atractionArray) {
						System.out.println(atr + " | " + atr.getSlots());
					}
					promo.decreaseSlots(atractionArray);
					for (Atraction atr : atractionArray) {
						System.out.println(atr + " | " + atr.getSlots());
					}
					// user.appoint(promo);
				}
			}
			System.out.println("------------------------");
			for (Atraction atr : atractionArray) {
				// System.out.println(atr + " | " + atr.getSlots());
			}
			// ArrayList<Atraction> sugerenciaAtraccion =
			// user.createSuggestion(atractionArray);
			// foreach recorrer cuando se acepta se popea de la lista de sugerencia
			// Append file de compra

		}
		input.close();

	}

}