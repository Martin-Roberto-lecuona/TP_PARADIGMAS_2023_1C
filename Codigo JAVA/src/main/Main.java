package main;

import entities.Atraction;
import wrappers.AtractionArrayList;
import wrappers.UsersArrayList;

public class Main {

	public static void main(String[] args) {

		AtractionArrayList aal = new AtractionArrayList();
		aal.importArtactionsFromFile(
				"C:\\Users\\Centorios\\Documents\\GitHub\\TP_PARADIGMAS_2023_1C\\Codigo JAVA\\src\\resources\\atractions.txt");

		
		 for (Atraction atraction : aal.atractionArray) {
		 System.out.println(atraction.toString()); 
		 }
		

		UsersArrayList ual = new UsersArrayList();

		ual.importUsersFromFile(
				"C:\\Users\\Centorios\\Documents\\GitHub\\TP_PARADIGMAS_2023_1C\\Codigo JAVA\\src\\resources\\users.txt");
		
		/*
		 for (User user : ual.usersArrayList) {
		 System.out.println(user.toString()); 
		 }
		 */

	}

}