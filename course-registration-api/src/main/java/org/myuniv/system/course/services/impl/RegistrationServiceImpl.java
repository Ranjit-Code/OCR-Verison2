package org.myuniv.system.course.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dao.CourseEntity;
import org.myuniv.system.course.beans.dao.ProfileEntity;
import org.myuniv.system.course.beans.dao.RegistrationEntity;
import org.myuniv.system.course.beans.dto.RegistrationDto;
import org.myuniv.system.course.repositories.CoursesRepository;
import org.myuniv.system.course.repositories.ProfilesRepository;
import org.myuniv.system.course.repositories.RegistrationsRepository;
import org.myuniv.system.course.services.RegistrationsService;
import org.myuniv.system.course.utils.RegistrationValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service public class RegistrationServiceImpl implements RegistrationsService {

    private RegistrationsRepository registrationsRepository;
    private ProfilesRepository      profilesRepository;
    private CoursesRepository       coursesRepository;

    @Autowired public RegistrationServiceImpl(RegistrationsRepository registrationsRepository,
        ProfilesRepository profilesRepository, CoursesRepository coursesRepository) {
        this.registrationsRepository = registrationsRepository;
        this.profilesRepository = profilesRepository;
        this.coursesRepository = coursesRepository;
    }

    @Override public RegistrationDto register(RegistrationDto registrationDetails) {
        Optional<CourseEntity> course = coursesRepository.findById(registrationDetails.getCourseId());
        ProfileEntity studentProfile = profilesRepository.findByUserId(registrationDetails.getStudentId());

        // ensure student profile record exists
        if (studentProfile == null) {
            throw new RegistrationValidationException("Student profile doesn't exist");
        }

        // ensure course record exists
        if (!course.isPresent()) {
            throw new RegistrationValidationException("Course doesn't exist");
        }

        CourseEntity courseDetails = course.get();

        String errors = validateRegistrationCriteria(registrationDetails, studentProfile, courseDetails);
        if (errors != null && !errors.isEmpty()) {
            throw new RegistrationValidationException(errors);
        }

        registrationDetails.setStatus("ACTIVE");

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RegistrationEntity registrationEntity = mapper.map(registrationDetails, RegistrationEntity.class);

        registrationsRepository.save(registrationEntity);

        courseDetails.setVacantSlots(courseDetails.getVacantSlots() - 1);
        coursesRepository.save(courseDetails);

        RegistrationDto returnValue = mapper.map(registrationEntity, RegistrationDto.class);

        return returnValue;
    }

    private String validateRegistrationCriteria(RegistrationDto registrationDetails, ProfileEntity studentProfile,
        CourseEntity courseDetails) {
        StringBuilder validationMessage = new StringBuilder();

        // ensure student has paid the fees
        if (!studentProfile.getFeesPaid()) {
            validationMessage.append("Student fees not paid. ");
        }

        // ensure course has seats left
        if (courseDetails.getVacantSlots() < 1) {
            validationMessage.append("Course seats exhausted. ");
        }

        // ensure student is in same term as course offering
        if (!studentProfile.getTerm().equalsIgnoreCase(courseDetails.getTerm())) {
            validationMessage.append("Course is not for the term student is in. ");
        }

        // ensure student has completed pre requisite courses already
        List<String> preRequisiteCourses = new ArrayList<>();
        if (courseDetails.getPreRequisiteCourses() != null && !courseDetails.getPreRequisiteCourses().isEmpty()) {
            preRequisiteCourses.addAll(Arrays.asList(courseDetails.getPreRequisiteCourses().split(",")));
        }
        for (String preRequisiteCourseId : preRequisiteCourses) {
            Optional<RegistrationEntity> byId = registrationsRepository
                .findByCourseIdAndStudentId(Long.parseLong(preRequisiteCourseId), registrationDetails.getStudentId());
            if (!byId.isPresent()) {
                validationMessage.append("Pre requisite course " + preRequisiteCourseId + " incomplete. ");
            }
        }
        return String.valueOf(validationMessage);
    }

    @Override public RegistrationDto unRegister(Long registrationId) {
        // mark status as inactive
        Optional<RegistrationEntity> byId = registrationsRepository.findById(registrationId);

        if (!byId.isPresent()) {
            throw new RegistrationValidationException("Invalid Registration Id: " + registrationId);
        }

        RegistrationEntity registrationDetails = byId.get();
        registrationDetails.setStatus("INACTIVE");

        registrationsRepository.save(registrationDetails);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RegistrationDto returnValue = mapper.map(registrationDetails, RegistrationDto.class);

        return returnValue;
    }
}
