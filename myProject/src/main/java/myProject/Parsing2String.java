
package myProject;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.parser.IParser;


public class Parsing2String {
	
	public static void main(String[] args){
		
		FhirContext ctxDstu2 = FhirContext.forDstu2();
		
		String msgString = "<Patient xmlns=\"http://hl7.org/fhir\">"
				+ "<text><status value=\"generated\" /><div xmlns=\"http://www.w3.org/1999/xhtml\">John Cardinal</div></text>"
				+ "<identifier><system value=\"http://orionhealth.com/mrn\" /><value value=\"PRP1660\" /></identifier>"
				+ "<name><use value=\"official\" /><family value=\"Cardinal\" /><given value=\"John\" /></name>"
				+ "<gender><coding><system value=\"http://hl7.org/fhir/v3/AdministrativeGender\" /><code value=\"M\" /></coding></gender>"
				+ "<address><use value=\"home\" /><line value=\"2222 Home Street\" /></address><active value=\"true\" />"
				+ "</Patient>";
		
		IParser parser = ctxDstu2.newXmlParser();
		Patient patient = parser.parseResource(Patient.class, msgString);
		
		String patientId = patient.getIdentifier().get(0).getValue();
		String familyName = patient.getName().get(0).getFamily().get(0).getValue();
		String gender = patient.getGender();
		
		System.out.println(patientId);
		System.out.println(familyName);
		System.out.println(gender);
	}
	

}
