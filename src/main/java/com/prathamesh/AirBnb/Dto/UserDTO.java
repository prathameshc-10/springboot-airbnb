package com.prathamesh.AirBnb.Dto;

import com.prathamesh.AirBnb.Enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private  String email;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;

}

