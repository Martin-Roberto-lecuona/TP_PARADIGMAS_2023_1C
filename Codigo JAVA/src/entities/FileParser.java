package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import enums.AtractionType;
import enums.PromotionType;

public class FileParser {

	private static final String EOL = "\n";
	private String name;

	public FileParser(String name) {
		this.name = name;
	}

	public ArrayList<Atraction> importArtactionsFromFile() {
		ArrayList<Atraction> atractionArray = new ArrayList<Atraction>();
		Scanner reader = null;
		try {
			File file = new File(this.name);
			reader = new Scanner(file);
			reader.useLocale(Locale.ENGLISH);
			skipHeader(reader);
			while (reader.hasNextLine()) {

				atractionArray.add(atractionParser(reader));
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return atractionArray;
	}

	private Atraction atractionParser(Scanner reader) {
		String line = reader.nextLine();
		String[] parsedValues = line.split(";");

		String name = parsedValues[0];
		double cost = Double.valueOf(parsedValues[1]);
		double estimatedTime = Double.valueOf(parsedValues[2]);
		int maxSlots = Integer.valueOf(parsedValues[3]);
		AtractionType realAtractionType = AtractionType.valueOf(parsedValues[4].toUpperCase());

		return new Atraction(name, cost, estimatedTime, maxSlots, realAtractionType);
	}

	public ArrayList<User> importUsersFromFile() {
		ArrayList<User> usersArrayList = new ArrayList<User>();
		Scanner reader = null;
		try {
			File file = new File(this.name);
			reader = new Scanner(file);
			reader.useLocale(Locale.ENGLISH);
			skipHeader(reader);
			while (reader.hasNextLine()) {
				usersArrayList.add(userParser(reader));
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return usersArrayList;
	}

	private User userParser(Scanner reader) {
		String line = reader.nextLine();
		String[] parsedValues = line.split(";");

		String name = parsedValues[0];
		double budget = Double.valueOf(parsedValues[1]);
		double freeTime = Double.valueOf(parsedValues[2]);
		AtractionType preferredAtractionType = AtractionType.valueOf(parsedValues[3].toUpperCase());

		return new User(name, budget, freeTime, preferredAtractionType);
	}

	public ArrayList<Promotion> importPromotionsFromFile(ArrayList<Atraction> atractionList) {
		ArrayList<Promotion> promotionArrayList = new ArrayList<Promotion>();
		Scanner reader = null;
		try {
			File file = new File(this.name);
			reader = new Scanner(file);
			reader.useLocale(Locale.ENGLISH);
			while (reader.hasNextLine()) {
				promotionArrayList.add(promotionParser(atractionList, reader));
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return promotionArrayList;

	}

	private Promotion promotionParser(ArrayList<Atraction> atractionList, Scanner reader) {
		ArrayList<Atraction> atractionsWithPromotion;
		atractionsWithPromotion = new ArrayList<Atraction>();
		String line = reader.nextLine();
		String[] parsedValues = line.split(";");
		String[] atractionsInFIle = parsedValues[1].split(",");

		atractionsWithPromotion = findAtractionByName(atractionList, atractionsInFIle);

		if (atractionsWithPromotion.isEmpty()) {
			return null;
		}
		return createNewPromotionBasedOnType(atractionList, atractionsWithPromotion, parsedValues);
	}

	private Promotion createNewPromotionBasedOnType(ArrayList<Atraction> atractionList,
			ArrayList<Atraction> atractionsWithPromotion, String[] parsedValues) {
		PromotionType promotionType = PromotionType.valueOf(parsedValues[0]);
		if (PromotionType.AXB == promotionType) {
			String[] atractionsFreeInFIle = parsedValues[2].split(",");
			ArrayList<Atraction> free = findAtractionByName(atractionList, atractionsFreeInFIle);
			return new AxB(atractionsWithPromotion, free);
		}
		Double discount = Double.valueOf(parsedValues[2]);
		if (PromotionType.ABSOLUTA == promotionType) {
			return new Absoluta(atractionsWithPromotion, discount);
		}
		return new Porcentual(atractionsWithPromotion, discount);
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

	public void writeObjectToFile(Object data, boolean append) {
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

	private void skipHeader(Scanner reader) {
		if (reader.hasNextLine()) {
			reader.nextLine();
		}
	}
}
