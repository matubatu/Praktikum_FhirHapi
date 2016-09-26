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
import dataGenerators.PLZ_StadtGenerator;

public class DataBase_AT {

	public static final String pfad = "c:\\Users\\matubatu\\Desktop\\Map.xls";
	
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		PLZ_StadtGenerator generator=new PLZ_StadtGenerator();
		KH_AddressGenerator addressGenerator = new KH_AddressGenerator();
				
		try {
			
			//--- Connection-Configuration of DB
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			if (con!=null) System.out.println("Server: Connected");
			st = con.createStatement();
			st.executeUpdate(" SET plv8.start_proc = 'plv8_init' ");
	
			
			//--- Location Res Creation
			for (int i = 1; i <2; i++) { 
				
				String SqlLocation = " SELECT fhir_create_resource(' {"
						+ " \"allowId\":true,"
						+ " \"resource\":{\"resourceType\":\"Location\", "
						+ " \"status\":\"active\", "
						+ " \"name\":\" " + addressGenerator.getElement(ExcelElement.HOSPITAL_NAME, i) + " \", "
						+ " \"address\":[{\"line\":[\""+ addressGenerator.getElement(ExcelElement.LINE, i) + "\"], "
						+ " \"city\":\""+ addressGenerator.getElement(ExcelElement.CITY, i) + "\", \"postalCode\":\"" + addressGenerator.getElement(ExcelElement.POSTCODE, i)+ "\", \"country\":\""+ addressGenerator.getElement(ExcelElement.STATE, i) + "\"  }] "
						+ "}} ')";
			
				rs = st.executeQuery( SqlLocation);
					
				System.out.println("Location Resources uploaded! \n");
			} // for

			//--- Patient-Encounter Res Creation
//			for (int i = 1; i <2; i++) { 
//				
//				String SqlPatient = " SELECT fhir_create_resource(' {"
//						+ "\"allowId\":true,"
//						+ "\"resource\":{\"resourceType\":\"Patient\","
//						+ "\"id\":\"" + i + "\", "
//						+ " \"name\":[{\"family\":\""+ DataGenerator_AT.generateNachName() + "\", \"given\":\""+ DataGenerator_AT.generateVorName() + "\"}], "
//						+ " \"address\":[{\"line\":[\""+ DataGenerator_AT.generateStreet() + DataGenerator_AT.generateHouseNumber() + "\"], "
//						+ " \"city\":\""+ generator.randomiseCity() + "\", \"postalCode\":\""+ generator.randomisePostcode() + "\"}] "
//						+ "}} ')";	
//				
//				String SqlEncounter = " SELECT fhir_create_resource(' {"
//						+ " \"allowId\":true,"
//						+ " \"resource\":{\"resourceType\":\"Encounter\","
//						+ "\"id\":\"" + i + "\", "
//						+ " \"status\":\"onleave\", "
//						
//						+ " \"patient\":{\"reference\":\"Patient/" + i + "\"}, "
//						+ " \"location\":[{\"location\":{\"reference\":\"Location/1 \"},\"status\":\"active\"}] "
//								
//						+ "}} ')";
//					
//				rs = st.executeQuery( SqlPatient );
//				rs = st.executeQuery( SqlEncounter);
//					
//				System.out.println("processing... ");
//			} // for
            
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
