package Test;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.json.Json;

import org.postgresql.core.Query;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.ElementDefinitionDt.Type;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;

public class PostgreConnectivityTest3 {

	public static void main(String[] args) {
		
		Connection con = null;
		CallableStatement callSt;

		/// https://www.youtube.com/watch?v=_2sJls8rnBU
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			
			if (con!=null)
				System.out.println("Connected");
//			Statement st = con.createStatement();
			
			// Original
//			String funk = " SELECT fhir_create_resource('{"resource": {"resourceType": "Patient", "name": [{"given": ["Smith"]}]}}'::jsonb) ";
			// mit '' satt ""
			String funk = " SELECT fhir_create_resource('{'resource': {'resourceType': 'Patient', 'name': [{'given': ['Smith']}]}}'::jsonb) ";
			
			callSt = con.prepareCall("{ CALL fhir_create_resource(?) }");
			callSt.setString(1, funk);
			callSt.execute();
			
			System.out.println("Finishd calling StoredP");
			
//			callSt.registerOutParameter(1, Types.String);
			
//			while (rs.next()) {
//			    // do something with the results...
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//        	con.close(); 
//			callSt.close();		
			}
		
	}

}
