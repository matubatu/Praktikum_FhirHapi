package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import dataGenerators.DataGenerator;

public class PatEncounter_Random_Object {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		FhirContext ctx = FhirContext.forDstu2();
		Patient patient = new Patient();
		
//--- Patient ERZEUGEN
		patient.setId("Patient/smithke6");
		HumanNameDt name = patient.addName();
		
		name.addFamily(DataGenerator.generateNachName());
		name.addGiven(DataGenerator.generateVorName());
		patient.setGender(AdministrativeGenderEnum.MALE);
		
		AddressDt address = patient.addAddress();
		address.addLine(DataGenerator.generateStreet() + "" +  DataGenerator.generateHouseNumber());
		address.setCity(DataGenerator.generateStadt());
		address.setPostalCode( DataGenerator.generatePlz() );
		
		String patientJson = ctx.newJsonParser().encodeResourceToString(patient);
		System.out.println("Pure Json: " + patientJson + "\n");
		
//--- Concatenation		
		String SqlStatem = " SELECT fhir_create_resource(' {\"allowId\": true, \"resource\":" + patientJson + " } ')";	
		System.out.println("Concatenate with JSON:" + SqlStatem+ "\n");
		
//--- Escape einfuegen	
		String SqlPrint = SqlStatem.replaceAll("(\")", "\\\\$1");
		System.out.println("Ziel: " + SqlPrint + "\n");
        
//		String x = "SELECT fhir_create_resource(\' {\"allowId\":true,\"resource\":{\"resourceType\":\"Patient\",\"id\":\"smithsuj\",\"name\":[{\"given\":\"Bruno\"}]}} \')";
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			
			if (con!=null) System.out.println("Connected");
			
			st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
            st.executeUpdate(" SET plv8.start_proc = 'plv8_init' ");
            
            rs = st.executeQuery( SqlPrint );
            System.out.println("Finished!!!! ");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
        }
		
	}// main

}
