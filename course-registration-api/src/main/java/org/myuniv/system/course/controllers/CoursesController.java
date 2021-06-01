package org.myuniv.system.course.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dto.CourseDto;
import org.myuniv.system.course.beans.requests.CourseRequest;
import org.myuniv.system.course.beans.responses.CourseResponse;
import org.myuniv.system.course.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/courses") public class CoursesController {

    @Autowired CoursesService coursesService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CourseResponse> createCourse(@Validated @RequestBody CourseRequest courseDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CourseDto courseDto = modelMapper.map(courseDetails, CourseDto.class);

        CourseDto createdCourse = coursesService.createCourse(courseDto);

        CourseResponse returnValue = modelMapper.map(createdCourse, CourseResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping(value = "/{courseId}", consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CourseResponse> editCourseDetails(@PathVariable Long courseId,
        @Validated @RequestBody CourseRequest courseDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CourseDto courseDto = modelMapper.map(courseDetails, CourseDto.class);
        courseDto.setId(courseId);

        CourseDto createdCourse = coursesService.updateCourse(courseDto);

        CourseResponse returnValue = modelMapper.map(createdCourse, CourseResponse.class);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
    }

    @GetMapping(value = "/{courseId}", consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CourseResponse> viewCourseDetails(@PathVariable Long courseId) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CourseDto course = coursesService.retrieveCourse(courseId);

        CourseResponse returnValue = modelMapper.map(course, CourseResponse.class);
        return ResponseEntity.status(HttpStatus.FOUND).body(returnValue);
    }

    @DeleteMapping(value = "/{courseId}", consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE}) public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        coursesService.deleteCourse(courseId);

        return ResponseEntity.status(HttpStatus.OK).body("Course Deleted");
    }

}
