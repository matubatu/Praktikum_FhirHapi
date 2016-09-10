package Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.json.Json;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.ElementDefinitionDt.Type;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;

public class PostgreConnectivityTest2 {

	public static void main(String[] args) {
		
		Connection con = null;
		
//	http://stackoverflow.com/questions/17435060/call-a-stored-function-on-postgres-from-java
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			
			if (con!=null)
				System.out.println("Connected");
			Statement st = con.createStatement();
			
// http://stackoverflow.com/questions/11862936/return-rows-from-a-pl-pgsql-function
			
			st.execute("CREATE OR REPLACE FUNCTION fhir_read_resource(query json) RETURNS json AS' "
					+ "$BODY$"
// 					
					+ "  var mod = require('/fhirbase/src/fhir/crud.coffee') " 					//ERROR -> path "" wurde gewechselt: '' (aber dann syntax Fehler bei "/")
//					+ "  var mod = require( "C:/fhirbase-plv8-master/src/fhir/crud.coffee" ) "  //path wurde geändert
//					+ "  var mod = require( "C:\fhirbase-plv8-master\src\fhir\crud.coffee" ) "  // Slash-backslash wurde geändert
					
					+ "  return mod.fhir_read_resource(plv8, query)"
					+ "$BODY$"
					+ "  LANGUAGE plv8 VOLATILE"
					+ "  COST 100;"
					+ "ALTER FUNCTION fhir_read_resource(json)"
					+ "  OWNER TO postgres;' ");
					
			ResultSet rs;
//			rs = st.executeQuery(" SELECT fhir_create_resource('{ {"resource": {"resourceType": "Patient", "name": [{"given": ["Smith"]}]}} }')::jsonb; ");
//			String funk = " SELECT fhir_create_resource('{"resource": {"resourceType": "Patient", "name": [{"given": ["Smith"]}]}}'::jsonb; ) ";
			String funk = " SELECT fhir_create_resource(' { 'resource': {'resourceType': 'Patient', 'name': [{'given': ['Smith']}]} } ')::jsonb "; //ERROR -> "" wurde gewechselt: '' 
			rs = st.executeQuery(funk);
		
			while (rs.next()) {
			    // do something with the results...
			}
        	rs.close();
        	con.close();
			st.close();
			
		} catch (SQLException ee) {
			ee.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
