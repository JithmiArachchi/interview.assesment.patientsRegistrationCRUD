package com.monitoredrx.interview.assesment.Repos;

import com.monitoredrx.interview.assesment.DTO.response.PatientResponseDto;
import com.monitoredrx.interview.assesment.Entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patients, UUID> {

}
