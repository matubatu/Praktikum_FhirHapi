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

public class PatEncounter_Random_NoObject {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		FhirContext ctx = FhirContext.forDstu2();
		Patient patient = new Patient();
		
//Patient ERZEUGEN
		patient.setId("Patient/smithke3");
		HumanNameDt name = patient.addName();
		
		name.addFamily(DataGenerator.generateNachName());
		
		String SqlPatient = " SELECT fhir_create_resource(' {"
				+ "\"allowId\":true,"
				+ "\"resource\":{\"resourceType\":\"Patient\","
				+ "\"id\":\"smith2\","
				+ " \"name\":[{\"family\":\""+ DataGenerator.generateNachName() + "\", \"given\":\""+ DataGenerator.generateVorName() + "\"}], "
				+ " \"address\":[{\"line\":[\""+ DataGenerator.generateStreet() + DataGenerator.generateHouseNumber() + "\"], "
				+ " \"city\":\""+ DataGenerator.generateStadt() + "\", \"postalCode\":\""+ DataGenerator.generatePlz() + "\"}] "
				+ "}} ')";	
		
//      Json Encounter:  {"allowId":true,"resource":{"resourceType":"Encounter","status":"onleave","patient":{"reference":"Patient/smith"}}}
//		rs = st.executeQuery( "SELECT fhir_create_resource(\' {\"allowId\":true,\"resource\":{\"resourceType\":\"Encounter\",\"status\":\"onleave\",\"patient\":{\"reference\":\"Patient/smith\"}}} \') ");
		
		String SqlEncounter = " SELECT fhir_create_resource(' {"
				+ "\"allowId\":true,"
				+ "\"resource\":{\"resourceType\":\"Encounter\","
				+ "\"status\":\"onleave\","
				+ "\"patient\":{\"reference\":\"Patient/smith2\"} "
				+ "}} ')";
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			
			if (con!=null) System.out.println("Connected");
			
			st = con.createStatement();
      
            st.executeUpdate(" SET plv8.start_proc = 'plv8_init' ");
            
            rs = st.executeQuery( SqlPatient );
            rs = st.executeQuery( SqlEncounter);
            System.out.println("Finished!!!! ");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
        }
		
	}// main

}
