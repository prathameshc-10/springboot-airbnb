package com.prathamesh.AirBnb.dto;

import com.prathamesh.AirBnb.entities.HotelContactInfo;
import lombok.Data;

import java.util.List;

@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String city;
    private List<String> photos;
    private List<String> amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
}
