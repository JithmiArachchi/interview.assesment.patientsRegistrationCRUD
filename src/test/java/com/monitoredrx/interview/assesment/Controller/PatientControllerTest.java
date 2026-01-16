package com.monitoredrx.interview.assesment.Controller;

import com.monitoredrx.interview.assesment.DTO.request.PatientRequestDto;
import com.monitoredrx.interview.assesment.DTO.request.PatientUpdateRequest;
import com.monitoredrx.interview.assesment.DTO.response.PatientResponseDto;
import com.monitoredrx.interview.assesment.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
    @InjectMocks
    private PatientController patientController;
    @Mock
    private PatientService patientService;

   @Test
    void createPatient() {
       PatientRequestDto req = new PatientRequestDto();
       PatientResponseDto res = new PatientResponseDto();

       when(patientService.createPatient(any(PatientRequestDto.class))).thenReturn(res);
       ResponseEntity<PatientResponseDto> output = patientController.createPatient(req);
       assertThat(output.getStatusCode()).isEqualTo(HttpStatus.CREATED);
       assertThat(output.getBody().getId()).isEqualTo(res.getId());
       verify(patientService).createPatient(req);
    }

    @Test
    void getAllPatients() {
        List<PatientResponseDto> patient = List.of(new PatientResponseDto());
        when(patientService.getAll()).thenReturn(patient);
        ResponseEntity<List<PatientResponseDto>> output = patientController.getAllPatients();
        assertThat(output.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(output.getBody()).isEqualTo(patient);
        verify(patientService).getAll();
    }

    @Test
    void getPatientById() {
       UUID id = UUID.randomUUID();
       PatientResponseDto res = new PatientResponseDto();
       when(patientService.getPatientById(id)).thenReturn(res);
       ResponseEntity<PatientResponseDto> output = patientController.getPatientById(id);
       assertThat(output.getStatusCode()).isEqualTo(HttpStatus.OK);
       assertThat(output.getBody()).isEqualTo(res);
       verify(patientService).getPatientById(id);
    }

    @Test
    void updatePatient() {
       UUID id = UUID.randomUUID();
       PatientUpdateRequest req = new PatientUpdateRequest();
       PatientResponseDto update = new PatientResponseDto();
       when(patientService.updatePatient(eq(id), any())).thenReturn(update);
       ResponseEntity<PatientResponseDto> result
               = patientController.updatePatient(id,req);
       assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
       assertThat(result.getBody()).isEqualTo(update);
       verify(patientService).updatePatient(id,req);
    }

    @Test
    void deletePatient() {
       UUID id = UUID.randomUUID();
       doNothing().when(patientService).deletePatient(id);
       ResponseEntity<Void> result = patientController.deletePatient(id);
       assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
       verify(patientService).deletePatient(id);
    }



}


