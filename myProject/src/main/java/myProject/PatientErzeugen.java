

package myProject;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.ContainedDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AddressUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ch.qos.logback.core.net.SyslogOutputStream;
import dataGenerators.DataGenerator;
import dataGenerators.DataGenerator;

// Encoding a Resource to a String

public class PatientErzeugen {

	public static void main(String[] args) {
		
		FhirContext ctx = FhirContext.forDstu2();
		
		Patient patient = new Patient();
		
	//id Generate:
		patient.setId("Patient/1333");
		patient.addIdentifier().setSystem("urn:mrns").setValue("253345");
		
	// Contained Resources fuer Patient: Encounter
		Encounter encounter = new Encounter();
		encounter.setContained(new ContainedDt());
		
		
		
		
		
		HumanNameDt name = patient.addName();
		
	// Generierung von Namen
		name.setUse(NameUseEnum.OFFICIAL);
		name.addFamily(DataGenerator.generateNachName());
		name.addGiven(DataGenerator.generateVorName());
		name.addGiven(DataGenerator.generateVorName());

		patient.setGender(AdministrativeGenderEnum.MALE);
		patient.setBirthDate(new DateDt("1987-11-11"));
		
	// Generierung von Adresse

		AddressDt address = patient.addAddress();
		address.setUse(AddressUseEnum.HOME);
		address.addLine("Erewhon St 23");
		address.setCity(DataGenerator.generateStadt());
		address.setPostalCode( DataGenerator.generatePlz() );
		
		
		patient.getManagingOrganization().setReference("Organization/123456");

		String encoded = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
		System.out.println(encoded);

		
	}

}
