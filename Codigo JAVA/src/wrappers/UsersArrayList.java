package wrappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.User;
import enums.AtractionType;

public class UsersArrayList {

	public ArrayList<User> usersArrayList = new ArrayList<User>();

	public void importUsersFromFile(String path) {
		try {
			File file = new File(path);
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()) {

				String line = reader.nextLine();
				String[] parsedValues = line.split(";");

				String name = parsedValues[0];
				double budget = Double.valueOf(parsedValues[1]);
				double freeTime = Double.valueOf(parsedValues[2]);
				AtractionType preferredAtractionType = AtractionType.valueOf(parsedValues[3].toUpperCase());

				usersArrayList.add(new User(name, budget, freeTime, preferredAtractionType));

			}

			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}
