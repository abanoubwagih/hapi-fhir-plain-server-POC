package com.allegiancemd.service;

import com.allegiancemd.entity.Patient;
import com.allegiancemd.repo.PatientRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@Validated
@Slf4j
@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepo patientRepo;

    public Patient getPatient(@NotNull @Positive Integer patientId) {
        Optional<Patient> patient = patientRepo.findPatient(patientId);
        log.info(String.valueOf(patient.get()));
        return Optional.ofNullable(patient).get().get();
    }

    public List<Patient> getAllPatients() {
        List<Patient> patient = patientRepo.findAllPatient();
        return patient;
    }
}
