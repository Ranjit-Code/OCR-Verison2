package org.myuniv.system.course.services;

import org.myuniv.system.course.beans.dto.RegistrationDto;
import org.springframework.stereotype.Service;

@Service public interface RegistrationsService {
    RegistrationDto register(RegistrationDto registrationDto);

    RegistrationDto unRegister(Long registrationId);
}
