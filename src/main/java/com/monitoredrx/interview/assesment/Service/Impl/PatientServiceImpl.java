package com.monitoredrx.interview.assesment.Service.Impl;

import com.monitoredrx.interview.assesment.DTO.request.PatientRequestDto;
import com.monitoredrx.interview.assesment.DTO.request.PatientUpdateRequest;
import com.monitoredrx.interview.assesment.DTO.response.PatientResponseDto;
import com.monitoredrx.interview.assesment.Entity.Patients;
import com.monitoredrx.interview.assesment.Repos.PatientRepository;
import com.monitoredrx.interview.assesment.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public PatientResponseDto createPatient(PatientRequestDto requestDto) {
        Patients patient = modelMapper.map(requestDto, Patients.class);
        patient.setActive(true);
        Patients savePatients=patientRepository.save(patient);
        return modelMapper.map(savePatients,PatientResponseDto.class);
    }

    @Override
    public List<PatientResponseDto> getAll() {
        return patientRepository.findAll()
                .stream()
                .map(patients -> modelMapper.map(patients,PatientResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDto getPatientById(UUID id) {
        Patients patient = patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient not found: "+ id));
        return modelMapper.map(patient,PatientResponseDto.class);
    }

    @Override
    public PatientResponseDto updatePatient(UUID id, PatientUpdateRequest requestDto) {
        System.out.println("updatePatient"+id + " " + requestDto);
        Patients findCurrentPatient= patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient not found: "+ id));

        if (requestDto.getActive()!=null) findCurrentPatient.setActive(requestDto.getActive());

        if (requestDto.getCity()!=null) findCurrentPatient.setCity(requestDto.getCity());
        if (requestDto.getFirstName()!=null) findCurrentPatient.setFirstName(requestDto.getFirstName());
        if (requestDto.getLastName()!=null) findCurrentPatient.setLastName(requestDto.getLastName());
        if (requestDto.getEmail()!=null) findCurrentPatient.setEmail(requestDto.getEmail());
        if (requestDto.getState()!=null) findCurrentPatient.setState(requestDto.getState());
        if (requestDto.getAddress()!=null) findCurrentPatient.setAddress(requestDto.getAddress());
        if (requestDto.getPhoneNumber()!=null) findCurrentPatient.setPhoneNumber(requestDto.getPhoneNumber());

       findCurrentPatient.setUpdatedDate(LocalDateTime.now());
        findCurrentPatient.setUpdatedBy("loginUser");
//        modelMapper.map(requestDto,findCurrentPatient);
//        Patients updatePatient=modelMapper.map(findCurrentPatient,Patients.class);
       Patients updatePatient =patientRepository.save(findCurrentPatient);
        return modelMapper.map(updatePatient,PatientResponseDto.class);
    }

    @Override
    public void deletePatient(UUID id) {
        if(!patientRepository.existsById(id)){
            throw new RuntimeException("Patient not found: "+ id);
        }
        patientRepository.deleteById(id);
    }
}
