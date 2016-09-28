package myProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import Test.VersionTest;

import java.util.logging.Level;

import dataGenerators.DataGenerator_AT;
import dataGenerators.KH_AddressGenerator;
import dataGenerators.KH_AddressGenerator.ExcelElement;
import dataGenerators.NameGenerator;
import dataGenerators.PLZ_StadtGenerator;

public class DataBase_AT_Name {

	public static final int locationNum = 281; // max 281 items in Excel
	public static final int patientNum = 2000;

	
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		PLZ_StadtGenerator generator=new PLZ_StadtGenerator();
		KH_AddressGenerator addressGenerator = new KH_AddressGenerator();
		NameGenerator nameGenerator = new NameGenerator();
		
		try {
			
			//--- Connection-Configuration of DB

			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			if (con!=null) System.out.println("Server connected \n");
			st = con.createStatement();
			st.executeUpdate(" SET plv8.start_proc = 'plv8_init' ");
	
			//--- Location Res Creation

			System.out.println("processing Location data from Excel... \n ");

			int i;
			for (i = 1; i <= locationNum; i++) {  // 281 items in Excel
				
				String SqlLocation = " SELECT fhir_create_resource(' {"
						+ " \"allowId\":true,"
						+ " \"resource\":{\"resourceType\":\"Location\", "
						+ "\"id\":\"" + i + "\", "
						+ " \"status\":\"active\", "
						+ " \"name\":\" " + addressGenerator.getElement(ExcelElement.HOSPITAL_NAME, i) + " \", "
						+ " \"address\":[{\"line\":[\""+ addressGenerator.getElement(ExcelElement.LINE, i) + "\"], "
						+ " \"city\":\""+ addressGenerator.getElement(ExcelElement.CITY, i) + "\", \"postalCode\":\"" + addressGenerator.getElement(ExcelElement.POSTCODE, i)+ "\", \"country\":\""+ addressGenerator.getElement(ExcelElement.STATE, i) + "\"  }] "
						+ "}} ')";
			
				rs = st.executeQuery( SqlLocation);
					
			} // for
			
			System.out.println(i-1 + " Location-Resources was uploaded! \n");

			//--- Patient-Encounter Res Creation
			
			System.out.println("creating " + patientNum + " resources... \n ");
			
			int j;
			String gender="";
			
			for (j = 1; j <= patientNum; j++) { 

				String genderVorName = nameGenerator.randomiseGenderVorname();
				String vorNameCut = genderVorName.substring(1);
				char genderCut = genderVorName.charAt(0);
				gender = (genderCut == 'M') ? "male" : "female";
				
				String SqlPatient = " SELECT fhir_create_resource(' {"
						+ "\"allowId\":true,"
						+ "\"resource\":{\"resourceType\":\"Patient\","
						+ "\"id\":\"" + j + "\", "
						+ "\"gender\":\"" + gender + "\", "
						+ " \"name\":[{\"family\":\""+ nameGenerator.randomiseNachname() + "\", \"given\":\""+ vorNameCut + "\"}], "
						+ " \"address\":[{\"line\":[\""+ DataGenerator_AT.generateStreet() + DataGenerator_AT.generateHouseNumber() + "\"], "
						+ " \"city\":\""+ generator.randomiseCity() + "\", \"postalCode\":\""+ generator.randomisePostcode() + "\"}] "
						+ "}} ')";	
				
 				String SqlEncounter = " SELECT fhir_create_resource(' {"
 						+ " \"allowId\":true,"
 						+ " \"resource\":{\"resourceType\":\"Encounter\","
 						+ "\"id\":\"" + j + "\", "
 						+ " \"status\":\"onleave\", "
 						+ " \"patient\":{\"reference\":\"Patient/" + j + "\"}, "
 						+ " \"location\":[{\"location\":{\"reference\":\"Location/" + DataGenerator_AT.getRandomId(locationNum) + " \"},\"status\":\"active\"}] "
 						+ "}} ')";
					
				rs = st.executeQuery( SqlPatient );
				rs = st.executeQuery( SqlEncounter);
					
			} // for

			System.out.println(j-1 + " Patient-Encoutner pair was created! \n ");
            
			} catch (ClassNotFoundException e) {
			   e.printStackTrace();
			} catch (SQLException ex) {
			   Logger lgr = Logger.getLogger(VersionTest.class.getName());
			   lgr.log(Level.SEVERE, ex.getMessage(), ex);
			   ex.printStackTrace();
			   
			} finally {
			   try {
			       if (rs != null) {
			           rs.close();
						System.out.println("ResultSet closed");
			       }
			       if (st != null) {
			           st.close();
			           System.out.println("Statement closed");
			       }
			       if (con != null) {
			           con.close();
			           System.out.println("Connection closed");
			       }
			   } catch (SQLException ex) {
			       Logger lgr = Logger.getLogger(VersionTest.class.getName());
			       lgr.log(Level.WARNING, ex.getMessage(), ex);
			   }
			}
	}// main
}
