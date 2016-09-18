package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreConnectivityTest2 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbd:postgresql://localhost:2345/fhir","postgres", "");
			
			if (con!=null)
				System.out.println("Connected");
			
			st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

            st.executeUpdate(" SET plv8.start_proc = 'plv8_init'");
            
// Patient Erzeugen
//          Json Patient: {"allowId":true,"resource":{"resourceType":"Patient","id":"smith","name":[{"given":"Bruno"}]}}
            
            String x = "SELECT fhir_create_resource(\'{ \"allowId\": true, \"resource\": {\"resourceType\":\"Patient\",\"id\":\"smithyx\",\"name\":[{\"family\":[\"Wagner\"],\"given\":[\"Azakirzur\"]}],\"address\":[{\"line\":[\"Bussardgasse 41\"],\"city\":\"Bad Ischl\",\"postalCode\":\"2268\"}]}\')";
            rs = st.executeQuery(x);

//             
//          Json Encounter:  {"allowId":true,"resource":{"resourceType":"Encounter","status":"onleave","patient":{"reference":"Patient/smith"}}}
//            rs = st.executeQuery( "SELECT fhir_create_resource(\' {\"allowId\":true,\"resource\":{\"resourceType\":\"Encounter\",\"status\":\"onleave\",\"patient\":{\"reference\":\"Patient/smith\"}}} \') ");
            
            System.out.println("Finished calling StoredProcedure");
			
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
        }
		
	}

}
