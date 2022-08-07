package com.allegiancemd.resourceprovider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.allegiancemd.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class PatientResourceProvider implements IResourceProvider {
    private final PatientService patientService;

    //A Map to hold all the patients.
    private Map<String, Patient> patientMap = new HashMap<String, Patient>();

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Patient.class;
    }

    @Search
    public List<Patient> search(@RequiredParam(name = Patient.SP_FAMILY) StringParam theParam) {
        List<Patient> patients = new ArrayList<Patient>();
        patients = this.searchByFamilyName(theParam.getValue());
        return patients;
    }

    @Search
    public List<Patient> getAllPatients() {
        loadDummyPatients();
        return patientMap.values().stream().collect(Collectors.toList());
    }

    @Read()
    public Patient read(@IdParam IdType theId) {
        loadDummyPatients();
        Patient retVal = patientMap.get(theId.getIdPart());


        FhirContext fhirContext = FhirContext.forR4();

//        used to parse / encode ResourceProvider to xml string
        String resourceToString = fhirContext.newXmlParser().encodeResourceToString(retVal);
        log.info("ResourceProvider To Xml String ========> " + resourceToString);

//        used to parse / encode ResourceProvider to json string
        IParser iParser = fhirContext.newJsonParser();
        resourceToString = iParser.encodeResourceToString(retVal);
        log.info("ResourceProvider To Json String ========> " + resourceToString);

//        used to decode ResourceProvider from json string
        Patient parsedPatient = iParser.parseResource(Patient.class, resourceToString);
        log.info("parsedPatient =======> " + parsedPatient);

        if (retVal == null) {
            throw new ResourceNotFoundException(theId);
        }
        return retVal;
    }

    private List<Patient> searchByFamilyName(String familyName) {
        List<Patient> retPatients;
        loadDummyPatients();
        // Encode the output, including the narrative - see below
        // Loop through the patients looking for matches
        retPatients = patientMap.values()
                .stream()
                .filter(next -> familyName.toLowerCase().equals(next.getNameFirstRep().getFamily().toLowerCase()))
                .collect(Collectors.toList());
        return retPatients;
    }

    private void loadDummyPatients() {
        Patient patient = new Patient();
        List<com.allegiancemd.entity.Patient> patientList = patientService.getAllPatients();
        for (int i = 0; i < patientList.size(); i++) {
            patient = new Patient();
            patient.setId(Integer.toString(i));
            patient.addIdentifier().setSystem("http://optum.com/MRNs").setValue(patientList.get(i).getId().toString());
            patient.addName().setFamily(patientList.get(i).getLastName()).addGiven(patientList.get(i).getFirstName());

            this.patientMap.put(Integer.toString(i), patient);
        }
    }
}
