package main;

public class MainPruebas {
/*
	private static final String pathAtraction = "casos de prueba/in/atractions.in";
	private static final String pathPromotions = "casos de prueba/in/promotions.in";
	private static final String pathUsers = "casos de prueba/in/users.in";
	private static final String pathPurchases = "casos de prueba/out/purchases.out";
	private static final String accepts = "SsyY";

	public static void main(String[] args) {

		MyFiles atractionFile = new MyFiles(pathAtraction);
		MyFiles promotionsFile = new MyFiles(pathPromotions);
		MyFiles userFile = new MyFiles(pathUsers);
		MyFiles purchaseFile = new MyFiles(pathPurchases);

		ArrayList<Atraction> atractionArray = atractionFile.importArtactionsFromFile();
		LinkedList<Promotion> promotionArrayList = promotionsFile.importPromotionsFromFile(atractionArray);
		ArrayList<User> usersArrayList = userFile.importUsersFromFile();

		System.out.println("------------------------");
		for (Atraction atr : atractionArray) {
			System.out.println(atr.getName() + " | " + atr.getSlots());
		}
		System.out.println("------------------------");
		Scanner input = new Scanner(System.in);
		for (User user : usersArrayList) {
			Purchase compra = new Purchase(user);
			System.out.println(user.getName());
			
			ArrayList<Promotion> sugerenciaPromo = user.createSuggestion(promotionArrayList);
			ArrayList<Atraction> sugerenciaAtraction = user.createSuggestion(atractionArray);
			
			for (Promotion promo : sugerenciaPromo) {
				System.out.println("La Promocion que le presentamos es: " + promo);
				System.out.println(promo);
				System.out.println("Acepta la Promo 'S' para si 'N' para no ");
				String option = input.next();

				if (accepts.contains(option)) {
					// promo.deletePromotionWithSameAtraction(sugerenciaPromo);
					deleteAtractionInArrayFromPromotion(sugerenciaAtraction, promo.getAtractionList());
					decreaseSlotsToAllAtractionsInPromo(promo);
					compra.add(promo);
				}
			}

			for (Atraction atr : sugerenciaAtraction) {
	
				System.out.println("La Atraccion que le presentamos es: " + atr);
				System.out.println(atr);
				System.out.println("Acepta la Atraccion 'S' para si 'N' para no ");
				String option = input.next();

				if (accepts.contains(option)) {
					atr.decreaseSlots();
					compra.add(atr);
				}
			}
			purchaseFile.appendToFile(compra, true);
			break;
		}
		System.out.println("------------------------");
		for (Atraction atr : atractionArray) {
			System.out.println(atr.getName() + " | " + atr.getSlots());
		}
		System.out.println("------------------------");
		input.close();
	}

	private static void deleteAtractionInArrayFromPromotion(ArrayList<Atraction> where, ArrayList<Atraction> from) {
		for (Atraction atr : from) {
			where.remove(atr);
		}
		
	}

	private static void decreaseSlotsToAllAtractionsInPromo(Promotion promo) {
		for (Atraction atr : promo.getAtractionList()) {
			atr.decreaseSlots();
		}
	}
*/
}
