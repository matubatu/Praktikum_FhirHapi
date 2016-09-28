
package dataGenerators;

import java.util.Random;

public class DataGenerator_AT {

	private static String[] Nachnamen = { "Mueller", "Schmidt", "Schneider", "Fischer", "Weber",
		"Meyer", "Wagner", "Becker", "Schulz", "Hoffmann", "Schaefer", "Bauer", "Kovats", "Koch",
		"Richter", "Klein", "Wolf", "Schraeder", "Neumann", "Zimmermann", "Hartmann", "Krueger", "Werner",
		"Lange", "Lehmann", "Schmid", "Schulze", "Maier", "Köhler", "Herrmann", "König", "Walter", "Mayer", 
		"Huber", "Kaiser", "Fuchs", "Peters", "Lang", "Scholz", "Möller", "Weiß", "Jung", "Hahn", "Schubert",
		"Vogel", "Friedrich", "Keller", "Günther", "Frank", "Berger", "Winkler", "Roth"};

   private static String[] Vornamen = { "Anna", "Lukas", "Hannah", "Tobias", "Lena", "Maximilian", "Sarah", "David", 
		   "Sophie", "Jakob", "Emma", "Felix", "Julia", "Elias", "Marie", "Jonas", "Leonie", "Paul", "Laura", 
		   "Alexander", "Lea", "Sebastian", "Mia", "Julian", "Johanna", "Simon", "Katharina", "Fabian", "Lara", "Florian", 
		   "Amelie", "Moritz", "Magdalena", "Philipp", "Lisa", "Matthias", "Luisa", "Nico", "Clara", "Michael", "Viktoria", 
		   "Samuel", "Nina", "Benjamin", "Theresa", "Daniel", "Elena", "Niklas", "Emily", "Dominik", "Isabella", "Johannes",
		   "Alina", "Lorenz", "Marlene", "Marcel", "Lina", "Leo", "Helena", "Gabriel", "Selina", "Valentin", "Lilly", "Matteo",
		   "Annika", "Jan", "Pia", "Konstantin", "Vanessa", "Manuel", "Nora", "Luis", "Elisa", "Emil", "Eva", "Marco", "Maja",
		   "Anja", "Max", "Elisabeth", "Oliver", "Isabel", "Christian", "Maria", "Adrian", "Livia", "Anton", "Valeria"};
   
   private static String[] Gender = { "male", "female" };

	private static String[] City = { "Linz", "Wien", "Graz", "Salzburg", "Wels",
		"Bregenz", "St. Poelten", "Steyr", "Krems an der Donau", "Eisenstadt",
		"Villach", "Schwaz", "Feldkirch", "Judenburg", "Traun", "Bad Ischl" };

	private static String[] Street = { "Amtsstrasse ", "Anichweg", "Arnimgasse ", "Silbergasse", "Audorfgasse",
		"Arnoldgasse", "Bahndammweg", "Baldassgasse", "Berglergasse", "Berlagasse", "Dahliengasse",
		"Cooperweg", "Dahliengasse", "Coulombgasse", "Deingasse", "Demmergasse ", " Dattlergasse",
		"Corygasse", "Carminweg", "Castlegasse", "Carrogasse", "Bonitzgasse ", "Bussardgasse" };



   private static Random rand = new Random();

// Nachnamen Generator
	public static String generateNachName() {
		return Nachnamen[rand.nextInt(Nachnamen.length)];
	}
// Vornamen Generator
	public static String generateVorName() {
		return Vornamen[rand.nextInt(Vornamen.length)];

	}
// PLZ Generator
	public static String generatePlz() {
		return "" + rand.nextInt(10000);
   }
	

// Stadt Generator
	public static String generateStadt() {
		return City[rand.nextInt(City.length)];
   }
// Street Generator
	public static String generateStreet() {
		return Street[rand.nextInt(Street.length)];
   }
// HouseNumber Generator
	public static String generateHouseNumber() {
		return " " + rand.nextInt(10) + 1;
   }

// Location ID generator
public static String getRandomId(int locNum) {
	int x = rand.nextInt(locNum) + 1;
	return "" + x;
	
	
}

}
