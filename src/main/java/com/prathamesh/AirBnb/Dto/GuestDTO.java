package com.prathamesh.AirBnb.Dto;


import com.prathamesh.AirBnb.Enums.Gender;
import lombok.Data;

@Data
public class GuestDTO {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
}
