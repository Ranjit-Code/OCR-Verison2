package org.myuniv.system.course.services;

import org.myuniv.system.course.beans.dto.ProfileDto;
import org.springframework.stereotype.Service;

@Service public interface ProfilesService {
    ProfileDto createProfile(ProfileDto profileDto);

    ProfileDto updateProfile(ProfileDto profileDto);

    ProfileDto retrieveProfile(Long profileId);
}
