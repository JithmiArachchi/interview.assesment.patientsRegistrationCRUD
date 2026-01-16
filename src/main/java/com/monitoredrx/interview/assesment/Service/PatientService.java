package com.monitoredrx.interview.assesment.Service;

import com.monitoredrx.interview.assesment.DTO.request.PatientRequestDto;
import com.monitoredrx.interview.assesment.DTO.request.PatientUpdateRequest;
import com.monitoredrx.interview.assesment.DTO.response.PatientResponseDto;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto requestDto);

    List<PatientResponseDto> getAll();

    PatientResponseDto getPatientById(UUID id);

    PatientResponseDto updatePatient(UUID id, PatientUpdateRequest requestDto);

    void deletePatient(UUID id);
}
