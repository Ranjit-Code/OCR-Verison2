package org.myuniv.system.course.services.impl;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dao.CourseEntity;
import org.myuniv.system.course.beans.dto.CourseDto;
import org.myuniv.system.course.repositories.CoursesRepository;
import org.myuniv.system.course.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service public class CoursesServiceImpl implements CoursesService {

    CoursesRepository coursesRepository;

    @Autowired public CoursesServiceImpl(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @Override public CourseDto createCourse(CourseDto courseDetails) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CourseEntity courseEntity = mapper.map(courseDetails, CourseEntity.class);

        coursesRepository.save(courseEntity);

        CourseDto returnValue = mapper.map(courseEntity, CourseDto.class);

        return returnValue;
    }

    @Override public CourseDto updateCourse(CourseDto courseDetails) {
        Optional<CourseEntity> courseOptional = coursesRepository.findById(courseDetails.getId());
        CourseEntity course = null;

        if (!courseOptional.isPresent()) {
            return null;
        }
        course = courseOptional.get();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CourseEntity courseChanges = mapper.map(courseDetails, CourseEntity.class);
        courseChanges.setId(courseDetails.getId());

        ModelMapper updatesMapper = new ModelMapper();
        updatesMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        updatesMapper.map(courseChanges, course);

        coursesRepository.save(course);

        CourseDto returnValue = mapper.map(course, CourseDto.class);

        return returnValue;
    }

    @Override public CourseDto retrieveCourse(Long courseId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Optional<CourseEntity> course = coursesRepository.findById(courseId);

        if (!course.isPresent()) {
            return null;
        }

        CourseDto returnValue = mapper.map(course.get(), CourseDto.class);

        return returnValue;
    }

    @Override public void deleteCourse(Long courseId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Optional<CourseEntity> course = coursesRepository.findById(courseId);

        if (course.isPresent()) {
            coursesRepository.deleteById(courseId);
        }
    }
}
