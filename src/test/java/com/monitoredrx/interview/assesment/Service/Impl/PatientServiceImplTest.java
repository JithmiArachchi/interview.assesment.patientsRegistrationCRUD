package com.monitoredrx.interview.assesment.Service.Impl;

import com.monitoredrx.interview.assesment.DTO.request.PatientRequestDto;
import com.monitoredrx.interview.assesment.DTO.request.PatientUpdateRequest;
import com.monitoredrx.interview.assesment.DTO.response.PatientResponseDto;
import com.monitoredrx.interview.assesment.Entity.Patients;
import com.monitoredrx.interview.assesment.Repos.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {
    @Mock
    PatientRepository patientRepository;
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    PatientServiceImpl patientServiceImpl;


    @Test
    void createPatient() {
        PatientRequestDto dto=new PatientRequestDto();
        Patients savedEntity=new Patients();

        when(modelMapper.map(dto,Patients.class)).thenReturn(savedEntity);
        when(patientRepository.save(savedEntity)).thenReturn(savedEntity);
        when(modelMapper.map(savedEntity,PatientResponseDto.class))
                .thenReturn(new PatientResponseDto());
        PatientResponseDto result=
                patientServiceImpl.createPatient(dto);

        verify(patientRepository).save(savedEntity);
        assertThat(savedEntity.getActive()).isTrue();
        assertThat(result).isNotNull();
    }

    @Test
    void getAll(){
        Patients p1= new Patients();
        Patients p2= new Patients();

        when(patientRepository.findAll()).thenReturn(List.of(p1,p2));
        var result=patientServiceImpl.getAll();
        assertThat(result).hasSize(2);
        verify(patientRepository).findAll();
    }

    @Test
    void getById(){
        UUID id=UUID.randomUUID();
        Patients currentPatient= new Patients();

        when(patientRepository.findById(id))
                .thenReturn(Optional.of(currentPatient));

        PatientResponseDto result = patientServiceImpl.getPatientById(id);
        assertThat(result).isNotNull();
        verify(patientRepository).findById(id);
    }


    @Test
    void upatePatient(){
        UUID id=UUID.randomUUID();

        Patients current = Patients.builder()
                .firstName("Old Jhone")
                .active(true)
                .build();

        PatientUpdateRequest dto=new PatientUpdateRequest();
        dto.setFirstName("Harry");
        dto.setLastName("Scott");
        dto.setActive(false);

        when(patientRepository.findById(id)).thenReturn(Optional.of(current));
        when(patientRepository.save(any(Patients.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        patientServiceImpl.updatePatient(id,dto);
        assertThat(current.getActive()).isFalse();
        assertThat(current.getFirstName()).isEqualTo("Harry");
        assertThat(current.getLastName()).isEqualTo("Scott");

        verify(patientRepository).save(current);
    }



}

