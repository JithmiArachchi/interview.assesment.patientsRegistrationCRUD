package com.monitoredrx.interview.assesment.DTO.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class PatientRequestDto {

    private UUID id;

    private String firstName;
    private String lastName;
    private String address;
    private String  city;
    private String  state;


    private String zipCode;
    private List<String> phoneNumber;
    private String email;

    private LocalDateTime createdDate;
    private String createdBy;

    private String updatedBy;
    private LocalDateTime updatedDate;

    private Boolean active;

}
