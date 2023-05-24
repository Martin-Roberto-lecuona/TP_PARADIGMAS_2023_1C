package myFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
			if (reader.hasNextLine()) {
				reader.nextLine();
			}
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
			if (reader.hasNextLine()) {
				reader.nextLine();
			}
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

	public ArrayList<Promotion> importPromotionsFromFile(ArrayList<Atraction> atractionList) {
		ArrayList<Promotion> promotionArrayList = new ArrayList<Promotion>();
		ArrayList<Atraction> atractionsWithPromotion = new ArrayList<Atraction>();
		Scanner reader = null;
		try {
			File file = new File(this.name);
			reader = new Scanner(file);
			reader.useLocale(Locale.ENGLISH);
			if (reader.hasNextLine()) {
				reader.nextLine();
			}
			while (reader.hasNextLine()) {
				atractionsWithPromotion = new ArrayList<Atraction>();
				String line = reader.nextLine();
				String[] parsedValues = line.split(";");
				String[] atractionsInFIle = parsedValues[1].split(",");
				

				atractionsWithPromotion = findAtractionByName(atractionList, atractionsInFIle);

				if (!atractionsWithPromotion.isEmpty()) {
					if (PromotionType.AXB == PromotionType.valueOf(parsedValues[0])) {
						/*
						 * puede haber mas de una gratis
						 */
						String[] atractionsFreeInFIle = parsedValues[2].split(",");
						ArrayList<Atraction> free = findAtractionByName(atractionList, atractionsFreeInFIle);
						promotionArrayList.add(new AxB(atractionsWithPromotion, free));
					} else if (PromotionType.ABSOLUTA == PromotionType.valueOf(parsedValues[0])) {
						promotionArrayList.add(new Absoluta(atractionsWithPromotion, Double.valueOf(parsedValues[2])));
					} else {
						promotionArrayList
								.add(new Porcentual(atractionsWithPromotion, Double.valueOf(parsedValues[2])));
					}
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

	public void appendToFile(Object data, boolean append) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(this.name, append);
			writer.write(data + EOL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
