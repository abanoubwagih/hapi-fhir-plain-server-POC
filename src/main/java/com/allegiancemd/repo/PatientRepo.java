package com.allegiancemd.repo;

import com.allegiancemd.entity.Patient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@Mapper
@Validated
public interface PatientRepo {
   @Select("select * from patient where id = #{patientId}")
   Optional<Patient> findPatient(@NotNull @Positive @Param("patientId") Integer patientId);
}
