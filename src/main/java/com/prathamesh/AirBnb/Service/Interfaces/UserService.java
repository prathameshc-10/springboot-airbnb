package com.prathamesh.AirBnb.Service.Interfaces;


import com.prathamesh.AirBnb.Dto.ProfileUpdateRequestDTO;
import com.prathamesh.AirBnb.Dto.UserDTO;
import com.prathamesh.AirBnb.Entities.UserEntity;

public interface UserService {
    UserEntity getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDTO profileUpdateRequestDto);

    UserDTO getMyProfile();
}

