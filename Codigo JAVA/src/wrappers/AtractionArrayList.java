package wrappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Atraction;
import enums.AtractionType;

public class AtractionArrayList {

	public ArrayList<Atraction> atractionArray = new ArrayList<Atraction>();

	public void importArtactionsFromFile(String path) {
		try {
			File file = new File(path);
			Scanner reader = new Scanner(file);
			
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

			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}
