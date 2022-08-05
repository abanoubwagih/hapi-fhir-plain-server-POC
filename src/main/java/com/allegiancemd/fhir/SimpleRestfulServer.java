package com.allegiancemd.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import com.allegiancemd.resourceprovider.PatientResourceProvider;
import com.allegiancemd.service.PatientService;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@RequiredArgsConstructor
@WebServlet("/*")
public class SimpleRestfulServer extends RestfulServer {
    private final PatientService patientService;

    @Override
    protected void initialize()throws ServletException {
        //create a context for the appropriate version
        setFhirContext(FhirContext.forR4());
        //Register Resource Providers
        registerProvider(new PatientResourceProvider(patientService));
    }
}
