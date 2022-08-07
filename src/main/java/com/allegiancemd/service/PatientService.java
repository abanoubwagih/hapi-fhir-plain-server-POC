package com.allegiancemd.service;

import com.allegiancemd.entity.PatientEntity;
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

    public PatientEntity getPatient(@NotNull @Positive Integer patientId) {
        Optional<PatientEntity> patient = patientRepo.findPatient(patientId);
        return Optional.ofNullable(patient).get().get();
    }

    public List<PatientEntity> getAllPatients() {
        List<PatientEntity> patientEntity = patientRepo.findAllPatient();
        return patientEntity;
    }
}
