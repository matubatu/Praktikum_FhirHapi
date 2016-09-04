
package myProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import dataGenerators.DataGenerator;

public class PatientErzeugen {

	public static void main(String[] args) {
		
		FhirContext ctx = FhirContext.forDstu2();
		Patient patient = new Patient();
		
		for (int i = 1; i < 3; i++) {
			
			patient.setId("Patient/" + i); String pId = "Patient/" + i;
			
			HumanNameDt name = patient.addName();
			name.addFamily(DataGenerator.generateNachName());
			name.addGiven(DataGenerator.generateVorName());
			patient.setGender(AdministrativeGenderEnum.MALE);
			
			AddressDt address = patient.addAddress();
			address.addLine(DataGenerator.generateStreet() + " " +  DataGenerator.generateHouseNumber());
			address.setCity(DataGenerator.generateStadt());
			address.setPostalCode( DataGenerator.generatePlz() );
			
			String patientPrint = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
			System.out.println(patientPrint);
			
			Connection con = null;
			Statement st = null;
			try {
				Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
				
				if (con!=null)
					System.out.println("Connected");
				 st = con.createStatement();
				
//				String sql = "UPDATE patient_orig SET familyname ='xxx' where familyname IS NULL";
				
//				String sql1 = "SELECT patient WHERE id IS NULL";
				String sql = "INSERT INTO patient (id, familyname, givenname, gender, address) VALUES "
						+ "('Pat/', 'Schmieder','John', 'Male', 'Pressgasse 15')";
				
				st.execute(sql);
//				st.execute(sql2);
				
			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		} // for
		
	}

}
