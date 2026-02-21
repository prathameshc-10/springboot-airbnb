package com.prathamesh.AirBnb.dto;

import com.prathamesh.AirBnb.enums.Gender;
import lombok.Data;

@Data
public class GuestDTO {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
}