package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import enums.AtractionType;

public abstract class Promotions {
	protected static LinkedList<Atraction> atractionArray = new LinkedList<>();

	public void closeAttraction(Atraction a) {
		if (a.getTakenSlots() >= a.getMaxSlots()) {
			atractionArray.remove(a);
		}
	}

	public void importArtactionsFromFile(String path) {
		try {

			File file = new File(path);
			Scanner reader = new Scanner(file);
			// each 5 lines changes the object
			while (reader.hasNextLine()) {
				String atractionName = reader.nextLine();
				double cost = reader.nextDouble();
				double time = reader.nextDouble();
				int maxSlots = reader.nextInt();
				String atractionType = reader.nextLine().toUpperCase();
				AtractionType realAtractionType = AtractionType.valueOf(atractionType);

				atractionArray.add(new Atraction(atractionName, cost, time, maxSlots, realAtractionType));

			}

			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}