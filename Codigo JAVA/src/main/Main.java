package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import entities.Atraction;
import entities.Promotion;
import entities.Purchase;
import entities.User;
import myFiles.MyFiles;

public class Main {

	private static final String pathAtraction = "casos de prueba/in/atractions.in";
	private static final String pathPromotions = "casos de prueba/in/promotions.in";
	private static final String pathUsers = "casos de prueba/in/users.in";
	private static final String accepts = "SsyY";

	public static void main(String[] args) {

		MyFiles atractionFile = new MyFiles(pathAtraction);
		MyFiles promotionsFile = new MyFiles(pathPromotions);
		MyFiles userFile = new MyFiles(pathUsers);

		ArrayList<Atraction> atractionArray = atractionFile.importArtactionsFromFile();
		LinkedList<Promotion> promotionArrayList = promotionsFile.importPromotionsFromFile(atractionArray);
		ArrayList<User> usersArrayList = userFile.importUsersFromFile();

		System.out.println("------------------------");
		for (Atraction atr : atractionArray) {
			System.out.println(atr.getName() + " | " + atr.getSlots());
		}
		System.out.println("------------------------");
		Scanner input = new Scanner(System.in);
		for (User user : usersArrayList) {
			Purchase compra = new Purchase(user);
			System.out.println(user.getName());
			ArrayList<Promotion> sugerenciaPromo = user.createSuggestion(promotionArrayList);
			for (Promotion promo : sugerenciaPromo) {
				System.out.println("La Promocion que le presentamos es: " + promo);
				System.out.println(promo);
				System.out.println("Acepta la Promo 'S' para si 'N' para no ");
				String option = input.next();

				if (accepts.contains(option)) {
					// promo.deletePromotionWithSameAtraction(sugerenciaPromo);
					decreaseSlotsToAllAtractionsInPromo(promo);
					compra.add(promo);
				}
			}
			/*
			ArrayList<Atraction> sugerenciaAtraction = user.createSuggestion(atractionArray);
			for (Atraction atr : sugerenciaAtraction) {
				System.out.println("La Atraccion que le presentamos es: " + atr);
				System.out.println(atr);
				System.out.println("Acepta la Atraccion 'S' para si 'N' para no ");
				String option = input.next();

				if (accepts.contains(option)) {
					// promo.deletePromotionWithSameAtraction(sugerenciaPromo);
					atr.decreaseSlots();
					// compra.add(dc);
				}
			}
			*/
			

			break;
		}
		System.out.println("------------------------");
		for (Atraction atr : atractionArray) {
			System.out.println(atr.getName() + " | " + atr.getSlots());
		}
		System.out.println("------------------------");
		input.close();
	}
	

	private static void decreaseSlotsToAllAtractionsInPromo(Promotion promo) {
		for (Atraction atr : promo.getAtractionList()) {
			atr.decreaseSlots();
		}
	}

}
