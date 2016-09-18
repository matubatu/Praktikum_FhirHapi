
package Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import dataGenerators.DataGenerator;

public class PatientErzeugen_Hapi_Test {

	public static void main(String[] args) {
		
		FhirContext ctx = FhirContext.forDstu2();
		Patient patient = new Patient();
		
		for (int i = 1; i < 2; i++) {
			
			patient.setId("Patient/smith7");
			HumanNameDt name = patient.addName();
			name.addFamily(DataGenerator.generateNachName());
			name.addGiven(DataGenerator.generateVorName());
//			patient.setGender(AdministrativeGenderEnum.MALE);
			
			AddressDt address = patient.addAddress();
			address.addLine(DataGenerator.generateStreet() + "" +  DataGenerator.generateHouseNumber());
			address.setCity(DataGenerator.generateStadt());
			address.setPostalCode( DataGenerator.generatePlz() );
			
			String patientPrint2 = ctx.newJsonParser().encodeResourceToString(patient);
			System.out.println("Without PrettyPrint: " + patientPrint2 + "\n");
			
			System.out.println("Nur name: " + name.getFormatCommentsPost() + "\n");

			String patientPrint = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
			System.out.println(patientPrint + "\n");
			
		} // for
		
	}

}
