package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import entities.Atraction;
import entities.Promotion;
import entities.User;
import enums.AtractionType;
import myFiles.MyFiles;

public class MainPruebas {

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

		ArrayList<Promotion> sug = usersArrayList.get(0).createSuggestion(promotionArrayList);
		System.out.println("ANTES: ");
		for (Atraction atr : atractionArray) {
			System.out.println(atr.getName() + " " + atr.getSlots());
		}
		System.out.println("---------------------");
		System.out.println("promociones para el usuario " + usersArrayList.get(0).getName());
		for (Promotion promo : sug) {
			for (Atraction atr : promo.getAtractionList()) {
				atr.decreaseSlots();
			}
			System.out.println(promo);
		}

		System.out.println("DESPUES: ");
		for (Atraction atr : atractionArray) {
			System.out.println(atr.getName() + " " + atr.getSlots());
		}

	}

}
