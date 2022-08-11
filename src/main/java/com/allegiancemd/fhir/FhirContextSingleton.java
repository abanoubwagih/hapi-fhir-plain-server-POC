package com.allegiancemd.fhir;

import ca.uhn.fhir.context.FhirContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FhirContextSingleton {
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public FhirContext fhirContextSingleton() {
        log.info("FhirContext created only one time...");
        return FhirContext.forR4();
    }
}
