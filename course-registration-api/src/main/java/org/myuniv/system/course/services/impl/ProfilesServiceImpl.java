package org.myuniv.system.course.services.impl;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dao.ProfileEntity;
import org.myuniv.system.course.beans.dto.ProfileDto;
import org.myuniv.system.course.repositories.ProfilesRepository;
import org.myuniv.system.course.services.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service public class ProfilesServiceImpl implements ProfilesService {

    ProfilesRepository profilesRepository;

    @Autowired public ProfilesServiceImpl(ProfilesRepository profilesRepository) {
        this.profilesRepository = profilesRepository;
    }

    @Override public ProfileDto createProfile(ProfileDto userProfile) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProfileEntity profileEntity = mapper.map(userProfile, ProfileEntity.class);

        if (userProfile.getFeesPaid() == null) {
            profileEntity.setFeesPaid(false);
        }

        profilesRepository.save(profileEntity);

        ProfileDto returnValue = mapper.map(profileEntity, ProfileDto.class);

        return returnValue;
    }

    @Override public ProfileDto updateProfile(ProfileDto userProfile) {
        Optional<ProfileEntity> profileOptional = profilesRepository.findById(userProfile.getId());
        ProfileEntity profile = null;

        if (!profileOptional.isPresent()) {
            return null;
        }
        profile = profileOptional.get();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProfileEntity profileChanges = mapper.map(userProfile, ProfileEntity.class);
        profileChanges.setId(userProfile.getId());

        ModelMapper updatesMapper = new ModelMapper();
        updatesMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        updatesMapper.map(profileChanges, profile);

        profilesRepository.save(profile);

        ProfileDto returnValue = mapper.map(profile, ProfileDto.class);

        return returnValue;
    }

    @Override public ProfileDto retrieveProfile(Long profileId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Optional<ProfileEntity> profile = profilesRepository.findById(profileId);

        if (!profile.isPresent()) {
            return null;
        }

        ProfileDto returnValue = mapper.map(profile.get(), ProfileDto.class);

        return returnValue;
    }
}
