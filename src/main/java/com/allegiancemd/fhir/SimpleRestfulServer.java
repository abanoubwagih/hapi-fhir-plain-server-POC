package com.allegiancemd.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import com.allegiancemd.interceptor.FhirAuthorizationInterceptor;
import com.allegiancemd.interceptor.RequestCounterInterceptor;
import com.allegiancemd.interceptor.RequestExceptionInterceptor;
import com.allegiancemd.resourceprovider.PatientResourceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@RequiredArgsConstructor
@WebServlet("/*")
@Component
public class SimpleRestfulServer extends RestfulServer {
    private final PatientResourceProvider patientResourceProvider;
    private final FhirContextSingleton fhirContextSingleton;

    @Override
    protected void initialize() throws ServletException {
        //create a context for the appropriate version
        setFhirContext(fhirContextSingleton.fhirContextSingleton());
        //Register Resource Providers
        registerProvider(patientResourceProvider);

//        register Interceptors
        registerInterceptor(new FhirAuthorizationInterceptor());
        registerInterceptor(new RequestCounterInterceptor());
        registerInterceptor(new RequestExceptionInterceptor());
    }
}
