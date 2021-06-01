package org.myuniv.system.course.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dto.RegistrationDto;
import org.myuniv.system.course.beans.requests.RegistrationRequest;
import org.myuniv.system.course.beans.responses.RegistrationResponse;
import org.myuniv.system.course.services.RegistrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/registrations") public class RegistrationsController {

    @Autowired RegistrationsService registrationsService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RegistrationResponse> registerForCourse(
        @Validated @RequestBody RegistrationRequest registrationDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RegistrationDto registrationDto = modelMapper.map(registrationDetails, RegistrationDto.class);

        RegistrationDto registeredCourse = registrationsService.register(registrationDto);

        RegistrationResponse returnValue = modelMapper.map(registeredCourse, RegistrationResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @DeleteMapping(value = "/{registrationId}", consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}) public ResponseEntity<RegistrationResponse> unRegisterForCourse(
        @PathVariable("registrationId") Long registrationId) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RegistrationDto unRegisteredCourse = registrationsService.unRegister(registrationId);

        RegistrationResponse returnValue = modelMapper.map(unRegisteredCourse, RegistrationResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
