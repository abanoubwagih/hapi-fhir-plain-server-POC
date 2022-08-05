package com.allegiancemd.controller;

import com.allegiancemd.entity.Patient;
import com.allegiancemd.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Slf4j
@RequestMapping()
@RequiredArgsConstructor
@RestController
public class PatientController {
   private final PatientService patientService;

   @GetMapping("/patient")
   public ResponseEntity<Patient> patient(@RequestParam("patientId") @NotNull @Positive Integer patientId) {
      log.info("================================");
      Patient patient = patientService.getPatient(patientId);
      return ResponseEntity.ok().body(patient);
   }
}
