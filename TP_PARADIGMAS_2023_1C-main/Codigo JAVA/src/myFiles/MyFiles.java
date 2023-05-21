package myFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

import entities.Absoluta;
import entities.Atraction;
import entities.AxB;
import entities.Porcentual;
import entities.Promotion;
import entities.User;
import enums.AtractionType;
import enums.PromotionType;

public class MyFiles {

	private static final String EOL = "\n";
	private String name;

	public MyFiles(String name) {
		this.name = name;
	}

	public ArrayList<Atraction> importArtactionsFromFile() {
		ArrayList<Atraction> atractionArray = new ArrayList<Atraction>();
		Scanner reader = null;
		try {
			File file = new File(this.name);
			reader = new Scanner(file);
			reader.useLocale(Locale.ENGLISH);

			while (reader.hasNextLine()) {

				String line = reader.nextLine();
				String[] parsedValues = line.split(";");

				String name = parsedValues[0];
				double cost = Double.valueOf(parsedValues[1]);
				double estimatedTime = Double.valueOf(parsedValues[2]);
				int maxSlots = Integer.valueOf(parsedValues[3]);
				AtractionType realAtractionType = AtractionType.valueOf(parsedValues[4].toUpperCase());

				atractionArray.add(new Atraction(name, cost, estimatedTime, maxSlots, realAtractionType));
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return atractionArray;
	}

	public ArrayList<User> importUsersFromFile() {
		ArrayList<User> usersArrayList = new ArrayList<User>();
		Scanner reader = null;
		try {
			File file = new File(this.name);
			reader = new Scanner(file);
			reader.useLocale(Locale.ENGLISH);

			while (reader.hasNextLine()) {

				String line = reader.nextLine();
				String[] parsedValues = line.split(";");

				String name = parsedValues[0];
				double budget = Double.valueOf(parsedValues[1]);
				double freeTime = Double.valueOf(parsedValues[2]);
				AtractionType preferredAtractionType = AtractionType.valueOf(parsedValues[3].toUpperCase());

				usersArrayList.add(new User(name, budget, freeTime, preferredAtractionType));
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return usersArrayList;

	}

	public LinkedList<Promotion> importPromotionsFromFile(ArrayList<Atraction> atractionList) {
		LinkedList<Promotion> promotionArrayList = new LinkedList<Promotion>();
		ArrayList<Atraction> atractionsWithPromotion = new ArrayList<Atraction>();
		Scanner reader = null;
		try {
			File file = new File(this.name);
			reader = new Scanner(file);
			reader.useLocale(Locale.ENGLISH);

			while (reader.hasNextLine()) {

				String line = reader.nextLine();
				String[] parsedValues = line.split(";");
				String[] atractionsInFIle = parsedValues[1].split(",");

				atractionsWithPromotion.addAll(findAtractionByName(atractionList, atractionsInFIle));
				if (PromotionType.AXB == PromotionType.valueOf(parsedValues[0])) {
					/*
					 * Como funciona esta promo mas de una gratis puede haber? OP1 te oferto n
					 * atracciones y una de ellas es gratis OP2 si compraste el el pasado, en otro
					 * paquete entoces ahora es valida la promocion y se te oferta
					 */
					Atraction free = findAtractionByName(atractionList, parsedValues[2]);
					promotionArrayList.add(new AxB(atractionsWithPromotion, free));
				} else if (PromotionType.ABSOLUTA == PromotionType.valueOf(parsedValues[0])) {
					promotionArrayList.add(new Absoluta(atractionsWithPromotion, Double.valueOf(parsedValues[2])));
				} else {
					promotionArrayList.add(new Porcentual(atractionsWithPromotion, Double.valueOf(parsedValues[2])));
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return promotionArrayList;

	}

	private ArrayList<Atraction> findAtractionByName(ArrayList<Atraction> origin, String[] atractions) {
		ArrayList<Atraction> res = new ArrayList<Atraction>();
		for (String atr : atractions) {
			boolean found = false;
			for (int i = 0; !found && i < origin.size(); i++) {
				found = (atr.equals(origin.get(i).getName()));
				if (found)
					res.add(origin.get(i));
			}
		}
		return res;
	}

	private Atraction findAtractionByName(ArrayList<Atraction> origin, String atractions) {
		Atraction res = null;
		boolean found = false;
		for (int i = 0; !found && i < origin.size(); i++) {
			found = (atractions.equals(origin.get(i).getName()));
			if (found)
				res = new Atraction(origin.get(i));
		}
		return res;
	}
}
