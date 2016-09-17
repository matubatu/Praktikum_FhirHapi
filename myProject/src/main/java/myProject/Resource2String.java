package myProject;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;
import ca.uhn.fhir.model.primitive.IdDt;
import ch.qos.logback.core.net.SyslogOutputStream;

// Encoding a Resource to a String

public class Resource2String {

	public static void main(String[] args) {
		
		FhirContext ctx = FhirContext.forDstu2();
		
		Patient patient = new Patient();
		
		// Add an MRN:  patient identifier
		IdentifierDt id = patient.addIdentifier();
		id.setSystem("http://example.com/...");
		id.setValue("MRN001");
		
		HumanNameDt name = patient.addName();
		name.setUse(NameUseEnum.OFFICIAL);
		name.addFamily("Test");
		name.addGiven("Graham");
		name.addGiven("Steven");

		// with parser encode this resource into a string.
		String encoded = ctx.newXmlParser().encodeResourceToString(patient);
		System.out.println(encoded);
		
//		IdDt ref = patient. getManagingOrganization().getReference();
//		System.out.println(ref);
		
		Patient patient2 = new Patient();
		
		patient2.setId("Patient/122");
		patient2.addIdentifier().setSystem("urn:mrns").setValue("253345");
		patient2.getManagingOrganization().setReference("Organization/123456");
//		System.out.println(ref);
		
//		String encoded2 = ctx.newJsonParser().encodeResourceToString(patient2);
		String encoded2 = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient2);
		System.out.println(encoded2);
		
		
		// Fluent Programming
//		Patient patient = new Patient();
//		patient.addIdentifier().setSystem("http://example.com/fictitious-mrns").setValue("MRN001");
//		patient.addName().setUse(NameUseEnum.OFFICIAL).addFamily("Tester").addGiven("John").addGiven("Q");
//		 
//		String encoded = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
//		System.out.println(encoded);
	}

}
