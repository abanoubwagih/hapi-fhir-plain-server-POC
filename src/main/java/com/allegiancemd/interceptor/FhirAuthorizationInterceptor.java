package com.allegiancemd.interceptor;

import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

@Slf4j
@Interceptor
public class FhirAuthorizationInterceptor extends AuthorizationInterceptor {
    @Override
    public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {

        // Process this header
        String authHeader = theRequestDetails.getHeader("patientId");
        log.info("Auth Header of patientId = " + authHeader);

        RuleBuilder builder = new RuleBuilder();
        builder.allow().metadata().andThen()
                .allow().read().allResources().withAnyId().andThen()
                .allow().write().resourcesOfType(Observation.class).inCompartment("PatientEntity", new IdType("PatientEntity/123"));

        return builder.build();
    }
}
