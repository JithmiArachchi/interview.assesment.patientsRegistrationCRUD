package com.monitoredrx.interview.assesment.Controller;

import com.monitoredrx.interview.assesment.DTO.request.PatientRequestDto;
import com.monitoredrx.interview.assesment.DTO.request.PatientUpdateRequest;
import com.monitoredrx.interview.assesment.DTO.response.PatientResponseDto;
import com.monitoredrx.interview.assesment.Entity.Patients;
import com.monitoredrx.interview.assesment.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {
    private final PatientService service;

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient
            (@RequestBody PatientRequestDto requestDto) {
        PatientResponseDto patientResponseDto = service.createPatient(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        List<PatientResponseDto> patients = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable UUID id) {
        PatientResponseDto patient = service.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient
            (@PathVariable UUID id,
             @RequestBody PatientUpdateRequest requestDto) {
        PatientResponseDto update = service.updatePatient(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        service.deletePatient(id);
        return ResponseEntity.ok().build();
    }

}
