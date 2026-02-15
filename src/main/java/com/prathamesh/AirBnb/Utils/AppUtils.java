package com.prathamesh.AirBnb.Utils;

import com.prathamesh.AirBnb.Entities.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUtils {
    public static UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
