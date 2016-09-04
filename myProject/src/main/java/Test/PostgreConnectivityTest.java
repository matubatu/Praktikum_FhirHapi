package Test;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreConnectivityTest {

	public static void main(String[] args) {

		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			
			if (con!=null)
				System.out.println("Connected");
			Statement st = con.createStatement();
			
//			String sql = "UPDATE patient_orig SET familyname ='xxx' where familyname IS NULL";
			
			
			String sql = "INSERT INTO patient (id, familyname, givenname, gender, address) VALUES "
					+ "('p_2', 'Schmieder','John', 'Male', 'Pressgasse 15')";
			
			
//			String sql = "INSERT INTO patient (id, familyname, givenname, gender, address) VALUES "
//					+ "('Patien/12', 'Schmidt', 'John', 'Male', 'Pressgasse 15')";
			
//			String sql = "DELETE FROM patient";
//			String sql = "UPDATE patient SET nameFamily='Schneider' WHERE nameFamily ='' ";
//			String sql = "update patient set nameFamily='Schneider' nameGiven='John'";
//			
			st.execute(sql);
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		
	}

}
