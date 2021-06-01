package org.myuniv.system.course.services;

import org.myuniv.system.course.beans.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserDetailsByEmail(String email);
}
