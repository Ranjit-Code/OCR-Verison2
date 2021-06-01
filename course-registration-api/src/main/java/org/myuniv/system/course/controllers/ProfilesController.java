package org.myuniv.system.course.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dto.ProfileDto;
import org.myuniv.system.course.beans.requests.ProfileRequest;
import org.myuniv.system.course.beans.responses.ProfileResponse;
import org.myuniv.system.course.services.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/profiles") public class ProfilesController {

    @Autowired ProfilesService profilesService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ProfileResponse> createProfile(@Validated @RequestBody ProfileRequest profileDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProfileDto profileDto = modelMapper.map(profileDetails, ProfileDto.class);

        ProfileDto createdProfile = profilesService.createProfile(profileDto);

        ProfileResponse returnValue = modelMapper.map(createdProfile, ProfileResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping(value = "/{profileId}", consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ProfileResponse> editProfile(@PathVariable Long profileId,
        @Validated @RequestBody ProfileRequest profileDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProfileDto profileDto = modelMapper.map(profileDetails, ProfileDto.class);
        profileDto.setId(profileId);

        ProfileDto updatedProfile = profilesService.updateProfile(profileDto);

        ProfileResponse returnValue = modelMapper.map(updatedProfile, ProfileResponse.class);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
    }

    @GetMapping(value = "/{profileId}", consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ProfileResponse> viewProfile(@PathVariable Long profileId) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ProfileDto profile = profilesService.retrieveProfile(profileId);

        ProfileResponse returnValue = modelMapper.map(profile, ProfileResponse.class);
        return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
    }
}
