package com.monitoredrx.interview.assesment.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String address;
    private String  city;
    private String  state;

    @Size(min = 1, max = 5)
    private String zipCode;

    @ElementCollection
    private List<String> phoneNumber;

    @Email
    private String email;

    private LocalDateTime createdDate = LocalDateTime.now();
    private String createdBy;

    private String updatedBy;
    private LocalDateTime updatedDate;

    private Boolean active;

}
