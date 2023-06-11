package Test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.Absoluta;
import entities.Atraction;
import entities.AxB;
import entities.Porcentual;
import entities.Promotion;
import entities.User;
import enums.AtractionType;

public class TotalizadorDeItinerarioTest {

	@Test
	public void TestFileOutput() {

		ArrayList<User> usersArrList = new ArrayList<User>();
		User user1 = new User("Robert Baratheon", 250, 12, AtractionType.AVENTURA);
		User user2 = new User("Tyrion Lannister", 250, 12, AtractionType.PAISAJE);
		User user3 = new User("Daenerys Targaryen", 87, 11, AtractionType.AVENTURA);
		User user4 = new User("Jon Snow", 30, 8, AtractionType.AVENTURA);
		User user5 = new User("Arya Stark", 150, 12, AtractionType.AVENTURA);
		User user6 = new User("Varys", 120, 3, AtractionType.DEGUSTACION);
		User user7 = new User("Bran Stark", 25, 18, AtractionType.PAISAJE);

		usersArrList.add(user1);
		usersArrList.add(user2);
		usersArrList.add(user3);
		usersArrList.add(user4);
		usersArrList.add(user5);
		usersArrList.add(user6);
		usersArrList.add(user7);

		Atraction atraction1 = new Atraction("Dragonstone", 12, 4, 22, AtractionType.AVENTURA);
		Atraction atraction2 = new Atraction("Highgarden", 50, 2.5, 18, AtractionType.DEGUSTACION);
		Atraction atraction3 = new Atraction("King's Landing", 75, 3, 7, AtractionType.DEGUSTACION);
		Atraction atraction4 = new Atraction("Lannisport", 25, 1.5, 2, AtractionType.DEGUSTACION);
		Atraction atraction5 = new Atraction("The Iron Islands", 10, 5, 10, AtractionType.AVENTURA);
		Atraction atraction6 = new Atraction("THe Vale of Arryn", 18, 3, 107, AtractionType.PAISAJE);
		Atraction atraction7 = new Atraction("The Wall", 6, 7, 250, AtractionType.AVENTURA);
		Atraction atraction8 = new Atraction("Winterfell", 10.5, 4.5, 97, AtractionType.PAISAJE);

		ArrayList<Atraction> atractions = new ArrayList<Atraction>();
		atractions.add(atraction1);
		atractions.add(atraction2);
		atractions.add(atraction3);
		atractions.add(atraction4);
		atractions.add(atraction5);
		atractions.add(atraction6);
		atractions.add(atraction7);
		atractions.add(atraction8);

		Porcentual promo1 = new Porcentual(new ArrayList<Atraction>(Arrays.asList(atraction1, atraction7)), 0.2);
		
		Absoluta promo2 = new Absoluta(new ArrayList<Atraction>(Arrays.asList(atraction2, atraction3)), 10);
		
		AxB promo3 = new AxB(new ArrayList<Atraction>(Arrays.asList(atraction2, atraction3)),
				new ArrayList<Atraction>(Arrays.asList(atraction4)));
		
		Porcentual promo4 = new Porcentual(new ArrayList<Atraction>(Arrays.asList(atraction6, atraction8)), 0.15);
		
		Absoluta promo5 = new Absoluta(new ArrayList<Atraction>(Arrays.asList(atraction1, atraction3)), 6);
		
		AxB promo6 = new AxB(new ArrayList<Atraction>(Arrays.asList(atraction8, atraction6)),
				new ArrayList<Atraction>(Arrays.asList(atraction5)));

		ArrayList<Promotion> promos = new ArrayList<Promotion>();

		promos.add(promo1);
		promos.add(promo2);
		promos.add(promo3);
		promos.add(promo4);
		promos.add(promo5);
		promos.add(promo6);

	}

}
