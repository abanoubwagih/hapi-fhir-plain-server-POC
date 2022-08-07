package com.allegiancemd.interceptor;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Interceptor
public class RequestCounterInterceptor {

    private int myRequestCount;

    public int getRequestCount() {
        return myRequestCount;
    }

    /**
     * Override the incomingRequestPreProcessed method, which is called
     * for each incoming request before any processing is done
     */
    @Hook(Pointcut.SERVER_INCOMING_REQUEST_PRE_PROCESSED)
    public boolean incomingRequestPreProcessed(HttpServletRequest theRequest, HttpServletResponse theResponse) {
        myRequestCount++;
        log.info("myRequestCount is: " + myRequestCount);
        return true;
    }

}
