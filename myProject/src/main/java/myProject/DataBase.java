package myProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import Test.VersionTest;

import java.util.logging.Level;

import dataGenerators.DataGenerator;

public class DataBase {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
//--- Connection-Configuration of DB
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			if (con!=null) System.out.println("Server: Connected");
			st = con.createStatement();
			st.executeUpdate(" SET plv8.start_proc = 'plv8_init' ");
	
//--- Patient-Encounter Creation
			for (int i = 1; i <10000; i++) { 
				
				String SqlPatient = " SELECT fhir_create_resource(' {"
						+ "\"allowId\":true,"
						+ "\"resource\":{\"resourceType\":\"Patient\","
						+ "\"id\":\"" + i + "\", "
						+ " \"name\":[{\"family\":\""+ DataGenerator.generateNachName() + "\", \"given\":\""+ DataGenerator.generateVorName() + "\"}], "
						+ " \"address\":[{\"line\":[\""+ DataGenerator.generateStreet() + DataGenerator.generateHouseNumber() + "\"], "
						+ " \"city\":\""+ DataGenerator.generateStadt() + "\", \"postalCode\":\""+ DataGenerator.generatePlz() + "\"}] "
						+ "}} ')";	
				
				String SqlEncounter = " SELECT fhir_create_resource(' {"
						+ " \"allowId\":true,"
						+ " \"resource\":{\"resourceType\":\"Encounter\","
						+ " \"status\":\"onleave\","
						+ " \"patient\":{\"reference\":\"Patient/" + i + "\"} "
						+ "}} ')";
				
				rs = st.executeQuery( SqlPatient );
				rs = st.executeQuery( SqlEncounter);
				System.out.println("processing... ");
			} // for
            
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
