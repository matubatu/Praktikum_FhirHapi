package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.ContainedDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.Encounter.Location;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AddressUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ch.qos.logback.core.net.SyslogOutputStream;
import dataGenerators.DataGenerator;

public class Pat_RandomInStatement {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		FhirContext ctx = FhirContext.forDstu2();
		Patient patient = new Patient();
		
//Patient ERZEUGEN
		
		HumanNameDt name = patient.addName();
		
		name.addFamily(DataGenerator.generateNachName());
		name.addGiven(DataGenerator.generateVorName());
		patient.setGender(AdministrativeGenderEnum.MALE);
		
		AddressDt address = patient.addAddress();
		address.setCity(DataGenerator.generateStadt());
		address.setPostalCode( DataGenerator.generatePlz() );
		
		String patientPrint = ctx.newJsonParser().encodeResourceToString(patient);
//		String patientPrint = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
		System.out.println(patientPrint + "\n");
		
//		String SqlPrint = SqlPrint.replaceAll("('|\")", "\\\\$1");
//		System.out.println(SqlPrint);
		
		String SqlStatem = " SELECT fhir_create_resource(\'{ \"allowId\": true, \"resource\": " + patientPrint + "\');";
		System.out.println(SqlStatem + "\n");
		
		String SqlPrint = SqlStatem.replaceAll("('|\")", "\\\\$1");
//		SqlPrint = SqlStatem.replace("\'", "\\\'");
		System.out.println(SqlPrint + "\n");
        
//        String patient = " SELECT fhir_create_resource(\'{ \"allowId\": true, \"resource\": { \"resourceType\": \"Patient\", \"id\": nachName, \"name\":[{\"given\":\"Bruno\"}]}}\'); ";

//		try {
//			Class.forName("org.postgresql.Driver");
//			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
//			
//			if (con!=null) System.out.println("Connected");
//			
//			st = con.createStatement();
//            rs = st.executeQuery("SELECT VERSION()");
//
//            if (rs.next()) {
//                System.out.println(rs.getString(1));
//            }
//
//            st.executeUpdate(" SET plv8.start_proc = 'plv8_init' ");
//            
//
//            rs = st.executeQuery( SqlStatem );
//            System.out.println("Finished Pat");
//            
//				
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally{
//        }
		
	}

}
