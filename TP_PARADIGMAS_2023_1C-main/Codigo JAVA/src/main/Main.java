package main;

import java.util.ArrayList;
import java.util.LinkedList;

import entities.Atraction;
import entities.Promotion;
import entities.User;
import myFiles.MyFiles;

public class Main {

	private static final String pathAtraction = "casos de prueba/in/atractions.in";
	private static final String pathPromotions = "casos de prueba/in/promotions.in";
	private static final String pathUsers = "casos de prueba/in/users.in";
	
	public static void main(String[] args) {
		
		
		MyFiles atractionFile = new MyFiles (pathAtraction);
		MyFiles promotionsFile = new MyFiles (pathPromotions);
		MyFiles userFile = new MyFiles (pathUsers);
		
		ArrayList<Atraction> atractionArray = atractionFile.importArtactionsFromFile();
		LinkedList<Promotion> PromotionArrayList = promotionsFile.importPromotionsFromFile(atractionArray);
		ArrayList<User> usersArrayList = userFile.importUsersFromFile();
		
		for (User user : usersArrayList) {
			
			System.out.println(user.getName());
			 //ArrayList<Promotion> = user.createSuggestionPromotion();
			 //ArrayList<Atraction> = user.createSuggestionAtraction();
		}
		System.out.println("---------------------");
		for (Atraction atr : atractionArray) {
			System.out.println(atr);
		}
		System.out.println("---------------------");
		for (Promotion pro : PromotionArrayList) {
			System.out.println(pro);
		}
		
	}

}