package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import entities.Atraction;
import entities.FileParser;
import entities.Promotion;
import entities.Purchase;
import entities.User;
import interfaces.UserInterface;

public class Main {

	private static final String pathAtraction = "casos de prueba/in/atractions.in";
	private static final String pathPromotions = "casos de prueba/in/promotions.in";
	private static final String pathUsers = "casos de prueba/in/users.in";
	private static final String pathPurchases = "casos de prueba/out/purchases.out";


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
			System.out.println("************************************");
			Purchase compra = new Purchase(user);
			System.out.println(user.getName());
			
			UserInterface.askUserPromotion(promotionArrayList, input, user, compra, true);
		
			UserInterface.askUserAtraction(atractionArray, input, user, compra, true);

			UserInterface.askUserPromotion(promotionArrayList, input, user, compra, false);

			UserInterface.askUserAtraction(atractionArray, input, user, compra, false);

			System.out.println("-----------------------------------");
			System.out.println(compra);
			System.out.println("-----------------------------------");
			purchaseFile.appendToFile(compra, true);
			
		}
		input.close();

	}


}
