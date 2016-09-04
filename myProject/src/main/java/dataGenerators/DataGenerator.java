
package dataGenerators;

import java.util.Random;

public class DataGenerator {

	private static String[] Nachnamen = { "Mueller", "Schmidt", "Schneider", "Fischer", "Weber",
		"Meyer", "Wagner", "Becker", "Schulz", "Hoffmann", "Schaefer", "Bauer", "Kovats", "Koch",
		"Richter", "Klein", "Wolf", "Schraeder", "Neumann", "Zimmermann", "Hartmann", "Krueger", "Werner",
		"Lange", "Lehmann" };

   private static String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
      "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
      "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
      "Mar", "Luk" };
   private static String[] Middle = { "air", "ir", "mi", "sor", "mee", "clo",
      "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
      "marac", "zoir", "slamar", "salmar", "urak" };
   private static String[] End = { "d", "ed", "ark", "arc", "es", "er", "der",
      "tron", "med", "ure", "zur", "cred", "mur" };

	private static String[] City = { "Linz", "Wien", "Graz", "Salzburg", "Wels",
		"Bregenz", "St. Poelten", "Steyr", "Krems an der Donau", "Eisenstadt",
		"Villach", "Schwaz", "Feldkirch", "Judenburg", "Traun", "Bad Ischl" };

   private static Random rand = new Random();

// Nachnamen Generator
	public static String generateNachName() {
		return Nachnamen[rand.nextInt(Nachnamen.length)];
	}

// Vornamen Generator
	public static String generateVorName() {

	return Beginning[rand.nextInt(Beginning.length)] + 
			Middle[rand.nextInt(Middle.length)]+
			End[rand.nextInt(End.length)];
	}
	
// PLZ Generator
	public static String generatePlz() {
		return "" + rand.nextInt(10000);
   }
  // Stadt Generator
	public static String generateStadt() {
		return City[rand.nextInt(City.length)];
   }

}
