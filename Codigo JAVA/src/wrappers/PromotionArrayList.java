package wrappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import entities.Porcentual;
import entities.Promotion;
import enums.PromotionType;

public class PromotionArrayList {

	LinkedList<Promotion> PromotionArrayList = new LinkedList<Promotion>();

	public void importPromotionsFromFile(String path, AtractionArrayList atractionList) {
		try {
			File file = new File(path);
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()) {

				String line = reader.nextLine();
				String[] parsedValues = line.split(";");
				List<String> item = Arrays.asList(parsedValues[1].split(","));

				double totalCost = 0;
				double totalTime = 0;

				for (String name : item) {


				}

				if (PromotionType.ABSOLUTA == PromotionType.valueOf(parsedValues[0])) {

				} else if (PromotionType.PORCENTUAL == PromotionType.valueOf(parsedValues[0])) {

				} else {

				}

			}

			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

}