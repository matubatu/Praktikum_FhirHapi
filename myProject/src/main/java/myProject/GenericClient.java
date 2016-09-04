
package myProject;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;

public class GenericClient {

	public static void main(String[] args) {

		FhirContext ctx = FhirContext.forDstu2();
		String serverBase = "http://fhirtest.uhn.ca/baseDstu2";
		 
		IGenericClient client = ctx.newRestfulGenericClient(serverBase);
		 
		// Perform a search
		ca.uhn.fhir.model.dstu2.resource.Bundle results = client
		      .search()
		      .forResource(Patient.class)
		      .where(Patient.FAMILY.matches().value("duck"))
		      .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
		      .execute();
		 
		System.out.println("Found " + results.getEntry().size() + " patients named 'duck'");

	}

}
