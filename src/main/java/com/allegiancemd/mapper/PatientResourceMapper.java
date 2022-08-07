package com.allegiancemd.mapper;

import com.allegiancemd.entity.PatientEntity;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientResourceMapper {
    PatientResourceMapper INSTANCE = Mappers.getMapper(PatientResourceMapper.class);

    @Mappings({
            @Mapping(target = "identifier", expression = "java(getPatientIdentifier(patientEntity.getId()))"),
            @Mapping(target = "name", expression = "java(getPatientName(patientEntity.getFirstName(),patientEntity.getLastName()))"),
    })
    Patient wrapperToPatientResource(PatientEntity patientEntity);

    default List<Identifier> getPatientIdentifier(Integer id) {
        if (id != null) {
            List<Identifier> theIdentifier = new ArrayList<>();
            Identifier identifier = new Patient().addIdentifier().setSystem("http://optum.com/MRNs").setValue(id.toString());
            theIdentifier.add(identifier);
            return theIdentifier;
        }
        return null;
    }

    default List<HumanName> getPatientName(String firstName, String lastName) {
        if (firstName != null || lastName != null) {
            List<HumanName> humanNameList = new ArrayList<>();
            HumanName humanName = new Patient().addName().setFamily(lastName).addGiven(firstName);
            humanNameList.add(humanName);
            return humanNameList;
        }
        return null;
    }

}
