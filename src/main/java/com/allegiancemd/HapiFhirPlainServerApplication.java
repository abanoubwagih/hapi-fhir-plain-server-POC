package com.allegiancemd;

import com.allegiancemd.fhir.SimpleRestfulServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication
public class HapiFhirPlainServerApplication {
    private final SimpleRestfulServer simpleRestfulServer;

    public static void main(String[] args) {
        SpringApplication.run(HapiFhirPlainServerApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean ServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(simpleRestfulServer, "/*");
        registration.setName("FhirServlet");
        return registration;
    }
}
