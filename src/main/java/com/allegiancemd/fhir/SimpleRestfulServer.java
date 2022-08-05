package com.allegiancemd.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/*")
public class SimpleRestfulServer extends RestfulServer {
    @Override
    protected void initialize()throws ServletException {
        //create a context for the appropriate version
        setFhirContext(FhirContext.forR4());
        //Register Resource Providers - COMING SOON
    }
}
